public class Student {
    Integer id = null;
    String nombre;
    String apellidos;
    Integer altura;
    Integer aula;

    public String getNombreSQL() {
        return this.nombre == null ? "null" : "'" + this.nombre + "'";
    }

    public String getApellidosSQL() {
        return this.apellidos == null ? "null" : "'" + this.apellidos + "'";
    }

    public Student(Integer id, String nombre, String apellidos, Integer altura, Integer aula) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.aula = aula;
    }

    public Student(String nombre, String apellidos, Integer altura, Integer aula) {
        this(null, nombre, apellidos, altura, aula);
    }

    public Student() {
        this(null, null, null, null, null);
    }

}
