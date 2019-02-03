package pl.qamar.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.qamar.commands.IngredientCommand;
import pl.qamar.domain.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
