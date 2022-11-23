import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

class AppendingObjectOutputStream extends ObjectOutputStream {
	public AppendingObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		reset();
	}
}

class Persona implements Serializable{
	String nombre;
	String apellido;
	int edad;

	public Persona(String nombre, String apellido, int edad){
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
}

class Departamento implements Serializable{
	String nombre;
	int numRef;

	public Departamento(String nombre, int numRef){
		this.nombre = nombre;
		this.numRef = numRef;
	}
}

public class Ej10 {

	public static void addObjects(File read, File write){
		try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(read)); 
		ObjectOutputStream oOut = write.length() == 0 ? new ObjectOutputStream(new FileOutputStream(write)) : new AppendingObjectOutputStream(new FileOutputStream(write, true)) ) {

			while (true){
				oOut.writeObject(oIn.readObject());
			}

		} catch (EOFException e) {
			System.out.println("Fin del fichero.\n---");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showInfo(File r){
		try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(r))) {
			Object o;
			while (true){
				o = oIn.readObject();
				if (o instanceof Persona){
					Persona p = (Persona)o;
					System.out.printf("Nombre y apellido: %s %s\nEdad: %d\n", p.nombre, p.apellido, p.edad);
				} else {
					Departamento d = (Departamento)o;
					System.out.printf("Nombre: %s \nNúm referencia: %d\n", d.nombre, d.numRef);
				}
			}
		} catch (EOFException e) {
			System.out.println("Fin del fichero\n---");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void addObjects(File f, Object o) {
		try (ObjectOutputStream oOut = f.length() == 0 ? new ObjectOutputStream(new FileOutputStream(f)) : new AppendingObjectOutputStream(new FileOutputStream(f, true))){
			oOut.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteObjects(File read, File write, String nameToDelete){
		try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(read))) {
			Persona p;
			Departamento d;
			String name;
			while (true){
				Object o = oIn.readObject();
				if (o instanceof Persona){
					p = (Persona) o;
					name = p.nombre;
				} else {
					d = (Departamento) o;
					name = d.nombre;
				}
				if (!name.equals(nameToDelete)){
					addObjects(write, o);
				}
			}

		} catch (EOFException e) {
			System.out.println("Fin del fichero.\n---");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		File in = new File("C:\\Users\\ID\\Downloads\\ejercicio10.txt");
		Persona p = new Persona("Javier", "García", 25);
		Persona p2 = new Persona("Isabel", "Rosado", 23);
		Departamento d = new Departamento("RRHH", 12345);
		File out = new File("C:\\Users\\ID\\Downloads\\ejercicio10Out.txt");
		File newDelete = new File("C:\\Users\\ID\\Downloads\\ejercicio10OutDel.txt");
		addObjects(in, p);
		addObjects(in, p2);
		addObjects(in, d);
		addObjects(in, out);
		showInfo(in);
		System.out.println("---------");
		deleteObjects(in, newDelete, "Isabel");
		showInfo(newDelete);
	}
}