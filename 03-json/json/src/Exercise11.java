import javax.json.JsonValue;

public class Exercise11 {

    public static void placeInfo(String country ){
            Exercise10 exercise10 = new Exercise10();
            JsonValue json =exercise10.getEventos(country);
    }
 public static void main(String[] args) {
    placeInfo("ES");
 }   
}
