package exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Business {

	private File f;

	public Business(String path) {
		this.f = new File(path);
	}

	public void addDepart() {
		saveObject(new Depart(getName("Depart")));
	}

	public void addPerson() {
		saveObject(new Person(getName("Person")));

	}

	private String getName(String type) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert the name of the " + type);
		return sc.nextLine();
	}

	private void saveObject(Object obj) {

		try (FileOutputStream dos = new FileOutputStream(f,true);
				ObjectOutputStream out = f.length() == 0 ? new ObjectOutputStream(dos)
						: new AppendingObjectOutputStream(dos)) {

			out.writeObject(obj);

		} catch (Exception e) {
			System.out.println("Error writing object " + e.getMessage());
		}
	}

	private void readObject(Object obj){
		try (FileInputStream fin = new FileInputStream(f)) {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	class AppendingObjectOutputStream extends ObjectOutputStream {
		public AppendingObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}

		@Override
		protected void writeStreamHeader() throws IOException {
			reset();// do not write a header
		}
	}
}
