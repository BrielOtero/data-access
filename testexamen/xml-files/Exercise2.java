import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Exercise2 {
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

	public static void showTitles(Document doc) {
		NodeList titles = doc.getElementsByTagName("titulo");

		for (int i = 0; i < titles.getLength(); i++) {
			System.out.printf("Titulo %40s\n",titles.item(i).getTextContent().trim());
		}
	}

	public static void main(String[] args) {

		showTitles(createTree("peliculas.xml"));

	}
}
