import javax.json.JsonValue;

public class Exercise2 {

	public static JsonValue weather(double lat, double lon) {
		FunctionsJson f = new FunctionsJson();
		return f.openJSON("http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
				+ "&APPID=8f8dccaf02657071004202f05c1fdce0");
	}

	public static void main(String[] args) {
		System.out.println(weather(-8.7226, 42.2328));
	}
}
