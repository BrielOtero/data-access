import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Exercise6 {

	static void splitByChars(File in, int contChar) {

		try (FileReader fr = new FileReader(in)) {

			int i;
			int fileIndex = 0;
			// char[] buffer = new char[contChar];

			for (int j = 0; j <= contChar; j++) {

				while ((i = fr.read()) != -1) {

					try (FileWriter fw = new FileWriter(
							new File(in.getPath().replace(in.getName(), "") + in.getName().substring(0, in.getName().indexOf(".")) + "-" + fileIndex + ".txt"))) {

						fw.append((char) i);
					} catch (Exception e) {
					}
				}

				fileIndex++;
			}
		} catch (

		Exception e) {
		}

	}

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Gabriel\\Downloads\\Fich.txt");
		splitByChars(file, 5);
	}
}
