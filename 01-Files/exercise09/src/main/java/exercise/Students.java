package exercise;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Students {

	ArrayList<Student> students = null;
	File f;

	public Students(String path) {
		File f = new File(path);
		this.f = f;
		students = new ArrayList<>();
		students = read();
	}

	private Student askValuesStudent() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert name");
		String name = sc.nextLine();
		System.out.println("Insert height");
		double height = sc.nextDouble();

		return new Student(0, name, height);
	}

	public void showStudents() {
		this.students = read();

		for (int i = 0; i < students.size(); i++) {
			System.out.println(String.format("%4d.- %10s %f ", students.get(i).getCode(), students.get(i).getName(),
					students.get(i).getHeight()));

		}

	}

	public void insertStudent() {

		Student s = askValuesStudent();

		if (!students.contains(s)) {
			s.setCode(students.size());
			students.add(s);
			save(s);
		}
	}

	public void save(Student student) {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, true))) {

			dos.writeInt(student.getCode());
			dos.writeUTF(student.getName());
			dos.writeUTF(String.format("%f", student.getHeight()));

			System.out.println("Insert");
			System.out.println(student.getCode());
			System.out.println(student.getName());
			System.out.println(String.format("%f", student.getHeight()));

		} catch (Exception e) {
			System.out.println("Save Exception");
		}
	}

	public void save(Student[] students) {

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, false))) {

			for (int i = 0; i < students.length; i++) {
				dos.writeInt(students[i].getCode());
				dos.writeUTF(students[i].getName());
				dos.writeDouble(students[i].getHeight());
			}

		} catch (Exception e) {
			System.out.println("Save Exception");
		}

	}

	public ArrayList<Student> read() {
		ArrayList<Student> readStudents = new ArrayList<>();
		Student readStudent;
		int code;
		String name = "";
		Double height;

		try (DataInputStream dis = new DataInputStream(new FileInputStream(this.f))) {

			try {
				while (true) {
					code = dis.readInt();
					name = dis.readUTF();
					height = Double.parseDouble(dis.readUTF().replace(",", "."));

					// System.out.println(code);
					// System.out.println(name);
					// System.out.println(height);

					if (!name.equals("")) {
						readStudent = new Student(code, name, height);
						readStudents.add(readStudent);
					}
				}

			} catch (EOFException ex) {
				System.out.println();
				System.out.println("There aren't any student");
				System.out.println();
				return readStudents;
			}
		} catch (IOException e) {
			System.out.println("Read exception");
		}
		return readStudents;
	}

}
