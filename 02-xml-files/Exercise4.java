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

	public static void showText(Element root, String tag) {
		NodeList nodeList = root.getElementsByTagName(tag);

		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getTextContent());
		}
	}

	public static void showNode(Element root, String tag) {
		NodeList nodeList = root.getElementsByTagName(tag);

		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getNodeName());
		}
	}
	
	public static void showData(Document doc) {
		NodeList rootList = doc.getElementsByTagName("pelicula");
		NodeList moviesList;
		NodeList directorList;
		NamedNodeMap attributes;
		Node attribute;
		Node movie;
		Node aux;
		Node auxDirector;

		for (int i = 0; i < rootList.getLength(); i++) {
			movie = rootList.item(i);

			System.out.println(movie.getTextContent());

			// if (movie.hasAttributes()) {
			// 	attributes = movie.getAttributes();
			// 	for (int k = 0; k < attributes.getLength(); k++) {
			// 		attribute = attributes.item(k);

			// 		if (attribute.getNodeName().equals("genero")) {
			// 			System.out.println(attribute.getNodeName() + " -> " + attribute.getNodeValue());
			// 		}

			// 	}
			// }

			moviesList = movie.getChildNodes();
			for (int j = 0; j < moviesList.getLength(); j++) {
				
				aux = moviesList.item(j);

				System.out.println(aux.getNodeName());
				
				if (aux.getNodeType() == Node.ELEMENT_NODE) {

					if (aux.getNodeName().equals("titulo")) {
						System.out.println(aux.getNodeName() );
						System.out.println(aux.getFirstChild().getTextContent());
					}

					if (aux.getNodeName().equals("director")) {

						directorList = aux.getChildNodes();

						for (int k = 0; k < directorList.getLength(); k++) {

							auxDirector = directorList.item(k);

							if (auxDirector.getNodeType() == Node.ELEMENT_NODE) {

								System.out.println(auxDirector.getNodeName() );
								System.out.println(auxDirector.getNodeValue());

							}
						}
					}
				}
			}
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
			System.out.println(movie.getParentNode().getNodeName());
			System.out.println(movie.getTextContent());

			// showText((Element)movie, "fimoteca");
			// showNode((Element)movie, "pelicula");
			// showText((Element) movie,"titulo");
			// showText((Element) movie,"director");

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

		showData(createTree("peliculas.xml"));
		// showDataMovies(createTree("peliculas.xml"));

	}
}
