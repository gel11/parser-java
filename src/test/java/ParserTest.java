import enteties.*;
import exception.IncorrectFormatException;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private Parser parser = new Parser();

    @Test
    public void testParseAddress() throws IncorrectFormatException {
        String line = "A|MyStreet 55|City|5520";

        Entity entity = parser.parseLineToEntity(line);

        assertTrue(entity instanceof Address);

        Address address = Address.class.cast(entity);

        assertEquals(address.getHashMap().size(), 3);
        assertThat(address.getHashMap(), IsMapContaining.hasEntry("street", "MyStreet 55"));
        assertThat(address.getHashMap(), IsMapContaining.hasEntry("city", "City"));
        assertThat(address.getHashMap(), IsMapContaining.hasEntry("postal", "5520"));
    }

    @Test
    public void testParseFamily() throws IncorrectFormatException {
        String line = "F|Gustav|1822";

        Entity entity = parser.parseLineToEntity(line);

        assertTrue(entity instanceof Family);

        Family family = Family.class.cast(entity);

        assertEquals(family.getHashMap().size(), 2);
        assertThat(family.getHashMap(), IsMapContaining.hasEntry("name", "Gustav"));
        assertThat(family.getHashMap(), IsMapContaining.hasEntry("born", "1822"));
    }

    @Test
    public void testParsePerson() throws IncorrectFormatException {
        String line = "P|Gustav|Elmgren";

        Entity entity = parser.parseLineToEntity(line);

        assertTrue(entity instanceof Person);

        Person person = Person.class.cast(entity);

        assertEquals(person.getHashMap().size(), 2);
        assertThat(person.getHashMap(), IsMapContaining.hasEntry("firstname", "Gustav"));
        assertThat(person.getHashMap(), IsMapContaining.hasEntry("lastname", "Elmgren"));
    }

    @Test
    public void testParsePhoneNumber() throws IncorrectFormatException {
        String line = "T|0722|5544554";

        Entity entity = parser.parseLineToEntity(line);

        assertTrue(entity instanceof PhoneNumber);

        PhoneNumber phoneNumber = PhoneNumber.class.cast(entity);

        assertEquals(phoneNumber.getHashMap().size(), 2);
        assertThat(phoneNumber.getHashMap(), IsMapContaining.hasEntry("mobile", "0722"));
        assertThat(phoneNumber.getHashMap(), IsMapContaining.hasEntry("landline", "5544554"));
    }

    @Test
    public void testParseAddressLineWithTooManyColumns() {
        String line = "A|MyStreet 55|City|5520|Extra";

        Assertions.assertThrows(IncorrectFormatException.class, () -> {
           parser.parseLineToEntity(line);
        });
    }

}