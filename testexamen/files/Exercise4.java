import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise4 {

    public static void count(String path) {
        File f = new File(path);
        HashMap<Character, Integer> chars = new HashMap<>();

        if (f.exists() && f.canRead() && f.isFile()) {
            try (Scanner s = new Scanner(f)) {
                String line;

                while (s.hasNext()) {
                    line = s.nextLine();

                    for (int j = 0; j < line.length(); j++) {
                        if (line.charAt(j) != ' ') {
                            chars.merge(line.charAt(j), 1, Integer::sum);
                        }
                    }
                }

                int maxValue = Collections.max(chars.values());

                System.out.println("Caracter(es) más usado(s):");

                for (Character key : chars.keySet()) {
                    if (chars.get(key) == maxValue) {
                        System.out.println(key + " -> " + maxValue);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error IOException leyendo en count");
            } catch (Exception e) {
                System.out.println("Error Exception leyendo en count");
            }

        } else {
            System.out.println("El archivo no se puede leer");
        }
    }

    public static void main(String[] args) {
        count("C:\\Users\\ID\\Downloads\\File.txt");

    }
}
