
package exercise;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Application {
	
	

	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		File f = new File("C:\\Users\\Gabriel\\Downloads\\Students.dat");
	
		try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {

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
					break;
				case 2:
				read(dis);

					break;
				case 3:

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

	} catch (IOException e) {
		System.out.println("Read exception");
	}

	}

	private static void read(DataInputStream dis) {
		int code;
		String name;
		Double height;
		System.out.println("UWU");

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
	}
}
