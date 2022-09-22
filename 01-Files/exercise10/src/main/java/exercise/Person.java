package exercise;

import java.io.Serializable;

public class Person implements Serializable,IShowObjects{

	public String name;
	private int id;


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Person(String name,int id) {
		this.name = name;
		this.id=id;
	}

	@Override
	public void showObject() {
		System.out.println("+--------------------------------------------");
		  System.out.println("|  Id: "+id);
		  System.out.println("|  Name: "+name);
		
	}
	
}
