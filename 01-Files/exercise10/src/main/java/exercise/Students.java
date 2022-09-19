package exercise;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Students {

	ArrayList<Student> students = null;
	File f;

	public Students(String path) {
		File f = new File(path);
		this.f = f;
		students = new ArrayList<>();
		read();
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

		for (Student student : students) {
			String.format("%4d.- %10s %f ", student.getCode(),student.getName(),student.getHeight())
			
		}
		
	}

	public void insertStudent() {

		Student s = askValuesStudent();

		if (!students.contains(s)) {
			s.setCode(students.size() - 1);
			students.add(s);
		}
	}

	public void save(Student student) {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, true))) {

			dos.writeInt(student.getCode());
			dos.writeUTF(student.getName());
			dos.writeDouble(student.getHeight());

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
		try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
			try {
				while (true) {
					readStudent = new Student(dis.readInt(), dis.readUTF(), dis.readDouble());
					readStudents.add(readStudent);
				}
			} catch (EOFException ex) {
				System.out.println("EOFException");
			}

		} catch (Exception e) {
			System.out.println("Read exception");
		}
		return readStudents;
	}

}
