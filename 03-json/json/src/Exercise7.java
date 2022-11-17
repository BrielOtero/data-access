import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class Exercise7 {
    public static void getDataFromCity(String city) {

         try {
            Exercise1 exercise1 = new Exercise1();
            JsonValue json = exercise1.weather(city);

            long time = json.asJsonObject().getJsonNumber("dt").longValue();
            double temp = json.asJsonObject().getJsonObject("main").getJsonNumber("temp").doubleValue();
            double humidity= json.asJsonObject().getJsonObject("main").getJsonNumber("humidity").doubleValue();
            double clouds= json.asJsonObject().getJsonObject("clouds").getJsonNumber("all").doubleValue();
            double windSpeed= json.asJsonObject().getJsonObject("wind").getJsonNumber("speed").doubleValue();
            String weather= json.asJsonObject().getJsonArray("weather").getJsonObject(0).getString("main");


            System.out.println("Date: "+unixTimeToString(time));
            System.out.println("Temperature: "+temp+"°");
            System.out.println("Humidity: "+humidity+"%");
            System.out.println("Clouds: "+clouds+"%");
            System.out.println("Wind Speed: "+windSpeed+"Km/h");
            System.out.println("Forecast: "+weather);
        } catch (Exception e) {
            System.out.println("City not found");
        }
    }

    public static String unixTimeToString(long unixTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
    }

    public static void main(String[] args) {
        getDataFromCity("Vigo");

    }
}
