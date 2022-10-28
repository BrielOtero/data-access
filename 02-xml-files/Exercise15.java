import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise15 extends DefaultHandler {

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		String genero = attributes.getValue("genero");

		if (genero != null) {
			System.out.printf("<%s  genero=\"%s\">", qName, genero);
		} else {
			System.out.print("<" + qName + ">");
		}
	
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		System.out.print(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		System.out.print("</" + qName + ">");

	}
}
