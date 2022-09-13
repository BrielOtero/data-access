import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercise6 {

	static void splitByChars(File in, int chars) {

		try (FileReader fr = new FileReader(in)) {

			int i;
			int fileIndex = 0;
			int contChar = 0;

			while ((i = fr.read()) != -1) {

				try (FileWriter fw = new FileWriter(new File(in.getPath().replace(".txt", "-" + fileIndex + ".txt")),
						true)) {

					fw.append((char) i);
					contChar++;

					if (contChar == chars) {
						fileIndex++;
						contChar = 0;
					}

				} catch (IOException e) {
				}
			}

		} catch (IOException e) {
		}

	}

	static void splitByLines(File in, int lines) {

		try (Scanner s = new Scanner(in)) {

			int fileIndex = 0;
			int contLines = 0;

			while (s.hasNextLine()) {

				try (FileWriter fw = new FileWriter(new File(in.getPath().replace(".txt", "-" + fileIndex + ".txt")),
						true)) {

					fw.append(s.nextLine() + "\n");
					contLines++;

					if (contLines == lines) {
						fileIndex++;
						contLines = 0;
					}

				} catch (IOException e) {
				}

			}

		} catch (IOException e) {
		}

	}

	static void mergeFiles(File[] in, File out) {

		for (int i = 0; i < in.length; i++) {

			try (Scanner s = new Scanner(in[i])) {
				while (s.hasNextLine()) {

					try (FileWriter fw = new FileWriter(out, true)) {
						fw.append(s.nextLine() + "\n");
					} catch (IOException e) {
					}
				}

			} catch (IOException e) {
			}

		}
	}

	public static void main(String[] args) {
		// File file = new File("C:\\Users\\Gabriel\\Downloads\\Fich.txt");
		File file = new File("C:\\Users\\ID\\Downloads\\Fich.txt");

		File file1 = new File("C:\\Users\\ID\\Downloads\\Fich-0.txt");
		File file2 = new File("C:\\Users\\ID\\Downloads\\Fich-1.txt");
		File file3 = new File("C:\\Users\\ID\\Downloads\\Fich-2.txt");

		File file4Out = new File("C:\\Users\\ID\\Downloads\\Fich-Out.txt");

		File[] files = { file1, file2, file3 };

		splitByChars(file, 1);
		splitByLines(file, 2);
		mergeFiles(files, file4Out);
	}
}
