package enteties;

import exception.IncorrectFormatException;

import java.util.Arrays;
import java.util.List;

public class Person extends Entity {

    public static final String ELEMENT_NAME = "person";
    public static final List<String> CHILD_ELEMENT_NAMES = Arrays.asList("firstname", "lastname");
    public static final String TYPE = "P";

    public Person(List<String> elements) throws IncorrectFormatException {
        super(elements);
    }

    @Override
    public boolean isParent() {
        return true;
    }

    @Override
    public List<String> getChildElementNames() {
        return CHILD_ELEMENT_NAMES;
    }

    @Override
    public String getElementName() {
        return ELEMENT_NAME;
    }

    @Override
    public List<String> getParentTypes() {
        return Arrays.asList(Entity.ROOT_ELEMENT_NAME);
    }
}
