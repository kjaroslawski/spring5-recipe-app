package pl.qamar.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.qamar.commands.RecipeCommand;
import pl.qamar.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient();
        source.getIngredients().forEach(ingredient -> {
            recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient));
        });
        recipe.setDifficulty(source.getDifficulty());
        NotesCommandToNotes notesCommandToNotes = new NotesCommandToNotes();
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        CategoryCommandToCategory categoryCommandToCategory = new CategoryCommandToCategory();
        source.getCategories().forEach(category -> {
            recipe.getCategories().add(categoryCommandToCategory.convert(category));
        });
        return recipe;
    }
}
