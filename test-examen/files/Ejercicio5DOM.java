import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio5DOM {
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

    public static void mostrarDirectores(Document doc, int numeroDirectores) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        NodeList elementosPeliculas;
        Node aux;
        Node pelicula;
        String titulo = "";

        for (int i = 0; i < peliculas.getLength(); i++) {
            pelicula = peliculas.item(i);
            elementosPeliculas = pelicula.getChildNodes();
            int contDirectors = 0;

            for (int j = 0; j < elementosPeliculas.getLength(); j++) {
                aux = elementosPeliculas.item(j);

                if (aux.getNodeType() == Node.ELEMENT_NODE && aux.getNodeName().equals("director")) {
                    contDirectors++;
                }

                if (aux.getNodeType() == Node.ELEMENT_NODE && aux.getNodeName().equals("titulo")) {
                    titulo = aux.getFirstChild().getNodeValue();
                }
            }
            if (contDirectors >= numeroDirectores) {
                System.out.println(titulo);
            }
        }
    }

    public static void main(String[] args) {
        Document doc = createTree("peliculas.xml");
        mostrarDirectores(doc, 0);

    }
}