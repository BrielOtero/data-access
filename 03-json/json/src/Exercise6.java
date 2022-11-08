import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Exercise6 {

	public static String getCoordsFromCity(String city) {

		try {
			Exercise1 exercise1 = new Exercise1();
			JsonValue json = exercise1.weather(city);

			JsonObject jsonCoords =json.asJsonObject().getJsonObject("coord");

			return jsonCoords.getJsonNumber("lat").doubleValue() +","+ jsonCoords.getJsonNumber("lon").doubleValue();

		} catch (Exception e) {
			return "City not found";
		}
	}

	public static void main(String[] args) {
		System.out.println(getCoordsFromCity("Vigo"));
		
	}
}
