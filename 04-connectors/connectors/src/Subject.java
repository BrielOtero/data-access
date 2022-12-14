public class Subject {
    Integer id;
    String nombre;

    public String getNombreSQL() {
        return this.nombre == null ? "null" : "'" + this.nombre + "'";
    }

    public Subject(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Subject(String nombre) {
        this(null, nombre);
    }

    public Subject() {
        this(null, null);
    }
}
