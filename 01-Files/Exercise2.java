import java.io.File;

public class Exercise2 {

	static void list(File f) {

		if (f.exists() && f.canRead()) {

			File[] files = f.listFiles();

			for (int i = 0; i < files.length; i++) {

				if (files[i].isDirectory()) {
					System.out.println(files[i].getName());
					list(new File(files[i].getAbsolutePath()));
				} else {
					System.out.println(files[i].getName());
				}
			}
		}

	}

	public static void main(String[] args) {

		list(new File("C:\\Users\\Gabriel\\Downloads"));

	}

}
