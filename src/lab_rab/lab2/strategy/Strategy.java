package lab_rab.lab2.strategy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface Strategy {
    void analyze() throws ParserConfigurationException, XMLStreamException, SAXException, IOException;
}
