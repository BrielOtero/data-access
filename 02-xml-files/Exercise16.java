import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise16 extends DefaultHandler {
	private String movie = "";
	private String filmoteca ="";
	private int directors = 0;
	private int directorsToShow;

	public Exercise16(int directorsToShow) {
		this.directorsToShow = directorsToShow;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("pelicula")) {
			movie = "";
			directors = 0;
			movie += "\n    " + qName + ":";

		}else movie +=   qName + ":";


		if (qName.equals("director")) {
			directors++;
		}

		

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		movie += new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		// movie += "</" + qName + ">";
		if (qName.equals("pelicula") && directors >= directorsToShow) {
			filmoteca+=movie;
		}
	}
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println(filmoteca);
	}

}
