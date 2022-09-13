import java.io.File;

public class Exercise1 {

	public static void muestra(File f) {
		File[] files = f.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
			}
		}
		
		System.out.println();

		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
			}
		}

	}

	public static void main(String[] args) {

		File fichero = new File("C:\\Users\\Gabriel\\Downloads\\ficheros.txt");
		System.out.println("Nombre: " + fichero.getName());
		System.out.println("¿Existe?: " + fichero.exists());
		System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());

		muestra(fichero);

	}
}