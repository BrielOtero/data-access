import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercises {
    public static Connectors connectors = new Connectors();

    public static void ejercicio1(String cad) {
        ResultSet rs = connectors
                .executeQuery(
                        "SELECT altura, apellidos,aula,nombre,codigo FROM alumnos WHERE nombre LIKE '%" + cad + "%'");

        try {
            int cont = 0;
            while (rs.next()) {
                System.out.println("Row: " + cont + " " + rs.getString("Nombre"));
                cont++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void ejercicio2_Alumnos(String nombre, String apellidos, int altura, int aula) {

        int res = connectors.executeUpdate(
                String.format("INSERT INTO alumnos (nombre,apellidos,altura,aula) VALUES (\"%s\",\"%s\",%d,%d)", nombre,
                        apellidos, altura, aula));

        System.out.println(res == 1 ? "Alumno inserted successfully" : "Alumno not inserted");
    }

    public static void ejercicio2_Asignaturas(String nombre) {
        ResultSet result = connectors.executeQuery("SELECT cod FROM asignaturas ORDER BY cod desc LIMIT 1");

        try {
            result.next();
            int index = result.getInt("cod") + 1;
            int res = connectors.executeUpdate(
                    String.format("INSERT INTO asignaturas (cod,nombre) VALUES (%d,\"%s\")", index, nombre));

            System.out.println(res == 1 ? "Asignatura inserted successfully" : "Asignatura not inserted");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // ejercicio1("Larry");
        // ejercicio2_Alumnos("Gabriel", "Otero", 184, 20);
        ejercicio2_Asignaturas("Mates");
    }
}
