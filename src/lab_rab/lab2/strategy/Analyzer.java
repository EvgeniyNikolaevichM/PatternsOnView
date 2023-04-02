package lab_rab.lab2.strategy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Analyzer {
    private Strategy strategy;

    public void setStrategy (Strategy strategy) {
        this.strategy=strategy;
    }
    public void documentParse () throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        this.strategy.analyze();
    }
}
    