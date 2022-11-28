import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Ej4 {

	public static void mostUsed(File f) {
		HashMap<Character, Integer> hashMap = new HashMap<>();
		String line = "";
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNext()){
				line = sc.nextLine();
				for (int i = 0; i < line.length(); i++) {
					hashMap.merge(line.charAt(i), 1, Integer::sum);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int maxValue = Collections.max(hashMap.values());

		System.out.println("Letra(s) más usada(s):");
		for (Character key : hashMap.keySet()) {
			if (hashMap.get(key) == maxValue ){
				System.out.println("Letra: "+ key +". Usos: "+maxValue+".");
			}
		}
	}

	public static void main(String[] args) {
		File f = new File("C:\\Users\\bdisa\\Desktop\\Prueba\\File.txt");
		mostUsed(f);
	}
}
