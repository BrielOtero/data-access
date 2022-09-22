package exercise;

import java.io.Serializable;

public class Depart implements Serializable,IShowObjects{

	private String departName;
	private int id;

	public int getId() {
		return id;
	}

	public String getDepartName() {
		return departName;
	}

	public Depart(String departName,int id) {
		this.departName = departName;
		this.id = id;
	}

	@Override
	public void showObject() {
		System.out.println("+--------------------------------------------");
		System.out.println("|  Id: "+id);
		System.out.println("|  Depart Name: "+departName);
		
	}
	
	
}
