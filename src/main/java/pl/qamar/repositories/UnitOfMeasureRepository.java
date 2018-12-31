package pl.qamar.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
