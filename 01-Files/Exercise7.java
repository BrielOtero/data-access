import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise7 {

	private static void fileFunctions(String file, String operation) {

		File f = new File(file);

		if (f.exists() && f.isFile() && f.canRead()) {

			switch (operation) {
				case "n":

					int[] values = countLinesAndWords(f);
					System.out.println(String.format("Lines -> %d", values[0]));
					System.out.println(String.format("Words -> %d", values[1]));

					break;
				case "A":

					sortLinesSensitive(f, true, true);

					break;
				case "D":
					sortLinesSensitive(f, false, true);

					break;
				case "a":
					sortLinesSensitive(f, true, false);

					break;

				case "d":
					sortLinesSensitive(f, false, false);

					break;
			}
		}

	}

	private static void sortLinesSensitive(File f, boolean isAscent, boolean isSensitive) {
		ArrayList<String> sentences = new ArrayList<>();

		try (Scanner s = new Scanner(f)) {

			while (s.hasNextLine()) {
				sentences.add(s.nextLine() + "\n");
			}

		} catch (Exception e) {
		}

		if (isSensitive) {
			Collections.sort(sentences);
		} else {
			Collections.sort(sentences, String.CASE_INSENSITIVE_ORDER);
		}

		try (FileWriter fw = new FileWriter(new File(f.getPath().replace(".txt", "-copy.txt")))) {

			if (isAscent) {

				for (int i = 0; i < sentences.size(); i++) {
					fw.write(sentences.get(i));
				}
			} else {
				for (int i = sentences.size(); i >= 0; i--) {
					fw.write(sentences.get(i));
				}
			}

		} catch (Exception e) {
		}
	}

	private static int[] countLinesAndWords(File f) {
		int contLines = 0;
		int contWords = 0;
		int[] values = new int[2];

		try (Scanner s = new Scanner(f)) {
			while (s.hasNextLine()) {
				contLines++;

				String[] words = s.nextLine().split("\\s+");

				if (!words[0].equals("")) {
					contWords += words.length;
				}

			}

		} catch (Exception e) {
		}

		values[0] = contLines;
		values[1] = contWords;
		return values;
	}

	public static void main(String[] args) {

		fileFunctions("C:\\Users\\Gabriel\\Downloads\\Fich.txt", "D");

	}
}