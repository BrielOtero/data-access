class Prueba {

    public void escribeFichero(File fichero, String cad) throws IOException {
        try (FileWriter fichOut = new FileWriter(fichero)) {
            for (int i = 0; i < cad.length(); i++) {
                fichOut.write(cad.charAt(i));
                fichOut.write(System.getProperty("line.separator"));
            }
        }
    }

    public void leeFichero(File fichero) throws IOException {
        try (FileReader fichIn = new FileReader(fichero)) {
            int i;
            while ((i = fichIn.read()) != -1) {
                System.out.print((char) i);
            }
        }
    }

    public void leerLineas(File fichero) throws FileNotFoundException {
        try (Scanner sc = new Scanner(fichero)) {
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        }

    }

    public void escribeFicheroPw(File fichero, String cad) throws IOException {
        try (PrintWriter pw = new PrintWriter(fichero)) {
            pw.println(cad);
        }
    }

    public void ficherosBinarios(File fileIn, File fileOut) throws IOException {
        try (FileInputStream in = new FileInputStream(fileIn);
                FileOutputStream out = new FileOutputStream(fileOut, true)) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
    }

    public void escribirPrimitivos(File fichero, ArrayList<Cliente> datos) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(fichero);
                DataOutputStream out = new DataOutputStream(fos)) {
            for (Cliente cl : datos) {
                out.writeUTF(cl.getNombre());
                out.writeInt(cl.getNumCompras());
                out.writeFloat(cl.getCredito());
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void leerPrimitivos(File fichero) throws IOException {
        try (FileInputStream fin = new FileInputStream(fichero);
                DataInputStream in = new DataInputStream(fin)) {
            try {
                while (true) {
                    System.out.printf("Nombre: %s Cantidad: %d Crédito: %f \n",
                            in.readUTF(), in.readInt(), in.readFloat());
                }
            } catch (EOFException e) {
                System.out.println("Fin de fichero");
            }
        }
    }

    public void escribirObjeto(File fichero, ArrayList<Cliente> datos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fichero);
                ObjectOutputStream out = new ObjectOutputStream(fos)) {
            for (Cliente cl : datos) {
                out.writeObject(cl);
            }
        }
    }

    public void leerObjetos(File fichero) throws IOException, ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream(fichero);
                ObjectInputStream in = new ObjectInputStream(fin)) {
            try {
                Cliente cl;
                while (true) {
                    cl = (Cliente) in.readObject();
                    System.out.printf("Nombre: %s Cantidad: %d Crédito: %f" + "\n", cl.getNombre(), cl.getNumCompras(),
                            cl.getCredito());
                }
            } catch (EOFException e) {
                System.out.println("Fin de fichero");
            }
        }
    }

    public void recorreDom(Document doc) {
        Node raiz, pelicula, nodoAux, atributo;
        NodeList peliculas, datos;
        NamedNodeMap atributos;

        // Se obtiene el primer nodo del documento, el nodo raíz
        raiz = doc.getFirstChild();

        // Se obtienen los hijos del nodo raíz como un objeto NodeList.
        System.out.printf("El nodo visualizado es: %s%n", raiz.getNodeName());

        peliculas = raiz.getChildNodes();

        // Se recorren los nodos hijos
        for (int i = 0; i < peliculas.getLength(); i++) {

            // Se obtienen la película i
            pelicula = peliculas.item(i);

            // Se procesa el nodo si es un nodo Element, los demás se ignoran
            if (pelicula.getNodeType() == Node.ELEMENT_NODE) {

                System.out.println("---------------------------------------");

                // Se obtienen los hijos de película
                datos = pelicula.getChildNodes();

                // Para cada hijo
                for (int j = 0; j < datos.getLength(); j++) {
                    nodoAux = datos.item(j);

                    // Solo se procesan los nodos Element hijos de película, en nuestro
                    // caso el título, el director y el estreno.
                    if (nodoAux.getNodeType() == Node.ELEMENT_NODE) {

                        // Se visualiza el nombre del elemento y su valor, recordar que el
                        // valor de un elemento reside en su primer hijo de tipo text.
                        System.out.println(nodoAux.getNodeName() + ":" + nodoAux.getFirstChild().getNodeValue());
                    }
                }

                // Si un elemento película tiene
                if (pelicula.hasAttributes()) {

                    // atributos se visualizan.
                    atributos = pelicula.getAttributes();
                    for (int k = 0; k < atributos.getLength(); k++) {
                        atributo = atributos.item(k);
                        System.out.printf("Atributo: %s con valor %s%n",
                                atributo.getNodeName(), atributo.getNodeValue());
                    }
                }
            }
        }
    }

    public void getTitulos(Document doc) {
        NodeList titulos = doc.getElementsByTagName("Título");
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.println(titulos.item(i).getFirstChild().getNodeValue());
        }
    }

    public void datosPelicula(Document doc, String pelicula) {
        Element padre;
        NodeList aux;
        NodeList titulos = doc.getElementsByTagName("Título");

        for (int i = 0; i < titulos.getLength(); i++) {
            if (titulos.item(i).getFirstChild().getNodeValue().equals(pelicula)) {
                padre = (Element) titulos.item(i).getParentNode();
                aux = padre.getElementsByTagName("Título");
                if (aux.getLength() > 0) {
                    System.out.println(aux.item(0).getFirstChild().getNodeValue());
                }
                aux = padre.getElementsByTagName("Director");
                if (aux.getLength() > 0) {
                    System.out.println(aux.item(0).getFirstChild().getNodeValue());
                }
                aux = padre.getElementsByTagName("Estreno");
                if (aux.getLength() > 0) {
                    System.out.println(aux.item(0).getFirstChild().getNodeValue());
                }
                break;
            }
        }
    }

    public class ParserSAX extends DefaultHandler {
        boolean esTitulo = false;

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            if (qName.equals("Título"))
                this.esTitulo = true;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (esTitulo) {
                String titulo = new String(ch, start, length);
                System.out.println(titulo);
                esTitulo = false;
            }
        }
    }

    public class ParserSAXB extends DefaultHandler {
        String qName = "";

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Comienzo del documento XML");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("Fin del documento XML");
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException {
            this.qName = qName;
            if (qName.equals("Película")) {
                for (int i = 0; i < attributes.getLength(); i++)
                    System.out.printf("Atributo %s con valor %s%n",
                            attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if (qName.equals("Película"))
                System.out.println("--------------------");
            this.qName = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String cad = new String(ch, start, length);
            if (this.qName.equals("Título"))
                System.out.println("Título: " + cad + "");
            else if (this.qName.equals("Director"))
                System.out.println("Direct: " + cad);
            else if (this.qName.equals("Estreno"))
                System.out.println("Año: " + cad);
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Aviso: " + e.getMessage());
        }

        @Override
        public void error(SAXParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        @Override
        public void fatalError(SAXParseException e) {
            System.out.println("Error fatal: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}