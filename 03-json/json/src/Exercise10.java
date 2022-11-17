import javax.json.JsonArray;
import javax.json.JsonValue;

public class Exercise10 {
    public static JsonValue getEventos(String country){
        FunctionsJson f = new FunctionsJson();
        return f.openJSON("https://app.ticketmaster.com/discovery/v2/events.json?countryCode="+country+"&apikey=AMXR5Rf8zlr7oGucsebGKvDCLOQmGUGE");
    }

    public static void eventos(String country) {
        JsonValue json = getEventos(country);

        JsonArray elements = json.asJsonObject().getJsonObject("_embedded").getJsonArray("events");
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).asJsonObject().getString("name"));
        }
    }

    public static void main(String[] args) {
        eventos("ES");
    }
}
