import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise3 {

	static int charCounter(File f, char character) {
		int counter = 0;

		if (f.isFile() && f.exists() && f.canRead()) {
			try (Scanner s = new Scanner(f)) {
				while (s.hasNextLine()) {
					String data = s.nextLine();

					for (int i = 0; i < data.length(); i++) {

						if (data.charAt(i) == character) {
							counter++;
						}

					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return counter;

	}

	public static void main(String[] args) {

		int a = charCounter(new File("C:\\Users\\ID\\Downloads\\Ficheros2.txt"), 'e');
		System.out.println(a);

	}
}
