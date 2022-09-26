
package exercise;

import java.util.Scanner;

public class Application {

	private static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void main(String[] args) {

		Business b = new Business("C:\\Users\\Gabriel\\Downloads\\Objects.dat");

		Scanner sc = new Scanner(System.in);

		int menu;
		do {
			System.out.println("Choose an option: ");
			System.out.println();
			System.out.println("1. Add Person");
			System.out.println("2. Add Depart");
			System.out.println("3. See Person");
			System.out.println("4. See Depart");
			System.out.println("5. See Depart & Person");
			System.out.println("6. Delete Person");
			System.out.println("7. Delete Depart");
			System.out.println("8. Exit");
			System.out.print("--> ");
			menu = sc.nextInt();
			sc.nextLine();
			clear();
			switch (menu) {
				case 1:
					b.addPerson();
					break;
				case 2:
					b.addDepart();
					break;
				case 3:
					b.showPerson();
					break;
				case 4:
					b.showDepart();
					break;
				case 5:
					b.showAll();
					break;
				case 6:
					removePerson(b, sc);
					break;
				case 7:
					removeDepart(b, sc);
					break;
				case 8:
					break;
				default:
					System.out.println();
					System.out.println("+----------------+");
					System.out.println("| Invalid option |");
					System.out.println("+----------------+");
					System.out.println();
					break;
			}
		} while (menu != 8);

	}

	private static void removeDepart(Business b, Scanner sc) {
		int maxIndex = b.getLastIndex(true);
		int indexToRem = 0;
		boolean error = false;

		if (maxIndex != 0) {

			do {

				b.showDepart();

				if (error) {

					System.out.println("Please select a valid number");
				}

				error = false;

				System.out.println("Please select the id to delete");

				try {
					indexToRem = sc.nextInt();
					sc.nextLine();

				} catch (Exception e) {
					error = true;
					sc.nextLine();
				}

				if (indexToRem > maxIndex) {
					error = true;
				}

			} while (error);

			b.removeObject(indexToRem, true);
		} else {
			System.out.println("No Depart to show");
		}
	}

	private static void removePerson(Business b, Scanner sc) {
		int maxIndex = b.getLastIndex(true);
		int indexToRem = 0;
		boolean error = false;

		if (maxIndex != 0) {

			do {

				b.showPerson();

				if (error) {

					System.out.println("Please select a valid number");
				}

				error = false;

				System.out.println("Please select the id to delete");

				try {
					indexToRem = sc.nextInt();

				} catch (Exception e) {
					error = true;
					sc.nextLine();
				}

				if (indexToRem > maxIndex) {
					error = true;
				}

			} while (error);

			b.removeObject(indexToRem, false);

		} else {
			System.out.println("No Person to show");

		}
	}
}
