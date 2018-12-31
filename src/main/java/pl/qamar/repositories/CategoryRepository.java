package pl.qamar.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
