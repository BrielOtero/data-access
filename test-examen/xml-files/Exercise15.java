import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise15 extends DefaultHandler {
	String text;
	String temporalText;
	ArrayList<String> validElements = new ArrayList<>(List.of("titulo", "nombre", "apellido", "pelicula"));
	boolean isTitle;
	boolean isValid;

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		text = "";
		temporalText = "";
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		String genero = attributes.getValue("genero");

		

		if (validElements.contains(qName)) {
			if(genero!=null ){
				temporalText = String.format("genero:%s\n", genero);
			}
			text += qName + ":";
			isValid = true;
			if(qName.equals("titulo")){
				isTitle=true;
			}
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);

		if (isValid) {
			text += new String(ch, start, length).trim() + "\n";
			isValid = false;
			
			if(isTitle){
				text+=temporalText;
				isTitle=false;
			}
		}
	}
@Override
public void endElement(String uri, String localName, String qName) throws SAXException {
	super.endElement(uri, localName, qName);
	if(qName.equals("pelicula")){
		text+="\n";
	}
}
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.print(text);
	}
}
