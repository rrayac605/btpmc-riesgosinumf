package mx.gob.imss.cit.pmc.riesgosinumf.repository;

import mx.gob.imss.cit.pmc.riesgosinumf.dto.EmailTemplateDTO;

public interface EmailTemplateRepository {

    EmailTemplateDTO findByName(String name);

}
