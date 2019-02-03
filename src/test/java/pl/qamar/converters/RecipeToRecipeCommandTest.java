package pl.qamar.converters;

import org.junit.Before;
import org.junit.Test;
import pl.qamar.commands.RecipeCommand;
import pl.qamar.domain.Category;
import pl.qamar.domain.Difficulty;
import pl.qamar.domain.Ingredient;
import pl.qamar.domain.Notes;
import pl.qamar.domain.Recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

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
    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //Given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);
        Category category = new Category();
        category.setId(CAT_ID_1);
        recipe.getCategories().add(category);
        category = new Category();
        category.setId(CAT_ID_2);
        recipe.getCategories().add(category);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_1);
        recipe.getIngredients().add(ingredient);
        ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_2);
        recipe.getIngredients().add(ingredient);

        //When
        RecipeCommand command = converter.convert(recipe);

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