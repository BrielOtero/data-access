import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Exercise9 {
    public static JsonValue getTrivia(){
        FunctionsJson f = new FunctionsJson();
        return= f.openJSON("https://opentdb.com/api.php?amount=20&category=18&difficulty=hard&type=multiple");
    }
    public static void trivia() {
        JsonValue json = getTrivia();

        JsonArray elementsOfJson = json.asJsonObject().getJsonArray("results");

        for (int i = 0; i < elementsOfJson.size(); i++) {
            System.out.println(elementsOfJson.get(i).asJsonObject().getString("question"));
            System.out.println("\t*" + elementsOfJson.get(i).asJsonObject().getString("correct_answer"));

            JsonArray incorrectAnswer = elementsOfJson.get(i).asJsonObject().getJsonArray("incorrect_answers");
            for (int j = 0; j < incorrectAnswer.size(); j++) {
                System.out.println("\t " + incorrectAnswer.getString(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        trivia();
    }
}
