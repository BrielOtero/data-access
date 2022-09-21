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
		f = new File(path);
	}

	private void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);

		Student student;
		int indexMod;
		int indexRem;
		int maxIndex;
		boolean error = false;

		int menu;
		do {
			System.out.println();
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
			System.out.println();

			switch (menu) {
				case 1:
					clear();

					student = askValuesStudent();
					writeNewStudent(student);

					break;
				case 2:
					clear();

					showStudents();

					break;
				case 3:

					error = false;

					do {
						clear();

						if (error) {
							System.out.println();
							System.out.println("This student don't exist, Please insert a valid student");
							System.out.println();
						}

						showStudents();
						maxIndex = getLastIndex() - 1;

						System.out.println("Select the code for the student to modify: ");
						indexMod = sc.nextInt();

						if (indexMod > maxIndex) {
							error = true;
						} else {
							error = false;
						}

					} while (indexMod > maxIndex);

					System.out.println();
					System.out.println("Insert the new Values");
					System.out.println();
					modifyStudent(indexMod, askValuesStudent());

					break;
				case 4:

					error = false;

					maxIndex = getLastIndex() - 1;

					do {
						clear();
						if (error) {
							System.out.println();
							System.out.println("This student don't exist, Please insert a valid student");
							System.out.println();
						}

						showStudents();

						System.out.println("Select the code for the student to remove: ");
						indexRem = sc.nextInt();

						if (indexRem > maxIndex) {
							error = true;
						} else {
							error = false;
						}

					} while (indexRem > maxIndex);
					removeStudent(indexRem);

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
		sc.close();
	}

	private boolean writeNewStudent(Student student) {

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(f, true))) {

			dos.writeInt(getLastIndex());
			dos.writeUTF(student.getName());
			dos.writeUTF(String.format("%.2f", student.getHeight()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void showStudents() {
		int code;
		String name;
		Double height;

		try (DataInputStream dis = new DataInputStream(new FileInputStream(this.f))) {

			try {
				while (true) {
					code = dis.readInt();
					name = dis.readUTF();
					height = Double.parseDouble(dis.readUTF().replace(",", "."));
					System.out.println(String.format("%4d.- %10s %.2f ", code, name, height));
				}

			} catch (EOFException ex) {
			}
	
		} catch (Exception e) {
		}
	}

	private void modifyStudent(int codeStudent, Student newStudent) {
		int code;
		String name;
		String height;
		File temp = new File(this.f.getParent() + "\\temp.dat");
		String fileNameWithPath = this.f.getAbsolutePath();

		try (DataInputStream dis = new DataInputStream(new FileInputStream(this.f));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(temp, true));) {

			while (true) {
				code = dis.readInt();
				name = dis.readUTF();
				height = dis.readUTF();

				if (code == codeStudent) {

					dos.writeInt(codeStudent);
					dos.writeUTF(newStudent.getName());
					dos.writeUTF(String.format("%.2f", newStudent.getHeight()));

				} else {
					dos.writeInt(code);
					dos.writeUTF(name);
					dos.writeUTF(height);
				}
			}

		} catch (EOFException ex) {

		} catch (IOException ex) {

		}

		f.delete();
		temp.renameTo(new File(fileNameWithPath));

		this.f = new File(fileNameWithPath);
	}

	private void removeStudent(int codeStudent) {
		int code;
		String name;
		String height;
		File temp = new File(this.f.getParent() + "\\temp.dat");
		String fileNameWithPath = this.f.getAbsolutePath();

		try (DataInputStream dis = new DataInputStream(new FileInputStream(this.f));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(temp, true));) {

			while (true) {
				code = dis.readInt();
				name = dis.readUTF();
				height = dis.readUTF();

				if (code != codeStudent) {

					if (code > codeStudent) {
						dos.writeInt(code - 1);
					} else {
						dos.writeInt(code);
					}

					dos.writeUTF(name);
					dos.writeUTF(height);
				}
			}

		} catch (EOFException ex) {

		} catch (IOException ex) {

		}

		f.delete();
		temp.renameTo(new File(fileNameWithPath));

		this.f = new File(fileNameWithPath);
	}

	private int getLastIndex() {
		int lastIndex = -1;
		String name;
		String height;

		try (DataInputStream dis = new DataInputStream(new FileInputStream(this.f))) {
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

		if (lastIndex == -1) {
			lastIndex = 0;
		} else {
			lastIndex++;
		}

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