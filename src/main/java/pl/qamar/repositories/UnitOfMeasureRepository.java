package pl.qamar.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
