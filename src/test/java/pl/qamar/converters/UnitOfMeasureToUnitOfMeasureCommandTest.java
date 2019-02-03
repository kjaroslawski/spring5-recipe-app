package pl.qamar.converters;

import org.junit.Before;
import org.junit.Test;
import pl.qamar.commands.UnitOfMeasureCommand;
import pl.qamar.domain.UnitOfMeasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);
    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //Given
        UnitOfMeasure command = new UnitOfMeasure();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //When
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(command);

        //Then
        assertNotNull(unitOfMeasureCommand);
        assertEquals(LONG_VALUE, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}