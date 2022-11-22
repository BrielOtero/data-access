import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserSAX extends DefaultHandler {
	boolean esTitulo = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("Título"))
			this.esTitulo = true;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (esTitulo) {
			String titulo = new String(ch, start, length);
			System.out.println(titulo);
			esTitulo = false;
		}
	}
}
