package exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import static java.lang.String.*;

@Path("/athletes")
public class AthleteService {
	private AthleteConnector ac = new AthleteConnector();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response all() {
		return ac.select("SELECT * FROM athletes;");
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response search(@PathParam("id") int id) {
		return ac.select("SELECT * FROM athletes WHERE id=" + id);
	}

	@GET
	@Path("/sport/{sportName}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response sport(@PathParam("sportName") String sportName) {
		return ac.select(format("SELECT * FROM athletes WHERE sport like '%s'", sportName));
	}

	@GET
	@Path("/active")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response active() {
		return ac.select("SELECT * FROM athletes WHERE active is true");
	}

	@GET
	@Path("/retired")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retired() {
		return ac.select("SELECT * FROM athletes WHERE active is false");
	}

	@GET
	@Path("/male")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response male() {
		return ac.select("SELECT * FROM athletes WHERE genre like 'male'");
	}

	@GET
	@Path("/female")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response female() {
		return ac.select("SELECT * FROM athletes WHERE genre like 'female'");
	}

	@GET
	@Path("/xg")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response genre() {
		HashMap<String, Object> genreResponse = new HashMap<>();

		genreResponse.put("male", male().getEntity());
		genreResponse.put("female", female().getEntity());

		return Response.ok(genreResponse).build();
	}

	@GET
	@Path("/sport/{sportName}/active")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response activeForSport(@PathParam("sportName") String sportName) {
		return ac.select(format("SELECT * FROM athletes WHERE sport like '%s' and active is true", sportName));
	}

	@GET
	@Path("/sathletes")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response countAthletes() {
		return ac.count("SELECT count(DISTINCT sport) as count FROM athletes");
	}

	@GET
	@Path("/sports")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listSports() {
		return ac.selectSport("SELECT DISTINCT sport FROM athletes ORDER BY sport ASC");
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addAthlete(Athlete athlete) {
		String query = format("INSERT INTO athletes VALUES (0,'%s',%b,'%s','%s')",
				athlete.getName(), athlete.isActive(), athlete.getGenre(), athlete.getSport());
		System.out.println(query);
		return ac.update(query);
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response addAthleteForm(@FormParam("athlete") Athlete athlete) {
		String query = format("INSERT INTO athletes VALUES (0,'%s',%b,'%s','%s')",
				athlete.getName(), athlete.isActive(), athlete.getGenre(), athlete.getSport());
		System.out.println(query);
		return ac.update(query);
	}

	@POST
	@Path("/adds")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAthletes(ArrayList<Athlete> athletes) {
		Response response = Response.ok().build();
		try {
			athletes.forEach(athlete -> {
				addAthlete(athlete);
			});
		} catch (Exception e) {
			response = Response.serverError().build();
		}
		return response;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAthlete(Athlete athlete) {
		String query = format("UPDATE athletes SET name='%s',active=%b, genre='%s', sport='%s' WHERE id=%d",
				athlete.getName(), athlete.isActive(), athlete.getGenre(), athlete.getSport(), athlete.getId());
		System.out.println(query);
		return ac.update(query);
	}

	@DELETE
	@Path("/del/{id}")
	public Response deleteAthlete(@PathParam("id") int id) {
		String query = format("DELETE FROM athletes WHERE id=%d", id);
		System.out.println(query);
		return ac.update(query);
	}

	// @GET
	// @Path("/img/{id}/{num}}")
	// public Response deleteAthleteImage(@PathParam("id") int id, @PathParam("num") int num){

	// }

	@Provider
	public class DebugExceptionMapper
			implements ExceptionMapper<Exception> {
		@Override
		public Response toResponse(Exception exception) {
			exception.printStackTrace();
			return Response.serverError().entity(exception.getMessage()).build();
		}
	}

}
