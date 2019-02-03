package pl.qamar.services;

import pl.qamar.commands.RecipeCommand;
import pl.qamar.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findRecipeCommandById(Long id);

    void deleteById(Long idToDelete);
}
