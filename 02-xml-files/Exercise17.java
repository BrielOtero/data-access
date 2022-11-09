import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise17 extends DefaultHandler {
	ArrayList<String> generos = new ArrayList<>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

		if (qName.equals("pelicula")) {

			String genero = attributes.getValue("genero");
			if (!generos.contains(genero)) {
				System.out.println(genero);
				generos.add(genero);
			}
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println(generos.size());
	}

}
