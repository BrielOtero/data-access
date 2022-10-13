import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Exercise5 {

	private static Document createTree(String path) {
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

	public static void showNode(Node root) {
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {

			if(nodeList.item(i).getNodeName().equals("titulo")){
				System.out.println(nodeList.item(i).getTextContent());
			}
			
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				
				showNode(nodeList.item(i));
			}
		}
	}

	private static int findTheNumbersOfElements(NodeList nl, String element) {
		int cont = 0;
		Node e;

		for (int i = 0; i < nl.getLength(); i++) {
			e = nl.item(i);
			if (e.getNodeType() == Node.ELEMENT_NODE && e.getNodeName().equals(element)) {
				cont++;
			}
		}

		return cont;
	}

	private static void showMovies(Document doc, int n) {
		NodeList nl = doc.getElementsByTagName("pelicula");
		int numOfMovies = 0;

		for (int i = 0; i < nl.getLength(); i++) {
			numOfMovies = findTheNumbersOfElements(nl.item(i).getChildNodes(), "director");
			if (numOfMovies > n) {
				showNode(nl.item(i));
			}

		}
	}

	public static void main(String[] args) {
		Document doc = createTree("peliculas.xml");
		showMovies(doc, 1);
	}

}
