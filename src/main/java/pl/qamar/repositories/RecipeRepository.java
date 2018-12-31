package pl.qamar.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
