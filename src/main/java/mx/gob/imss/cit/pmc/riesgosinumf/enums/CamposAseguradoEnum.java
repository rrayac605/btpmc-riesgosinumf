package mx.gob.imss.cit.pmc.riesgosinumf.enums;

import lombok.Getter;

@Getter
public enum CamposAseguradoEnum {

    FEC_ALTA("aseguradoDTO.fecAlta"),
    CVE_ESTADO_REGISTRO("aseguradoDTO.cveEstadoRegistro"),
    CVE_CASO_REGISTRO("aseguradoDTO.cveCasoRegistro"),
    CYCLE("aseguradoDTO.numCicloAnual"),
    NUM_NSS("aseguradoDTO.numNss"),
    NOMBRE("aseguradoDTO.nomAsegurado"),
    PRIMER_APELLIDO("aseguradoDTO.refPrimerApellido"),
    SEGUNDO_APELLIDO("aseguradoDTO.refSegundoApellido"),
    CURP("aseguradoDTO.refCurp"),
    CVE_DELEGACION_NSS("aseguradoDTO.cveDelegacionNss"),
    CVE_SUB_DELEGACION_NSS("aseguradoDTO.cveSubdelNss"),
    FEC_BAJA("aseguradoDTO.fecBaja"),
    CVE_UMF_ADSCRIP("aseguradoDTO.cveUmfAdscripcion");

    private final String desc;

    CamposAseguradoEnum(String desc) {
        this.desc = desc;
    }

}
