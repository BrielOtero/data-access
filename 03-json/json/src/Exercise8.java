import javax.json.JsonValue;

public class Exercise8 {

    public static JsonValue weather(String city, int cnt) {
        FunctionsJson f = new FunctionsJson();
        Exercise6 e = new Exercise6();
        double[] coords = e.getCoordsFromCity(city);
        if (coords != null) {
            return f.openJSON("http://api.openweathermap.org/data/2.5/find?lat=" + coords[0] + "&lon=" + coords[1]+ "&cnt=" + cnt + "&APPID=8f8dccaf02657071004202f05c1fdce0");
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(weather("Vigo",5));
    }
}
