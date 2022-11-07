import javax.json.JsonValue;

public class Exercise5 {
	public static String getNameFromCoords(double lat, double lon) {
		try {
			Exercise2 exercise2 = new Exercise2();
			JsonValue json = exercise2.weather(lat, lon);

			return json.asJsonObject().getString("name") + "";

		} catch (Exception e) {
			return "City not found";
		}
	}

	public static void main(String[] args) {
		System.out.println(getNameFromCoords(42.2328, -8.7226));
	}
}
