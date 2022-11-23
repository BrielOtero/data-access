
abstract class Componente {
    private static int id = 0;

    public static int getId() {
        return id;
    }

    public static void incrementId() {
        Componente.id++;
    }
}
