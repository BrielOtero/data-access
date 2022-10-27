import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Exercise13 {

	public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		ParserSAX parserSax = new ParserSAX(); // ParserSAX es la clase que se deberá
		parser.parse(entradaXML, parserSax);
	}

	public static void main(String[] args) {
		try {
			getSax("peliculas.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
