package exercises;

/* En gris se ponen los imports que se usan en la versión 2.x de Jersey */
import jakarta.ws.rs.GET; // javax.ws.rs.GET;
import jakarta.ws.rs.Path; // javax.ws.rs.Path;
import jakarta.ws.rs.Produces; // javax.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType; // javax.ws.rs.core.MediaType;
// Establece la ruta del servicio: URL base + /hola

@Path("/hello")
public class Hello {
	// Se ejecuta este método si se pide un Accept de tipo TEXT_PLAIN
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String textHello() {
		return "Hello I'm Rest";
	}

	// Se ejecuta este método si se pide un Accept de tipo TEXT_HTML
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String htmlHello() {
		return "<html><title> Hello Rest</title><body>"
				+ "<h1> Hello Rest</h1>"
				+ "</body></html>";
	}

	// En los dos métodos siguientes el contenido se crea “a mano” y el valor
	// devuelto es un String genérico. En ejemplos siguientes veremos como
	// mejorar esto
	// Se ejecuta este método si se pide un Accept de tipo APPLICATION_XML
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String xmlHello() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<hello>Hello Rest. I'm XML</hello>";
	}

	// Se ejecuta este método si se pide un Accept de tipo APPLICATION_JSON
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String jsonHello() {
		return "{\"hello\":\"Hello Rest. I'm JSON\"}";
	}
}
