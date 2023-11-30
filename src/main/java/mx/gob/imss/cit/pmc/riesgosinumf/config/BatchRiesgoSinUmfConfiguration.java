package mx.gob.imss.cit.pmc.riesgosinumf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.MovimientoDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.processor.RiesgoSinUmfProcessor;
import mx.gob.imss.cit.pmc.riesgosinumf.tasklet.CorreoRiesgoSinUmfTasklet;
import mx.gob.imss.cit.pmc.riesgosinumf.utils.ReaderUtils;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "mx.gob.imss.cit.pmc.riesgosinumf" })
public class BatchRiesgoSinUmfConfiguration extends DefaultBatchConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchRiesgoSinUmfConfiguration.class);
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Autowired
	private StepBuilderFactory sbf;
	
	@Autowired
	private MongoOperations mongoOperations;
		
	@Autowired
	private RiesgoSinUmfProcessor riesgoSinUmfProcessor;
		
	@Autowired
	private CorreoRiesgoSinUmfTasklet correoSusToErrTasklet;
	
	@Bean(name = "pmcRiesgoSinUmf")
	public Job riesgoSinUmf() {
		return jbf.get("pmcRiesgoSinUmf")
				.incrementer(new RunIdIncrementer())
				.start(pasoActualizaRiesgoSinUmf())
				.next(pasoEnviaCorreo())
				.build();
	}
		
	@Bean
	public Step pasoActualizaRiesgoSinUmf() {
		return sbf.get("pasoActualizaRiesgoSinUmf")
				.<MovimientoDTO, MovimientoDTO>chunk(RiesgoSinUmfConstantes.CHUNK_SIZE)
				.reader(consultarRiesgoSinUMF())
				.processor(riesgoSinUmfProcessor)
				.writer(riesgoSinUmfWriter())
				.build();
	}
	
	@Bean
	public Step pasoEnviaCorreo() {
		return sbf.get("pasoEnviaCorreo")
				.tasklet(correoSusToErrTasklet)
				.build();
	}
			
	@Bean
	@StepScope
	public MongoItemReader<MovimientoDTO> consultarRiesgoSinUMF(){
		logger.info("<---------------------------------------------------------------------------------------------->");
		logger.info("Consulta riesgos sin UMF {}", ReaderUtils.consultaJSONRiesgoSinUmf());
		logger.info("<---------------------------------------------------------------------------------------------->");
		return new MongoItemReaderBuilder<MovimientoDTO>()
				.name("consultarRiesgoSinUMF")
				.template(mongoOperations)
				.targetType(MovimientoDTO.class)
				.sorts(RiesgoSinUmfConstantes.ORDENAMIENTO)
				.jsonQuery(ReaderUtils.consultaJSONRiesgoSinUmf())
				.build();
	}
		
	@Bean
	MongoItemWriter<MovimientoDTO> riesgoSinUmfWriter() {
		return new MongoItemWriterBuilder<MovimientoDTO>()
				.template(mongoOperations)
				.collection("MCT_MOVIMIENTO")
				.build();
	}

}
