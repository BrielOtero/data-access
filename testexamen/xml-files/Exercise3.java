import java.security.KeyStore.Entry.Attribute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Exercise3 {
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

	public static void showDataMovies(Document doc) {
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

			moviesList = movie.getChildNodes();
			for (int j = 0; j < moviesList.getLength(); j++) {
				
				aux = moviesList.item(j);
				
				if (aux.getNodeType() == Node.ELEMENT_NODE) {

					if (aux.getNodeName().equals("titulo")) {

						System.out.println(aux.getNodeName() + " -> " +
								aux.getFirstChild().getTextContent());
					}

					if (aux.getNodeName().equals("director")) {

						directorList = aux.getChildNodes();

						for (int k = 0; k < directorList.getLength(); k++) {

							auxDirector = directorList.item(k);

							if (auxDirector.getNodeType() == Node.ELEMENT_NODE) {

								System.out.println(auxDirector.getNodeName() + " -> " + auxDirector.getTextContent());

							}
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) {

		showDataMovies(createTree("peliculas.xml"));

	}
}
