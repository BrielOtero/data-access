import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Exercise6 {

    public static double[] getCoordsFromCity(String city) {
        double[] coords;

        try {
            Exercise1 exercise1 = new Exercise1();
            JsonValue json = exercise1.weather(city);

            JsonObject jsonCoords = json.asJsonObject().getJsonObject("coord");
            coords = new double[2];
            coords[0] = jsonCoords.getJsonNumber("lat").doubleValue();
            coords[1] = jsonCoords.getJsonNumber("lon").doubleValue();
            return coords;

        } catch (Exception e) {
            coords = null;
            return coords;
        }
    }

    public static void main(String[] args) {
        double[] coord = getCoordsFromCity("Vigo");
        if (coord != null) {
            System.out.println(coord[0] + "," + coord[1]);
        }

    }
}
