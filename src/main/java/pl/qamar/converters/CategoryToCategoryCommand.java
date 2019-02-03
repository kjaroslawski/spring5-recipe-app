package pl.qamar.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.qamar.commands.CategoryCommand;
import pl.qamar.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());
        return categoryCommand;
    }
}
