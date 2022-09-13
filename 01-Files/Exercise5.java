import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise5 {
	static ArrayList<String> showLines(File f, String value) {
		ArrayList<String> values= new ArrayList<>();

		if (f.exists() && f.isFile() && f.canRead()) {
			try (Scanner s = new Scanner(f)) {

				int lineCont=0;

				while (s.hasNextLine()) {
					lineCont++;

					String data = s.nextLine();
				

					if (data.contains(value)) {

						values.add(lineCont+"///"+data);

						// System.out.println(lineCont);
						// System.out.println(data);

					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		return values;
	}

	public static void main(String[] args) {
		
		ArrayList<String> values;

		values = showLines(new File("C:\\Users\\Gabriel\\Downloads\\Ficheros.txt"), "de la Mancha");


		for (String string : values) {
			
			String[] newValue = string.split("///");
			System.out.println("Line "+newValue[0]+" -> "+newValue[1]);
		}

	}
}
