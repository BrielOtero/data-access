package exercises;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
public class PersonManagement {
	public static Person person;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response read() {
		return Response.ok(person).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void save(Person person) {
		PersonManagement.person = person;
	}

}
