import java.security.KeyStore.Entry.Attribute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Exercise4 {
	public static Document createTree(String path) {
		Document doc = null;

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setIgnoringComments(true);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.parse(path);

		} catch (Exception e) {
			System.err.println("Error creating document tree");
		}

		return doc;

	}

	public static void show(Element root, String tag) {
		NodeList nodeList = root.getElementsByTagName(tag);

		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getTextContent().trim());
		}
	}

	public static void showDataMovies(Document doc) {
		NodeList rootList = doc.getElementsByTagName("pelicula");
		NodeList moviesList;
		NodeList directorList;
		NamedNodeMap attributes;
		Node attribute;
		Node movie;
		Node aux;
		Node auxDirector;
		Node name;
		Node surname;

		for (int i = 0; i < rootList.getLength(); i++) {
			movie = rootList.item(i);

			show((Element) movie,"titulo");
			show((Element) movie,"director");

			// System.out.println(movie.getNodeName());

			// if (movie.hasAttributes()) {
			// 	attributes = movie.getAttributes();
			// 	for (int k = 0; k < attributes.getLength(); k++) {
			// 		attribute = attributes.item(k);

			// 		if (attribute.getNodeName().equals("genero")) {
			// 			System.out.println(attribute.getNodeName() + " -> " + attribute.getNodeValue());
			// 		}

			// 	}
			// }

		}

	}

	public static void main(String[] args) {

		showDataMovies(createTree("peliculas.xml"));

	}
}
