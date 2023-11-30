package mx.gob.imss.cit.pmc.riesgosinumf.repository;

import java.util.List;
import java.util.Optional;

import mx.gob.imss.cit.pmc.riesgosinumf.dto.ParametroDTO;

public interface ParametroRepository {

    Optional<ParametroDTO<String>> findOneByCve(String cve);

    Optional<ParametroDTO<List<String>>> findListByCve(String cve);

}
