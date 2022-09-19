package exercise;
public class Student {
	private int code;
	private String name;
	private double height;

	
	public Student(int code, String name, double height) {
		this.code = code;
		this.name = name;
		this.height = height;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = String.format("%.2f", name);
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	

}
