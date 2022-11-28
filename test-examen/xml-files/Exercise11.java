import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Exercise11 {
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

	public static void save(Document doc, String file) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, ClassCastException, FileNotFoundException {
		DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
		DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
		LSOutput output = ls.createLSOutput();
		output.setEncoding("UTF-8");

		output.setByteStream(new FileOutputStream(file));

		LSSerializer serializer = ls.createLSSerializer();

		serializer.setNewLine("\r\n");
		serializer.getDomConfig().setParameter("format-pretty-print", true);

		serializer.write(doc, output);
	}

	public static Node getNode(NodeList nodelist, String value) {

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE && node.getFirstChild().getNodeValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public static void deleteMovie(Document doc, String title) {
		NodeList titulos = doc.getElementsByTagName("titulo");
		Node movie=getNode(titulos, title);
		doc.getFirstChild().removeChild(movie.getParentNode());

		try {
			save(doc, "peliculas-ejercicio11.xml");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException
				| FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Document doc = createTree("peliculas.xml");
		deleteMovie(doc, "Dune");
	}
}
