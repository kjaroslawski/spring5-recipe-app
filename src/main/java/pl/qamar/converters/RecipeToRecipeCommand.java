package pl.qamar.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.qamar.commands.RecipeCommand;
import pl.qamar.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand();
        source.getIngredients().forEach(ingredient -> {
            recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient));
        });
        recipeCommand.setDifficulty(source.getDifficulty());
        NotesToNotesCommand notesToNotesCommand = new NotesToNotesCommand();
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        CategoryToCategoryCommand categoryToCategoryCommand = new CategoryToCategoryCommand();
        source.getCategories().forEach(category -> {
            recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category));
        });
        return recipeCommand;
    }
}
