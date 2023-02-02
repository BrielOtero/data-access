package exercises;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/personas")
public class Personas {
	private static ArrayList<Persona> personas = new ArrayList<Persona>();

	@Consumes({ MediaType.APPLICATION_JSON })
	private void guardar(Persona persona) {
		personas.add(persona);
	}

	@Produces({MediaType.TEXT_XML})
	private ArrayList<Persona> listar() {
		return personas;
	}
	
	@Path("{nombre}")
	
	private void ver(@PathParam("nombre") String nombre) {

		personas.forEach(x -> {
			if (x.getNombre().equals(nombre)) {
				System.out.println("Id: " + x.getId());
				System.out.println("Nombre: " + x.getNombre());
				System.out.println("Sexo: " + x.getSexo());
				String casado = x.isCasado() ? "Si" : "No";
				System.out.println("Casado: " + casado);
			}
		});
	}
}
