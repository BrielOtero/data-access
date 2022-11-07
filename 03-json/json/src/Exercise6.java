import javax.json.JsonObject;
import javax.json.JsonValue;

public class Exercise6 {

	public static String getCoordsFromCity(String city) {
		// try {
			Exercise1 exercise1 = new Exercise1();
			JsonValue json = exercise1.weather(city);

			JsonObject jsonObj =json.asJsonObject();
			System.out.println(jsonObj);

			return jsonObj.getJsonNumber("lat").doubleValue() +","+ jsonObj.getJsonNumber("lon").doubleValue();

		// } catch (Exception e) {
		// 	return "City not found";
		// }
	}

	public static void main(String[] args) {
		System.out.println(getCoordsFromCity("Vigo"));
		
	}
}
