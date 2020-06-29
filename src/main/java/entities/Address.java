package enteties;

import exception.IncorrectFormatException;

import java.util.Arrays;
import java.util.List;

public class Address extends Entity {

    public static final String ELEMENT_NAME = "address";
    public static final List<String> CHILD_ELEMENT_NAMES = Arrays.asList("street", "city", "postal");
    public static final String TYPE = "A";

    public Address(List<String> elements) throws IncorrectFormatException {
        super(elements);
    }

    @Override
    public boolean isParent() {
        return false;
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
        return Arrays.asList(Family.ELEMENT_NAME, Person.ELEMENT_NAME);
    }
}
