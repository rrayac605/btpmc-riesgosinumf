package mx.gob.imss.cit.pmc.riesgosinumf.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.AseguradoDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.AuditoriaDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.MovimientoDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.PatronDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.utils.DateUtils;

@Component
@StepScope
public class RiesgoSinUmfProcessor implements ItemProcessor<MovimientoDTO, MovimientoDTO>{
	
	private static final Logger logger = LoggerFactory.getLogger(RiesgoSinUmfProcessor.class);
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Override
	public MovimientoDTO process(MovimientoDTO movimientoDTO) {
		
		AseguradoDTO asegurado = movimientoDTO.getAseguradoDTO();
		PatronDTO patron = movimientoDTO.getPatronDTO();
		List<AuditoriaDTO> auditorias = movimientoDTO.getAuditorias();
		List<AuditoriaDTO> nuevaAuditoria = new ArrayList<AuditoriaDTO>();
		
		if(patron.getCveDelRegPatronal() != null && patron.getCveSubDelRegPatronal() != null) {
		
			logger.info("Comienza la asignacion del NSS {}", asegurado.getNumNss());
			
			asegurado.setCveDelegacionNss(patron.getCveDelRegPatronal());
			asegurado.setDesDelegacionNss(patron.getDesDelRegPatronal());
			asegurado.setCveSubdelNss(patron.getCveSubDelRegPatronal());
			asegurado.setDesSubDelNss(patron.getDesSubDelRegPatronal());
				
			if(asegurado.getCveEstadoRegistro() != null && asegurado.getCveEstadoRegistro().equals(RiesgoSinUmfConstantes.CVE_CORRECTO)) {
				asegurado.setCveEstadoRegistro(RiesgoSinUmfConstantes.CVE_CORRECTO_OTRAS);
				asegurado.setDesEstadoRegistro(RiesgoSinUmfConstantes.DES_CORRECTO_OTRAS);			
			}
			else if(asegurado.getCveEstadoRegistro() != null && asegurado.getCveEstadoRegistro().equals(RiesgoSinUmfConstantes.CVE_ERRONEO)) {
				asegurado.setCveEstadoRegistro(RiesgoSinUmfConstantes.CVE_ERRONEO_OTRAS);
				asegurado.setDesEstadoRegistro(RiesgoSinUmfConstantes.DES_ERRONEO_OTRAS);
			}
			else if(asegurado.getCveEstadoRegistro() != null && asegurado.getCveEstadoRegistro().equals(RiesgoSinUmfConstantes.CVE_SUSCEPTIBLE)) {
				asegurado.setCveEstadoRegistro(RiesgoSinUmfConstantes.CVE_SUSCEPTIBLE_OTRAS);
				asegurado.setDesEstadoRegistro(RiesgoSinUmfConstantes.DES_SUSCEPTIBLE_OTRAS);
			}
			
			asegurado.setFecActualizacion(DateUtils.obtenerFechaMongoISO());
			
			AuditoriaDTO auditoria = auditorias != null ? auditorias.get(1) : new AuditoriaDTO();
			
			if(!(auditoria == null) && auditoria.getFecBaja() == null) {
				auditoria.setFecBaja(DateUtils.obtenerFechaMongoISO());
			}
			
			AuditoriaDTO auditoriaRiesgoSinUmf = new AuditoriaDTO();
			auditoriaRiesgoSinUmf.setFecAlta(DateUtils.obtenerFechaMongoISO());
			auditoriaRiesgoSinUmf.setNomUsuario(RiesgoSinUmfConstantes.USUARIO);
			auditoriaRiesgoSinUmf.setDesAccionRegistro(RiesgoSinUmfConstantes.ACCION);
			auditoriaRiesgoSinUmf.setDesSituacionRegistro(RiesgoSinUmfConstantes.DES_SITUACION_REGISTRO_PENDIENTE);
			auditoriaRiesgoSinUmf.setOrdenEjecucion(RiesgoSinUmfConstantes.ORDEN_EJECUCION_1);
			auditoriaRiesgoSinUmf.setDescripcion(RiesgoSinUmfConstantes.DESCRIPCION);
			auditoriaRiesgoSinUmf.setFecBaja(DateUtils.obtenerFechaMongoISO());
			if(auditorias != null) {
				auditorias.add(auditoriaRiesgoSinUmf);
			}else {
				nuevaAuditoria.add(auditoriaRiesgoSinUmf);
				movimientoDTO.setAuditorias(nuevaAuditoria);
			}
			
			AuditoriaDTO auditoriasRiesgoSinUmf = new AuditoriaDTO();
			auditoriasRiesgoSinUmf.setFecAlta(DateUtils.obtenerFechaMongoISO());
			auditoriasRiesgoSinUmf.setNomUsuario(RiesgoSinUmfConstantes.USUARIO);
			auditoriasRiesgoSinUmf.setDesAccionRegistro(RiesgoSinUmfConstantes.ACCION);
			auditoriasRiesgoSinUmf.setDesSituacionRegistro(RiesgoSinUmfConstantes.DES_SITUACION_REGISTRO_APROBADO);
			auditoriasRiesgoSinUmf.setOrdenEjecucion(RiesgoSinUmfConstantes.ORDEN_EJECUCION_2);
			auditoriasRiesgoSinUmf.setDescripcion(RiesgoSinUmfConstantes.DESCRIPCION);
			if(auditorias != null) {
				auditorias.add(auditoriaRiesgoSinUmf);
			}else {
				nuevaAuditoria.add(auditoriasRiesgoSinUmf);
				movimientoDTO.setAuditorias(nuevaAuditoria);
			}
			
			mongoOperations.save(movimientoDTO);
			
			logger.info("Concluye la asignacion del NSS {}", asegurado.getNumNss());
			
		}else {
			logger.info("El patron del NSS {} no cuenta con OOAD ni subdelegacion", asegurado.getNumNss());
		}
						
		return movimientoDTO;
	}

}
