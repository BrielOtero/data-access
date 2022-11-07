import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise14 extends DefaultHandler {
	int depth = 0;

	ArrayList<String> depthList = new ArrayList<String>(List.of("filmoteca", "pelicula", "director"));
	// ArrayList<String> noIdent = new ArrayList<String>(List.of("titulo", "director", "nombre", "apellido"));

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Comienza a leer");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		for (int i = 0; i < depth; i++) {
			System.out.print("  ");
		}

		System.out.print("<" + qName + ">");

		if (depthList.contains(qName)) {
			System.out.println();
			depth++;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		System.out.print(new String(ch, start, length).trim());
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);

		if (depthList.contains(qName)) {
			depth--;
		}

		if (depthList.contains(qName)) {
			for (int i = 0; i < depth; i++) {
				System.out.print("  ");
			}
		}

		System.out.println("</" + qName + ">");

	}

}
