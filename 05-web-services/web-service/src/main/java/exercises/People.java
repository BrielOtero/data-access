package exercises;

import java.util.ArrayList;
import java.util.Arrays;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/people")
public class People {
	private static ArrayList<Person> people = new ArrayList<Person>();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response add() {
		Person per = new Person(0, "Test", false, null);
		people.add(per);
		return Response.ok(per).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void save(Person person) {
		people.add(person);
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public Response list() {
		return Response.ok(people).build();
	}

	@GET
	@Path("/{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response show(@PathParam("name") String name) {
		return Response.ok(people.stream().filter(p -> p.getName().equals(name)).toArray()).build();
	}

	@GET
	@Path("/search2")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response show2(@QueryParam("query") String query) {
		return Response.ok(people.stream().filter(p -> p.getName().toLowerCase().contains(query)).toArray()).build();
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED })
	public Response getCarText(@FormParam("id") int id, @FormParam("name") String name, @FormParam("sex") String sex,
			@FormParam("married") boolean married) {
		return Response.ok(new Person(id, name, married, sex)).build();
	}

	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(@PathParam("people") Person[] people) {
		for (int i = 0; i < people.length; i++) {
			People.people.add(people[i]);
		}
	}

	@DELETE
	@Path("/del/{id}")
	public void del(@PathParam("id") int id) {
		people.removeIf(p -> p.getId() == id);
	}

	@GET
	@Path("/search3")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response show3(@DefaultValue("es") @QueryParam("query") String query) {
		return Response.ok(people.stream().filter(p -> p.getName().toLowerCase().contains(query)).toArray()).build();
	}

	@GET
	@Path("/searchId/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response getPerson(@PathParam("id") int id) {
		Object[] o = people.stream().filter(p -> p.getId() == id).toArray();
		return Response.ok(Arrays.copyOf(o, o.length, Person[].class)).build();
	}

	/*
	 * Ejercicio 11 lo que tenemos que hacer es a las propiedades de person
	 * cambiar los xml element
	 * @XmlElement(name = "nuevaMarca")
	 */

}
