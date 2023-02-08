package exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Path("/")
public class AthleteService {
	private AthleteConnector ac = new AthleteConnector();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	private Response all() {
		return ac.select("SELECT * FROM "+ac.bd);
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
