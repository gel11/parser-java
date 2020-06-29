package enteties;

import exception.IncorrectFormatException;

import java.util.Arrays;
import java.util.List;

public class PhoneNumber extends Entity {

    public static final String ELEMENT_NAME = "phone";
    public static final List<String> CHILD_ELEMENT_NAMES = Arrays.asList("mobile", "landline");
    public static final String TYPE = "T";

    public PhoneNumber(List<String> elements) throws IncorrectFormatException {
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
        return Arrays.asList(Person.ELEMENT_NAME, Family.ELEMENT_NAME);
    }

}
