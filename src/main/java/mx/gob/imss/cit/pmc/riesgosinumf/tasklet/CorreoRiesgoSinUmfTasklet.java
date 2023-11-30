package mx.gob.imss.cit.pmc.riesgosinumf.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.services.EmailRiesgoSinUmfService;

@Component
@StepScope
public class CorreoRiesgoSinUmfTasklet implements Tasklet {
	
	@Autowired
	private EmailRiesgoSinUmfService emailSusToErrService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunk) throws Exception {
		try {
			String nombreTemplate = RiesgoSinUmfConstantes.CORREO_TEMPLATE;
			emailSusToErrService.enviarCorreo(nombreTemplate);
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return RepeatStatus.FINISHED;
	}

}
