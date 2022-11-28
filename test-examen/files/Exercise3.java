import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.text.Keymap;

public class Exercise3 {
    public static void count(String path, char character) {
        int counter=0;

        try (FileReader fr = new FileReader(path)) {
            int i;
            while ((i = fr.read()) != -1) {
                if ((char) i == character) {
                    counter++;
                }
            }

        } catch (FileNotFoundException f) {

        } catch (IOException i) {

        } catch (Exception e) {

        }

        System.out.println(counter);

    }

    public static void main(String[] args) {
        count("C:\\Users\\ID\\Downloads\\Ficheros2.txt", 'e');
    }
}