package pl.qamar.services;

import org.springframework.stereotype.Service;
import pl.qamar.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
