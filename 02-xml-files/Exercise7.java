import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Exercise7 {
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

	private static void addAttrib(Document doc, String title, String attrib, String attribValue) {
		NodeList titles;
		NamedNodeMap titlesAttrib;

		try {
			titles = doc.getElementsByTagName("titulo");
			for (int i = 0; i < titles.getLength(); i++) {

				if (titles.item(i).getFirstChild().getNodeValue().equals(title)) {
					Element padre = (Element) titles.item(i).getParentNode();

					if (!(padre.hasAttribute(attrib))) {
						padre.setAttribute(attrib, attribValue);
					}

				}
			}

		} catch (DOMException e) {
			System.out.println("ERROR with addAttrib");
		}

		try {
			save(doc, "peliculas-add.xml");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException
				| FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void remAttrib(Document doc, String title, String attrib) {
		NodeList titles;
		try {
			titles = doc.getElementsByTagName("titulo");
			for (int i = 0; i < titles.getLength(); i++) {
				Element padre = (Element) titles.item(i).getParentNode();

				if (titles.item(i).getFirstChild().getNodeValue().equals(title)) {
					padre.removeAttribute(attrib);
				}
			}

		} catch (DOMException e) {
			System.out.println("ERROR with addAttrib");
		}

		try {
			save(doc, "peliculas-rem.xml");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException
				| FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Document doc1 = createTree("peliculas.xml");
		addAttrib(doc1, "Dune", "duracion", "30");
		Document doc2 = createTree("peliculas.xml");
		remAttrib(doc2, "Dune", "genero");

	}
}
