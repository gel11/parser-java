package entities;

import exception.IncorrectFormatException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Family extends Entity {

    public static final String ELEMENT_NAME = "family";
    public static final List<String> CHILD_ELEMENT_NAMES = Arrays.asList("name", "born");
    public static final String TYPE = "F";

    public Family(List<String> elements) throws IncorrectFormatException {
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
        return Collections.singletonList(Person.ELEMENT_NAME);
    }
}
