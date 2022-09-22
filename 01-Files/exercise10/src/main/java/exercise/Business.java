package exercise;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Business {

	private File f;
	private int idDepart;
	private int idPerson;

	public Business(String path) {
		this.f = new File(path);
		idPerson=getLastIndex(true);
		idDepart=getLastIndex(false);
	}

	public void addDepart() {
		saveObject(new Depart(getName("Depart"),idDepart));
		idDepart++;
	}

	public void addPerson() {
		saveObject(new Person(getName("Person"),idPerson));
		idPerson++;
	}

	public void showAll() {
		readObjects();
	}

	public void showDepart() {
		readObjects(true);
	}

	public void showPerson() {
		readObjects(false);
	}

	private String getName(String type) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert the name of the " + type);
		return sc.nextLine();
	}

	private void saveObject(Object obj) {

		try (FileOutputStream dos = new FileOutputStream(f, true);
				ObjectOutputStream out = f.length() == 0 ? new ObjectOutputStream(dos)
						: new AppendingObjectOutputStream(dos)) {

			out.writeObject(obj);

		} catch (Exception e) {
			System.out.println("Error writing object ");
		}
	}

	private void readObjects() {
		try (FileInputStream fin = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fin)) {

			try {
				Depart readDepart;
				Person readPerson;
				Object readObject;

				while (true) {
					readObject = in.readObject();

					if (readObject instanceof Depart) {
						readDepart = (Depart) readObject;

						readDepart.showObject();

					} else if (readObject instanceof Person) {
						readPerson = (Person) readObject;

						readPerson.showObject();

					}

				}
			} catch (EOFException e) {
			}
			System.out.println("+--------------------------------------------");

		} catch (Exception e) {
			System.out.println("Error reading object ");
		}
	}

	private void readObjects(boolean isDepart) {
		try (FileInputStream fin = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fin)) {

			try {
				Depart readDepart;
				Person readPerson;
				Object readObject;

				while (true) {
					readObject = in.readObject();

					if (readObject instanceof Depart) {
						readDepart = (Depart) readObject;

						if(isDepart){
							readDepart.showObject();
						}

					} else if (readObject instanceof Person) {
						readPerson = (Person) readObject;

						if(!isDepart){
							readPerson.showObject();
						}
					}

				}
			} catch (EOFException e) {
			}
			System.out.println("+--------------------------------------------");

		} catch (Exception e) {
			System.out.println("Error reading object ");
		}
	}

	private int getLastIndex(boolean isDepart) {
		int lastIndex=-1;

		try (FileInputStream fin = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fin)) {

			try {
				Depart readDepart;
				Person readPerson;
				Object readObject;

				while (true) {
					readObject = in.readObject();

					if (readObject instanceof Depart) {
						readDepart = (Depart) readObject;

						if(isDepart){
						lastIndex=readDepart.getId();
						}

					} else if (readObject instanceof Person) {
						readPerson = (Person) readObject;

						if(!isDepart){
							lastIndex= readPerson.getId();
						}
					}

				}
			} catch (EOFException e) {
			}
			System.out.println("+--------------------------------------------");

		} catch (Exception e) {
			System.out.println("Error reading object ");
		}

		return lastIndex+1;
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
