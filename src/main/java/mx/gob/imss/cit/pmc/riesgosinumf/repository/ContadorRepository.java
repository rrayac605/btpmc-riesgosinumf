package mx.gob.imss.cit.pmc.riesgosinumf.repository;

import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

public interface ContadorRepository {

    long count(TypedAggregation<?> aggregation);

}
