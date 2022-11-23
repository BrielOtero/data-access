import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int menu;
        boolean correct = true;
        Manager m = new Manager("C:\\Users\\ID\\Downloads\\ejercicio10.txt");

        do {
            System.out.println("Elige una opcion:");
            System.out.println("1- Añadir Persona");
            System.out.println("2- Añadir Departamento");
            System.out.println("3- Ver Personas y Departamentos");
            System.out.println("4- Borrar Persona");
            System.out.println("5- Borrar Departamento");
            System.out.println("6- Salir");
            do {
                if (!correct) {
                    System.out.println("Inserta un valor valido");
                }
                menu = -1;
                correct = true;

                try {
                    menu = sc.nextInt();
                    sc.nextLine();
                    if (menu > 6 || menu < 1) {
                        correct = false;
                    }
                } catch (Exception e) {
                    correct = false;
                }

            } while (!correct);

            switch (menu) {
                case 1:
                    System.out.println("Inserta el nombre");
                    String nombrePersona = sc.nextLine();
                    m.añadirObjeto(new Persona(nombrePersona));
                    break;
                case 2:
                    System.out.println("Inserta el nombre");
                    String nombreDepartamento = sc.nextLine();
                    m.añadirObjeto(new Departamento(nombreDepartamento));
                    break;
                case 3:
                    m.leerObjetos(true, true);
                    break;
                case 4:
                    m.borrar(false);
                    break;
                case 5:
                    m.borrar(true);
                    break;
                case 6:
                    break;
            }

        } while (menu != 6);
    }
}
