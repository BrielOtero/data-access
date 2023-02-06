package exercises;

import java.util.ArrayList;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/people")
public class People {
	private static ArrayList<Person> people = new ArrayList<Person>();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Person add() {
		Person p = new Person(0, "Test", false, null);
		people.add(p);
		return p;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void save(Person person) {
		people.add(person);
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public ArrayList<Person> list() {
		return people;
	}

	@GET
	@Path("/{name}")
	public void show(@PathParam("name") String name) {
		people.forEach(x -> {
			if (x.getName().equals(name)) {
				System.out.println("Id: " + x.getId());
				System.out.println("Name: " + x.getName());
				System.out.println("Sex: " + x.getSex());
				String married = x.isMarried() ? "Yes" : "No";
				System.out.println("Married: " + married);
			}
		});
	}

	@GET
	@Path("/search")
	public void show2(@QueryParam("query") String query){
		System.out.println(query);
		people.forEach(x -> {
			if (x.getName().toLowerCase().contains(query)) {
				System.out.println("Id: " + x.getId());
				System.out.println("Name: " + x.getName());
				System.out.println("Sex: " + x.getSex());
				String married = x.isMarried() ? "Yes" : "No";
				System.out.println("Married: " + married);
			}
		});
	}


}
