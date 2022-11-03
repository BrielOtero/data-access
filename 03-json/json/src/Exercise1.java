import javax.json.JsonValue;

public class Exercise1 {

	public static JsonValue weather(String city){
		FunctionsJson f = new FunctionsJson();
		return f.openJSON("http://api.openweathermap.org/data/2.5/weather?q="+city+"&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");
	}

	public static void main(String[] args) {
		System.out.println(weather("vigo"));
	}
}
