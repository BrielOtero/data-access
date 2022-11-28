import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Main13_17 extends DefaultHandler{

	public void getSax(String entradaXML,DefaultHandler claseHandler) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(entradaXML, claseHandler); // implementar y que hereda de DafaultHandler
	}
	public static void main(String[] args) {
		Main13_17 m = new Main13_17();
		
		try {
			// m.getSax("peliculas.xml",new Exercise14());
			m.getSax("peliculas-mod.xml",new Prueba());

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
