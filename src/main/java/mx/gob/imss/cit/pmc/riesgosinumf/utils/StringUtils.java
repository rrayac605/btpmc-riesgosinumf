package mx.gob.imss.cit.pmc.riesgosinumf.utils;

import mx.gob.imss.cit.pmc.riesgosinumf.constantes.RiesgoSinUmfConstantes;
import mx.gob.imss.cit.pmc.riesgosinumf.dto.ParametroDTO;

import java.util.Optional;

public class StringUtils {

    public static String getFromParam(Optional<ParametroDTO<String>> parameterDTO) {
        return parameterDTO.map(ParametroDTO::getDesParametro).orElse(RiesgoSinUmfConstantes.VACIO);
    }
    

}
