package mx.gob.imss.cit.pmc.riesgosinumf.utils;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.CountOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.MovimientoDTO;
import mx.gob.imss.cit.pmc.riesgosinumf.enums.CamposAseguradoEnum;
import mx.gob.imss.cit.pmc.riesgosinumf.enums.CamposAuditoriasEnum;
import mx.gob.imss.cit.pmc.riesgosinumf.enums.CamposMovimientoEnum;
import mx.gob.imss.cit.pmc.riesgosinumf.enums.CamposPatronEnum;

public class ReaderUtils {
	
	/*** Consulta para obtener riesgos sin UMF ***/
	public static String consultaJSONRiesgoSinUmf() {
		return "{ '$and' : [" + 
			   "{ 'aseguradoDTO.cveUmfAdscripcion' : { '$exists' : false } }," + 
			   "{ 'aseguradoDTO.cveDelegacionNss' : { '$exists' : false } }," + 
			   "{ 'aseguradoDTO.cveSubdelNss' : { '$exists' : false } }," + 
			   "{ 'aseguradoDTO.cveEstadoRegistro' : { '$nin' : [3, 7, 10, 11] } }," + 
			   "{ 'isPending' : { '$exists' : false } }" + 
			   "]}";
	}
	
	/*** Conteo riesgos sin UMF ***/
	public static TypedAggregation<MovimientoDTO> contadorRiesgoSinUmf() {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_UMF_ADSCRIP.getDesc()).exists(Boolean.FALSE),
				Criteria.where(CamposAseguradoEnum.CVE_DELEGACION_NSS.getDesc()).exists(Boolean.FALSE),
				Criteria.where(CamposAseguradoEnum.CVE_SUB_DELEGACION_NSS.getDesc()).exists(Boolean.FALSE),
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).nin(3, 7, 10, 11),
				Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).exists(Boolean.FALSE)
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	/*** Conteos riesgos sin UMF actualizados ***/
	public static TypedAggregation<MovimientoDTO> contadorRiesgosActualizados() {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).gte(DateUtils.fechaInico()),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).lte(DateUtils.fechaFin()),				
				Criteria.where(CamposAuditoriasEnum.DESCRIPCION.getDesc()).is(RiesgoSinUmfConstantes.DESCRIPCION)
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	public static TypedAggregation<MovimientoDTO> contadorListaOOAD(int delegacion, int subDel) {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_DELEGACION_NSS.getDesc()).is(delegacion),
				Criteria.where(CamposAseguradoEnum.CVE_SUB_DELEGACION_NSS.getDesc()).is(subDel),
				Criteria.where(CamposPatronEnum.CVE_DEL_PATRON.getDesc()).is(delegacion),
				Criteria.where(CamposPatronEnum.CVE_SUBDEL_PATRON.getDesc()).is(subDel),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).gte(DateUtils.fechaInico()),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).lte(DateUtils.fechaFin()),				
				Criteria.where(CamposAuditoriasEnum.DESCRIPCION.getDesc()).is(RiesgoSinUmfConstantes.DESCRIPCION)
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	public static TypedAggregation<MovimientoDTO> contadorCorrectoToCorrectoOtras() {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).is(RiesgoSinUmfConstantes.CVE_CORRECTO_OTRAS),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).gte(DateUtils.fechaInico()),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).lte(DateUtils.fechaFin()),				
				Criteria.where(CamposAuditoriasEnum.DESCRIPCION.getDesc()).is(RiesgoSinUmfConstantes.DESCRIPCION)
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	public static TypedAggregation<MovimientoDTO> contadorErroneoToErroneoOtras(){
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).is(RiesgoSinUmfConstantes.CVE_ERRONEO_OTRAS),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).gte(DateUtils.fechaInico()),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).lte(DateUtils.fechaFin()),
				Criteria.where(CamposAuditoriasEnum.DESCRIPCION.getDesc()).is(RiesgoSinUmfConstantes.DESCRIPCION)				
				));		
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	public static TypedAggregation<MovimientoDTO> contadorSusToSusOtras(){
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).is(RiesgoSinUmfConstantes.CVE_SUSCEPTIBLE_OTRAS),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).gte(DateUtils.fechaInico()),
				Criteria.where(CamposAuditoriasEnum.FEC_ALTA.getDesc()).lte(DateUtils.fechaFin()),
				Criteria.where(CamposAuditoriasEnum.DESCRIPCION.getDesc()).is(RiesgoSinUmfConstantes.DESCRIPCION)
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);		
	}	
	
	/*** Conteos totales ***/
	public static TypedAggregation<MovimientoDTO> contadorTotalCorrectosOtras() {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).is(RiesgoSinUmfConstantes.CVE_CORRECTO_OTRAS),
				new Criteria().orOperator(
						Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).exists(Boolean.FALSE),
						Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).is(Boolean.FALSE))
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	public static TypedAggregation<MovimientoDTO> contadorTotalErrOtras() {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).is(RiesgoSinUmfConstantes.CVE_ERRONEO_OTRAS),
				new Criteria().orOperator(
						Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).exists(Boolean.FALSE),
						Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).is(Boolean.FALSE))
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}
	
	public static TypedAggregation<MovimientoDTO> contadorTotalSusOtras() {
		MatchOperation matchOperation = Aggregation.match(new Criteria().andOperator(
				Criteria.where(CamposAseguradoEnum.CVE_ESTADO_REGISTRO.getDesc()).is(RiesgoSinUmfConstantes.CVE_SUSCEPTIBLE_OTRAS),
				new Criteria().orOperator(
						Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).exists(Boolean.FALSE),
						Criteria.where(CamposMovimientoEnum.IS_PENDING.getDesc()).is(Boolean.FALSE))
				));
		
		CountOperation count = Aggregation.count().as(RiesgoSinUmfConstantes.COUNT);
		
		return Aggregation.newAggregation(MovimientoDTO.class, matchOperation, count);
	}

}
