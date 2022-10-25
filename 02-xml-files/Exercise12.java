import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Exercise12 {
	public static Document createTree() {
		Document doc = null;
String[]
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setIgnoringComments(true);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();

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

	private static void createTree(Document doc,String[][]values){

		for (int i = 0; i < values.length; i++) {
			
		Element compañia = doc.createElement("compañia");
		Element empleado = doc.createElement("empleado");
		empleado.setAttribute("id", (i+1)+"");
		Element nombre = doc.createElement("nombre");
		Text nombreText = doc.createTextNode(values[i][0]);
		nombre.appendChild(nombreText);
		Element apellidos = doc.createElement("apellidos");
		Text apellidosText = doc.createTextNode(values[i][1]);
		apellidos.appendChild(apellidosText);
		Element apodo = doc.createElement("apodo");
		Text apodoText = doc.createTextNode(values[i][2]);
		apodo.appendChild(apodoText);
		Element salario = doc.createElement("salario");
		Text salarioText=doc.createTextNode(values[i][03]);
		salario.appendChild(salarioText);
		empleado.appendChild(nombre);
		empleado.appendChild(apellidos);
		empleado.appendChild(apodo);
		empleado.appendChild(salario);
		compañia.appendChild(empleado);
		doc.appendChild(compañia);

		
	}

	try {
		save(doc, "peliculas-ejercicio12.xml");
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException
			| FileNotFoundException e) {
		e.printStackTrace();
	}

	}

	public static void main(String[] args) {
		String[][] values = new String[1][4];
		values[0][0] = "Juan";
		values[0][1] = "López Pérez";
		values[0][2] = "Juanin";
		values[0][3] = "1000";

		Document doc =createTree();

		createTree(doc, values);
	}
	public class a extends ArrayList {
	
	}
}

