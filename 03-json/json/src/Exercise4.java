import javax.json.JsonValue;

public class Exercise4 {

	public static String getIdFromCity(String city) {
		try {
			Exercise1 exercise1 = new Exercise1();
			JsonValue json = exercise1.weather(city);

			return json.asJsonObject().getInt("id") + "";

		} catch (Exception e) {
			return "City not found";
		}
	}

	public static void main(String[] args) {
		System.out.println(getIdFromCity("Vigo"));
		
	}

}
