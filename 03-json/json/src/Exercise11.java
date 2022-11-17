import javax.json.JsonValue;

public class Exercise11 {

    public static void placeInfo(String country ){
            Exercise10 exercise10 = new Exercise10();
            JsonValue json =exercise10.getEventos(country);
           System.out.println(json);
    }
 public static void main(String[] args) {
    placeInfo("ES");
 }   
}
