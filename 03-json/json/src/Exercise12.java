import javax.json.JsonArray;
import javax.json.JsonValue;

public class Exercise12 {
    public static void weatherInfo(String country) {
        Exercise10 exercise10 = new Exercise10();
        JsonValue json = exercise10.getEventos(country);
        JsonArray elements = json.asJsonObject().getJsonObject("_embedded").getJsonArray("events");

        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).asJsonObject().getString("name"));

            JsonArray venues = elements.get(i).asJsonObject().getJsonObject("_embedded").getJsonArray("venues");

            double lat =Double.parseDouble(venues.get(0).asJsonObject().getJsonObject("location").getString("longitude"));
            double lon =Double.parseDouble(venues.get(0).asJsonObject().getJsonObject("location").getString("latitude"));

            Exercise5 exercise5 = new Exercise5();
            Exercise7 exercise7 = new Exercise7();
            exercise7.getDataFromCity(exercise5.getNameFromCoords(lat, lon));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        weatherInfo("ES");
    }
}
