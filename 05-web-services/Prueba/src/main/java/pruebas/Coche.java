package pruebas;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/coches")
public class Coche {
    static ArrayList<Car> coches = new ArrayList<Car>();
    @DefaultValue("valor por defecto") @QueryParam("valor") String text;
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces( MediaType.APPLICATION_JSON)
    public Response getCar(Car coche) {
        coches.add(coche); // Se añade el coche a la lista
        return Response.ok(coche).build(); // Se devuelve el coche
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Car> getXML() {
        Car c = new Car(); // Se crea un coche y se inicializan sus param.
        c.setMarca("Ford");
        c.setModelo("Focus");
        coches.add(c); // Se añade el coche a la lista
        return coches; // Se devuelve la lista de coches
    }
}
