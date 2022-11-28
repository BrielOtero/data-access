import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Exercise8 {
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

	public static void addMovie(Document doc, String title, String directorName,String directorSurname, String[][] attrib) {
		Element nodoPelicula = doc.createElement("pelicula");

		for (int i = 0; i < attrib.length; i++) {
			nodoPelicula.setAttribute(attrib[i][0], attrib[i][1]);
		}
		Element nodoTitulo = doc.createElement("titulo");
		Text textNodoTitulo = doc.createTextNode(title);
		nodoTitulo.appendChild(textNodoTitulo);
		nodoPelicula.appendChild(nodoTitulo);
		
		Element nodoDirector = doc.createElement("director");
		Element nodoNombre= doc.createElement("nombre");
		Element nodoApellido = doc.createElement("apellido");
		
		Text textNodoNombre= doc.createTextNode(directorName);
		Text textNodoApellido = doc.createTextNode(directorSurname);

		nodoNombre.appendChild(textNodoNombre);
		nodoApellido.appendChild(textNodoApellido);

		nodoDirector.appendChild(nodoNombre);
		nodoDirector.appendChild(nodoApellido);
		nodoPelicula.appendChild(nodoDirector);

		Node raiz = doc.getFirstChild();
		raiz.appendChild(nodoPelicula);

		try {
			save(doc, "peliculas-ejercicio8.xml");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException
				| FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String[][] attrib = new String[3][2];
		attrib[0][0] = "año";
		attrib[0][1] = "1987";
		attrib[1][0] = "genero";
		attrib[1][1] = "accion";
		attrib[2][0] = "idioma";
		attrib[2][1] = "en";

		Document doc = createTree("peliculas.xml");

		addMovie(doc, "Depredador", "John","Tiernan", attrib);
	}
}
