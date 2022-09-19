
package exercise;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Students s = new Students("C:\\Users\\Gabriel\\Downloads\\Students.dat");

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
					s.insertStudent();
					break;
				case 2:

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

	}
}
