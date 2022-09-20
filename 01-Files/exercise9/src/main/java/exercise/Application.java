
package exercise;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// File f = new File("C:\\Users\\Gabriel\\Downloads\\Students.dat");
		File f = new File("C:\\Users\\ID\\Downloads\\Students.dat");

		Student student;

		

			int menu;
			do {
				System.out.println("Choose an option: ");
				System.out.println();
				System.out.println("1. Add Student");
				System.out.println("2. See Students");
				System.out.println("3. Change any student");
				System.out.println("4. Delete Student");
				System.out.println("5. Exit");
				System.out.print("--> ");
				menu = sc.nextInt();
				sc.nextLine();
				switch (menu) {
					case 1:

						student = askValuesStudent();
						writeNewStudent(f, student);

						break;
					case 2:
						showStudents(f);

						break;
					case 3:
						showStudents(f);

						System.out.println("Select the code for the student to remove: ");
						int indexRemove = sc.nextInt();



						break;
					case 4:

						break;
					case 5:
						break;
					default:
						System.out.println();
						System.out.println("+----------------+");
						System.out.println("| Invalid option |");
						System.out.println("+----------------+");
						System.out.println();
						break;
				}
			} while (menu != 5);

		

	}

	private static void writeNewStudent(File f, Student student) {

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, true))) {

			dos.writeInt(getLastIndex(f));
			dos.writeUTF(student.getName());
			dos.writeUTF(String.format("%.2f", student.getHeight()));

		} catch (Exception e) {
		}
	}

	private static void showStudents(File f) {
		int code;
		String name;
		Double height;

		try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {

			try {
				while (true) {
					code = dis.readInt();
					name = dis.readUTF();
					height = Double.parseDouble(dis.readUTF().replace(",", "."));
					System.out.println(String.format("%4d.- %10s %f ", code, name, height));
				}

			} catch (EOFException ex) {

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
		}
	}

	private static int getLastIndex(File f) {
		int lastIndex = -1;
		String name;
		String height;

		try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
			try {
				while (true) {
					lastIndex = dis.readInt();
					name = dis.readUTF();
					height = dis.readUTF();
				}

			} catch (EOFException ex) {

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
		}

		System.out.println("Before Last index: " + lastIndex);
		if (lastIndex == -1) {
			lastIndex = 0;
		} else {
			lastIndex++;
		}

		System.out.println("Last index: " + lastIndex);
		return lastIndex;
	}

	private static Student askValuesStudent() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert name");
		String name = sc.nextLine();
		System.out.println("Insert height");
		double height = sc.nextDouble();

		return new Student(0, name, height);
	}
}
