import javax.json.JsonValue;

public class Exercise3 {
	
	public static JsonValue weather(double lat, double lon, int cnt) {
		FunctionsJson f = new FunctionsJson();
		return f.openJSON("http://api.openweathermap.org/data/2.5/find?lat="+lat+"&lon="+lon+"&cnt="+cnt+"&APPID=8f8dccaf02657071004202f05c1fdce0");
	}

	public static void main(String[] args) {
		System.out.println(weather(42.2328,-8.7226,10));
	}
}
