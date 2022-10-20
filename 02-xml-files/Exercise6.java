import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Exercise6 {
	public static Document createTree(String path) {
		Document doc = null;

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// dbf.setIgnoringComments(true);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.parse(path);

		} catch (Exception e) {
			System.err.println("Error creating document tree");
		}

		return doc;

	}

	private static ArrayList<String>genres(Document doc){
		NodeList rootList = doc.getElementsByTagName("pelicula");
		Node movie;
		NamedNodeMap attributes;
		Node attribute;
		ArrayList<String> genres = new ArrayList<>();
		
		for (int i = 0; i < rootList.getLength(); i++) {
			movie = rootList.item(i);

			System.out.println();

			if (movie.hasAttributes()) {
				attributes = movie.getAttributes();
				for (int k = 0; k < attributes.getLength(); k++) {
					attribute = attributes.item(k);

					if (attribute.getNodeName().equals("genero")) {
						if(!genres.contains(attribute.getNodeValue())){
							genres.add(attribute.getNodeValue());
						}
					}

				}
			}
		}
		return genres;
	}

	public static void main(String[] args) {
		Document doc = createTree("peliculas.xml");
		ArrayList<String> genres;
		genres=genres(doc);

		System.out.println(genres.size());

		for (String string : genres) {
			System.out.println(string);
		}
	}
	
}
