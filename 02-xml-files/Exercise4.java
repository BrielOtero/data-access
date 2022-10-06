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
			// dbf.setIgnoringComments(true);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.parse(path);

		} catch (Exception e) {
			System.err.println("Error creating document tree");
		}

		return doc;

	}

	public static void showNode(Node root, int depth) {
		NodeList nodeList = root.getChildNodes();
		int aux;
		for (int i = 0; i < nodeList.getLength(); i++) {

			for (int j = 0; j < depth; j++) {
				System.out.print("\t");
			}
			System.out.println(nodeList.item(i).getNodeType() + " " + nodeList.item(i).getNodeName());
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				aux = depth;
				aux++;
				showNode(nodeList.item(i), aux);
			}
		}
	}

	public static void showDataMovies(Document doc) {
		showNode(doc, 0);
	}

	public static void main(String[] args) {
		long startTime;
		long estimatedTime;

		startTime = System.currentTimeMillis();

		showDataMovies(createTree("peliculas.xml"));

		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time: "+estimatedTime+" ms");

	}
}
