package enteties;

import exception.IncorrectFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.List;


public abstract class Entity {

    private final HashMap<String, String> hashMap = new HashMap<>();

    public static final String ROOT_ELEMENT_NAME = "people";

    public Entity() {
    }

    public Entity(List<String> elements) throws IncorrectFormatException {
        if (elements.size() > getChildElementNames().size())
            throw new IncorrectFormatException(String.format("wrong number of columns, got %d but expected %d", elements.size(), getChildElementNames().size()));

        for (int i = 0; i < elements.size(); i++)
            this.hashMap.put(getChildElementNames().get(i), elements.get(i));
    }


    public Element createElement(Document document, Element parent) {
        Element element = document.createElement(this.getElementName());

        this.hashMap.forEach((key, value) -> {
            Element e = document.createElement(key);
            e.appendChild(document.createTextNode(value));
            element.appendChild(e);
        });

        parent.appendChild(element);
        return element;
    }

    public abstract boolean isParent();

    public abstract List<String> getChildElementNames();

    public abstract String getElementName();

    public abstract List<String> getParentTypes();

    public HashMap<String, String> getHashMap() {
        return this.hashMap;
    }

}
