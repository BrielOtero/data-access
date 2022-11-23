import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Prueba extends DefaultHandler {
    boolean isDirector = false;
    String element;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("director")) {
            isDirector = true;
        }
        this.element = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (element.equals("nombre") && !isDirector) {
            System.out.println(new String(ch, start, length));
            this.element = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("director")) {
            isDirector = false;
        }
    }

    public static void getSax(String entradaXML, DefaultHandler claseHandler)
            throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(entradaXML, claseHandler); // implementar y que hereda de DafaultHandler
    }

    public static void main(String[] args) {
        try {
            getSax("peliculas-mod.xml", new Prueba());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}