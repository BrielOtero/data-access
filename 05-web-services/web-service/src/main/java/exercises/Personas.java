package exercises;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("personas")
public class Personas {
	private static ArrayList<Persona >personas= new ArrayList<Persona>();

	@Consumes({ MediaType.APPLICATION_JSON })
	private void guardar(Persona persona){
			personas.add(persona);
	}
}
