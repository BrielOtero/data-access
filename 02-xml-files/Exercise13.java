import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Exercise13 {
	public void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		ParserSAX parserSax = new ParserSAX(); // ParserSAX es la clase que se deberá
		parser.parse(entradaXML, parserSax); // implementar y que hereda de DafaultHandler
	}

}
