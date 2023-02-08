package exercises;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	private int id;
	private String name;
	private boolean married;
	private String sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Person() {
	}

	public Person(int id, String name, boolean married, String sex) {
		setId(id);
		setName(name);
		setMarried(married);
		setSex(sex);
	}

}