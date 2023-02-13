package exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Format;
import java.util.HashMap;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
		return ac.insert(query);
	}

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
