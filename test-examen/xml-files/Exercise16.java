import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise16 extends DefaultHandler {
	private String movieTitle = "";
	private ArrayList<String> moviesTitles = new ArrayList<>();
	boolean isElement = false;
	private int directors = 0;
	private int directorsToShow;

	public Exercise16(int directorsToShow) {
		this.directorsToShow = directorsToShow;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

		if (qName.equals("titulo")) {
			movieTitle = qName + ":";
			isElement = true;
		}

		if (qName.equals("director")) {
			directors++;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (isElement) {
			movieTitle += new String(ch, start, length);
			isElement = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);

		if (qName.equals("pelicula")) {
			if (directors >= directorsToShow) {
				moviesTitles.add(movieTitle);
			}
			
			directors = 0;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		for (String valueString : moviesTitles) {
			System.out.println(valueString);
		}
	}

}
