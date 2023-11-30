package mx.gob.imss.cit.pmc.riesgosinumf.controller;

import java.text.MessageFormat;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.utils.DateUtils;

@RestController
@RequestMapping("/btpmcRiesgoSinUmf")
public class BatchRiesgoSinUmfControllerImpl {
	
	Logger logger = LoggerFactory.getLogger("BatchRiesgoSinUmfControllerImpl");
	
	@Autowired
	@Qualifier("jobLauncherController")
	private SimpleJobLauncher jobLauncherController;
	
	@Autowired
	@Qualifier("pmcRiesgoSinUmf")
	private Job pmcRiesgoSinUmf;
	
	@Bean
	public SimpleJobLauncher jobLauncherController(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
	
	@PostMapping(value = "/ejecutar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> execute() {
		
		logger.info("Se inicia la ejecución del proceso");
		
		ResponseEntity<?> response = null;
		
		try {
			JobParameters params = new JobParametersBuilder()
					.addDate(RiesgoSinUmfConstantes.FECHA_EJECUCION, Objects.requireNonNull(DateUtils.obtenerFechaMexico()))
					.toJobParameters();
			JobExecution execution = jobLauncherController.run(pmcRiesgoSinUmf, params);
			response = new ResponseEntity<>("Proceso de riesgos sin UMF ejecutado correctamente",
					HttpStatus.OK);
			logger.info(MessageFormat.format("La ejecucion fue exitosa, fecha de inicio: {0}, fecha de fin: {1}",
					execution.getStartTime(), execution.getEndTime()));			
		} catch(Exception exc) {
			logger.info("Error al ejecutar {}", exc);
			response = new ResponseEntity<>("Error al ejecutar el batch", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
		
	}

}
