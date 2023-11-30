package mx.gob.imss.cit.pmc.riesgosinumf.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.EmailTemplateDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.MovimientoDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.repository.ContadorRepository;
import mx.gob.imss.cit.pmc.riesgosinumf.repository.EmailTemplateRepository;
import mx.gob.imss.cit.pmc.riesgosinumf.services.EmailRiesgoSinUmfService;
import mx.gob.imss.cit.pmc.riesgosinumf.utils.DateUtils;
import mx.gob.imss.cit.pmc.riesgosinumf.utils.ReaderUtils;

@Service
public class EmailRiesgoSinUmfServiceImpl implements EmailRiesgoSinUmfService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailRiesgoSinUmfServiceImpl.class);
	
	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ContadorRepository contadorRepository;
	
	@Override
	public void enviarCorreo(String nombreTemplate) {
		try {
			EmailTemplateDTO emailTemplate = emailTemplateRepository.findByName(nombreTemplate);
			llenarTemplate(emailTemplate);
			MimeMessageHelper mimeMessageHelper = construirMimeMessageHelper(emailTemplate);
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch(MessagingException | MailException me) {
			logger.error("Error al enviar el correo ", me);
		} catch (UnsupportedEncodingException uee) {
			logger.error("Erorr al codificar encabezado {}", uee);
		}
	}
	
	private MimeMessageHelper construirMimeMessageHelper(EmailTemplateDTO emailTemplate) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, Boolean.TRUE);
		mimeMessageHelper.setSubject(MimeUtility.encodeText(emailTemplate.getSubject(), "UTF-8", "Q"));
		mimeMessageHelper.setFrom(emailTemplate.getFrom());
		mimeMessageHelper.setTo(emailTemplate.getTo());
		mimeMessageHelper.setText(emailTemplate.getTemplate(), Boolean.TRUE);
		mimeMessageHelper.addInline(RiesgoSinUmfConstantes.ENCABEZADO_IMG,
				new ClassPathResource(RiesgoSinUmfConstantes.RUTA_IMGS.concat(RiesgoSinUmfConstantes.ENCABEZADO_IMG)));
		mimeMessageHelper.addInline(RiesgoSinUmfConstantes.PIE_IMG,
				new ClassPathResource(RiesgoSinUmfConstantes.RUTA_IMGS.concat(RiesgoSinUmfConstantes.PIE_IMG)));
		
		return mimeMessageHelper;
	}
	
	private void llenarTemplate(EmailTemplateDTO emailTemplateDTO) {
		
		TypedAggregation<MovimientoDTO> riesgosObtenidos = ReaderUtils.contadorRiesgosActualizados();
		TypedAggregation<MovimientoDTO> riesgoSinAsignar = ReaderUtils.contadorRiesgoSinUmf();
		TypedAggregation<MovimientoDTO> correctoToCorrectosOtras = ReaderUtils.contadorCorrectoToCorrectoOtras();
		TypedAggregation<MovimientoDTO> errToErrOtras = ReaderUtils.contadorErroneoToErroneoOtras();
		TypedAggregation<MovimientoDTO> susToSusotras = ReaderUtils.contadorSusToSusOtras();
		TypedAggregation<MovimientoDTO> regsCorrectoOtras = ReaderUtils.contadorTotalCorrectosOtras();
		TypedAggregation<MovimientoDTO> regsErrOtras = ReaderUtils.contadorTotalErrOtras();
		TypedAggregation<MovimientoDTO> regsSusotras = ReaderUtils.contadorTotalSusOtras();
		
		long obtenidos = contadorRepository.count(riesgosObtenidos);
		long asignados = contadorRepository.count(riesgosObtenidos);
		long sinAsignar = contadorRepository.count(riesgoSinAsignar);
		long correctosOtras = contadorRepository.count(correctoToCorrectosOtras);
		long errOtras = contadorRepository.count(errToErrOtras);
		long susOtras = contadorRepository.count(susToSusotras);
		long totalCorrectosOtras = contadorRepository.count(regsCorrectoOtras);
		long totalErrOtras = contadorRepository.count(regsErrOtras);
		long totalSusOtras = contadorRepository.count(regsSusotras);		
		
		if(sinAsignar > 0L) {
			obtenidos = obtenidos + sinAsignar;
			asignados = asignados - sinAsignar;
		}
		
		long[] registrosAntes = {obtenidos, asignados, sinAsignar};
		long[] registrosActualizados = {correctosOtras, errOtras, susOtras};
		long[] registrosDespues = {totalCorrectosOtras, totalErrOtras, totalSusOtras};
				
		String fechaHora = "";
		String totalesAntes = "";
		String totalesActualizados = "";
		String totalesDespues = "";
		String listaOOAD = "";
		
		fechaHora = fechaHora.concat("<p>").concat(DateUtils.obtenerFechaMexicoString()).concat("</p>");
		
		for(int i = 0; i < RiesgoSinUmfConstantes.REGISTROS_ANTES.length; i++) {
			totalesAntes = totalesAntes.concat("<p>").concat(RiesgoSinUmfConstantes.REGISTROS_ANTES[i])
					.concat(String.valueOf(registrosAntes[i])).concat("</p>");
		}
		
		for(int j = 0; j < RiesgoSinUmfConstantes.REGISTROS_ACTUALIZADOS.length; j++) {
			totalesActualizados = totalesActualizados.concat("<p>").concat(RiesgoSinUmfConstantes.REGISTROS_ACTUALIZADOS[j])
					.concat(String.valueOf(registrosActualizados[j])).concat("</p>");
		}
		
		for(int k = 0; k < RiesgoSinUmfConstantes.REGISTROS_DESPUES.length; k++) {
			totalesDespues = totalesDespues.concat("<p>").concat(RiesgoSinUmfConstantes.REGISTROS_DESPUES[k])
					.concat(String.valueOf(registrosDespues[k])).concat("</p>");
		}
		
		for(int l = 1; l <= RiesgoSinUmfConstantes.CVES_DEL_SUBDEL.size(); l++) {
			List<Integer> cves = RiesgoSinUmfConstantes.CVES_DEL_SUBDEL.get(l);
			List<Integer> subDelList = RiesgoSinUmfConstantes.CVES_DEL_SUBDEL.get(l);
			List<String> desSubDels = RiesgoSinUmfConstantes.DESCRIPCION_SUBDELS.get(l);
			List<Integer> subDels = subDelList.subList(1, subDelList.size());
			String delDesc = RiesgoSinUmfConstantes.DESCRIPCION_DELEGACION.get(l);
			int del = cves.get(0);
			
			logger.info("Delegacion a buscar {}", del);
			
			for(int m = 1; m <= subDels.size(); m++) {
				logger.info("Subdelegacion a buscar {}", subDelList.get(m));
				TypedAggregation<MovimientoDTO> conteoOOAD = ReaderUtils.contadorListaOOAD(del, subDelList.get(m));
				long totalesOOAD = contadorRepository.count(conteoOOAD);
				if(totalesOOAD > 0L) {
					logger.info("Delegacion {} y subdelegacion {} con riesgos asignados", delDesc, desSubDels.get(m-1));
					listaOOAD = listaOOAD.concat("<p>").concat(delDesc).concat(", ").concat(desSubDels.get(m-1))
							.concat(" - ").concat(String.valueOf(totalesOOAD)).concat("</p>");
					logger.info("Lista a enviar {}", listaOOAD);
				}	
			}
		}
		
		logger.info("Datos en fechaHora {}", fechaHora);
		logger.info("Datos en totales antes {}", totalesAntes);
		logger.info("Datos en totales actualizados {} ", totalesActualizados);
		logger.info("Datos en totales despues {}", totalesDespues);
		logger.info("Del subdel {}", listaOOAD);
		
		emailTemplateDTO.setTemplate(emailTemplateDTO.getTemplate().replace(RiesgoSinUmfConstantes.FECHA_HORA, fechaHora));
		emailTemplateDTO.setTemplate(emailTemplateDTO.getTemplate().replace(RiesgoSinUmfConstantes.TOTALES_ANTES, totalesAntes));
		emailTemplateDTO.setTemplate(emailTemplateDTO.getTemplate().replace(RiesgoSinUmfConstantes.TOTALES_ACTUALIZADOS, totalesActualizados));
		emailTemplateDTO.setTemplate(emailTemplateDTO.getTemplate().replace(RiesgoSinUmfConstantes.TOTALES_DESPUES, totalesDespues));
		emailTemplateDTO.setTemplate(emailTemplateDTO.getTemplate().replace(RiesgoSinUmfConstantes.LISTA_ASIGNACION, listaOOAD));
	}

}
