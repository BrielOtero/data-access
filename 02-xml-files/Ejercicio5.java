import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Ejercicio5 {
	
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

	private static void showMovies(Document doc,int nDirectores){
		NodeList nl = doc.getElementsByTagName("pelicula");

		for (int i = 0; i < nl.getLength(); i++) {
			nl.item(i).getChildNodes();
		}
	}

	public static void main(String[] args) {
		showMovies(createTree("peliculas.xml"),1);
	}
	
}
