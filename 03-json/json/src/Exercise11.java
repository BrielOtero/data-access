import javax.json.JsonArray;
import javax.json.JsonValue;

public class Exercise11 {

    public static void placeInfo(String country) {
        Exercise10 exercise10 = new Exercise10();
        JsonValue json = exercise10.getEventos(country);
        JsonArray elements = json.asJsonObject().getJsonObject("_embedded").getJsonArray("events");

        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).asJsonObject().getString("name"));
            JsonArray venues = elements.get(i).asJsonObject().getJsonObject("_embedded").getJsonArray("venues");

            System.out.println("\tPlace: " + venues.get(0).asJsonObject().getString("name"));
            System.out.println("\tAddress: " + venues.get(0).asJsonObject().getJsonObject("address").getString("line1"));
        }
    }

    public static void eventInfo(String country){
        Exercise10 exercise10 = new Exercise10();
        JsonValue json = exercise10.getEventos(country);
        JsonArray elements = json.asJsonObject().getJsonObject("_embedded").getJsonArray("events");

        for (int i = 0; i < elements.size(); i++) {
                
            
            System.out.println(elements.get(i).asJsonObject().getString("name"));
            System.out.println("\tDate: "+elements.get(i).asJsonObject().getJsonObject("dates").getJsonObject("start").getString("localDate"));
            try {
                System.out.println("\tTime: "+elements.get(i).asJsonObject().getJsonObject("dates").getJsonObject("start").getString("localTime"));
            } catch (Exception e) {
                System.out.println("\tTime: No time available ");
            }
        }
    }

    public static void main(String[] args) {
        placeInfo("ES");
        eventInfo("ES");
    }
}
