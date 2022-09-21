package exercise;

import java.util.Scanner;

public class UI {
	
	public void menu(){
		Scanner sc = new Scanner(System.in);
		
		int menu;
		do {
			System.out.println("Choose an option: ");
			System.out.println();
			System.out.println("1. Add Person");
			System.out.println("2. Add Depart");
			System.out.println("3. See Person");
			System.out.println("4. See Depart");
			System.out.println("5. Delete Person");
			System.out.println("6. Delete Depart");
			System.out.println("7. Exit");
			System.out.print("--> ");
			menu=sc.nextInt();
			sc.nextLine();
			switch (menu){
			case 1:
			
				break;
			case 2:
			
				break;
			case 3:
			
				break;
			case 4:
			
				break;
			case 5:
			break;
			case 6:
				break;
				case 7:
				break;
			default:
				System.out.println();
				System.out.println("+----------------+");
				System.out.println("| Invalid option |");
				System.out.println("+----------------+");
				System.out.println();
				break;
			}
		} while (menu != 7);
		
	}
}
