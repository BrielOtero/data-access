import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
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

	private static void genres(Document doc){
		NodeList rootList = doc.getElementsByTagName("pelicula");
		Node movie;
		
		for (int i = 0; i < rootList.getLength(); i++) {
			movie = rootList.item(i);

			System.out.println();

			if (movie.hasAttributes()) {
				attributes = movie.getAttributes();
				for (int k = 0; k < attributes.getLength(); k++) {
					attribute = attributes.item(k);

					if (attribute.getNodeName().equals("genero")) {
						System.out.println(attribute.getNodeName() + " -> " + attribute.getNodeValue());
					}

				}
			}

	}

	public static void main(String[] args) {
		Document doc = createTree("peliculas.xml");
		genres(doc);
	}
	
}
