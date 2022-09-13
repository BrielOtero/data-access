import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise4 {

	static ArrayList<Character> charMostUsed(File f) {
		ArrayList<Character> charChecked = new ArrayList<>();
		ArrayList<Integer> charCountChecked = new ArrayList<>();
		ArrayList<Character> maxChar = new ArrayList<>();

		if (f.isFile() && f.exists() && f.canRead()) {
			try (Scanner s = new Scanner(f)) {
				while (s.hasNextLine()) {
					String data = s.nextLine();

					for (int i = 0; i < data.length(); i++) {

						if(data.charAt(i)!=' '){	
							if (!charChecked.contains(data.charAt(i))) {
								
								charChecked.add(data.charAt(i));
								charCountChecked.add(charCounter(f, data.charAt(i)));
							}
						}
					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			int max = Collections.max(charCountChecked);

			for (int i = 0; i < charCountChecked.size(); i++) {
				if (charCountChecked.get(i) == max) {
					maxChar.add(charChecked.get(i));
				}
			}

		}

		return maxChar;

	}

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

		ArrayList<Character> c = new ArrayList<>();

		c = charMostUsed(new File("C:\\Users\\Gabriel\\Downloads\\Ficheros.txt"));

		for (Character character : c) {
			System.out.println(character);
		}

	}
}
