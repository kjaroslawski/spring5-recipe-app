package pl.qamar.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.qamar.commands.RecipeCommand;
import pl.qamar.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientCommandToIngredient,
                                 CategoryToCategoryCommand categoryCommandToCategory,
                                 NotesToNotesCommand notesToNotesCommand) {
        this.ingredientToIngredientCommand = ingredientCommandToIngredient;
        this.categoryToCategoryCommand = categoryCommandToCategory;
        this.notesToNotesCommand = notesToNotesCommand;
    }

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
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> {
                recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient));
            });
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> {
                recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category));
            });
        }
        return recipeCommand;
    }
}
