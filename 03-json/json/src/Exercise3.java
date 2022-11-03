import javax.json.JsonValue;

public class Exercise3 {
	public static JsonValue weather(double lat, double lon, int cnt) {
		FunctionsJson f = new FunctionsJson();
		return f.openJSON("http://api.openweathermap.org/data/2.5/find?lat="+lat+"&lon="+lon+"&cnt="+cnt+"&APPID=8f8dccaf02657071004202f05c1fdce0");
		// "http://api.openweathermap.org/data/2.5/find?lat=42.232819&lon=-8.72264&cnt=20&APPID=8f8dccaf02657071004202f05c1fdce0"
	}

	public static void main(String[] args) {
		System.out.println(weather(-8.7226, 42.2328,10));
	}
}
