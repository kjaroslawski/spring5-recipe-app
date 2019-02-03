package pl.qamar.converters;

import org.junit.Before;
import org.junit.Test;
import pl.qamar.commands.CategoryCommand;
import pl.qamar.commands.IngredientCommand;
import pl.qamar.commands.NotesCommand;
import pl.qamar.commands.RecipeCommand;
import pl.qamar.domain.Difficulty;
import pl.qamar.domain.Recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 3L;
    public static final Long INGREDIENT_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_1);
        recipeCommand.getCategories().add(categoryCommand);
        categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_2);
        recipeCommand.getCategories().add(categoryCommand);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID_1);
        recipeCommand.getIngredients().add(ingredientCommand);
        ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID_2);
        recipeCommand.getIngredients().add(ingredientCommand);

        //When
        Recipe command = converter.convert(recipeCommand);

        //Then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertNotNull(command.getNotes());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertNotNull(command.getCategories());
        assertEquals(2, command.getCategories().size());
        assertNotNull(command.getIngredients());
        assertEquals(2, command.getIngredients().size());
    }
}