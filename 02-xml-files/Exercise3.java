import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
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
		Node root;
		Node movie;
		Node aux;
		Node attrib;

		NodeList movies;
		NodeList data;

		NamedNodeMap attribs;

		root = doc.getFirstChild();

		System.out.printf("Node: %s\n", root.getNodeName());
		System.out.println();

		movies = root.getChildNodes();

		for (int i = 0; i < movies.getLength(); i++) {
			movie = movies.item(i);

			if (movie.getNodeType() == Node.ELEMENT_NODE) {
				data = movie.getChildNodes();

				for (int j = 0; j < data.getLength(); j++) {
					aux = data.item(i);

					if (aux.getNodeType() == Node.ELEMENT_NODE) {
						System.out.printf("%s : %s\n", aux.getNodeName(), aux.getFirstChild().getNodeValue());
					}
				}
				System.out.println();

				if (movie.hasAttributes()) {
					attribs = movie.getAttributes();

					for (int k = 0; k < attribs.getLength(); k++) {
						attrib = attribs.item(k);
						System.out.printf("Attrib: %s -> %s\n", attrib.getNodeName(), attrib.getNodeValue());
					}

					System.out.println();

				}
			}
		}
	}

	public static void main(String[] args) {

		showDataMovies(createTree("peliculas.xml"));

	}
}
