package pl.qamar.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
