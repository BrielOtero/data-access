package exercises;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Athlete {
	private int id;
	private String name;
	private String sport;
	private boolean active;
	private String genre;

	public Athlete() {
	}

	public Athlete(int id, String name, String sport, boolean active, String genre) {
		this.id = id;
		this.name = name;
		this.sport = sport;
		this.active = active;
		this.genre = genre;
	}

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

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
