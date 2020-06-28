import enteties.*;
import exception.IncorrectFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private Document document;
    private Element root;
    private final LatestAddedParentsList parentsList = new LatestAddedParentsList();

    public void parse(String inputPath) throws IOException, ParserConfigurationException {
        FileInputStream fis = new FileInputStream(inputPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        this.document = documentBuilder.newDocument();

        this.root = document.createElement(Entity.ROOT_ELEMENT_NAME);
        document.appendChild(this.root);

        this.readLines(br);
    }

    public void readLines(BufferedReader br) throws IOException {
        Element parent = this.root;
        this.parentsList.addNewParent(parent);
        String line;
        int lineNumber = 1;

        while ((line = br.readLine()) != null) {
            Entity entity;
            try {
                entity = parseLineToEntity(line);
                parent = this.parentsList.getLatestElementOfType(entity.getParentTypes());

                Element element = entity.createElement(document, parent);

                if (entity.isParent())
                    this.parentsList.addNewParent(element);

                lineNumber++;
            } catch (IncorrectFormatException e) {
                System.out.println(String.format("Could not parse row at line %d, %s. Skipping...", lineNumber, e.getLocalizedMessage()));
            }
        }

        br.close();
    }

    public Entity parseLineToEntity(String line) throws IncorrectFormatException {
        List<String> split = new ArrayList<>(Arrays.asList(line.split("\\|")));

        String type = split.remove(0);

        switch (type) {
            case PhoneNumber.TYPE:
                return new PhoneNumber(split);
            case Address.TYPE:
                return new Address(split);
            case Person.TYPE:
                return new Person(split);
            case Family.TYPE:
                return new Family(split);
            default:
                throw new IncorrectFormatException("could not determinate type");
        }
    }

    public void writeToFile(String path) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(this.document);
        StreamResult streamResult = new StreamResult(path);

        transformer.transform(domSource, streamResult);
    }

    private class LatestAddedParentsList {
        private List<Element> latestParents = new ArrayList<>();

        public void addNewParent(Element parent) {
            latestParents.removeIf(e -> e.getTagName().equals(parent.getTagName()));
            latestParents.add(0, parent);
        }

        public Element getLatestElementOfType(List<String> types) {
            return latestParents.stream()
                    .filter(e -> types.contains(e.getTagName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Could not find any parents of type %s", types)));


        }
    }
}
