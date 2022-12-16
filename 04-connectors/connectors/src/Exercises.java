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

    public static void ejercicio2_Alumnos(Student student) {

        int res = connectors.executeUpdate(
                String.format("INSERT INTO alumnos (nombre,apellidos,altura,aula) VALUES (\"%s\",\"%s\",%d,%d)",
                        student.nombre,
                        student.apellidos, student.altura, student.aula));

        System.out.println(res == 1 ? "Alumno inserted successfully" : "Alumno not inserted");
    }

    public static void ejercicio2_Asignaturas(Subject subject) {
        ResultSet result = connectors.executeQuery("SELECT cod FROM asignaturas ORDER BY cod desc LIMIT 1");

        try {
            result.next();
            int index = result.getInt("cod") + 1;
            int res = connectors.executeUpdate(
                    String.format("INSERT INTO asignaturas (cod,nombre) VALUES (%d,\"%s\")", index, subject.nombre));

            System.out.println(res == 1 ? "Asignatura inserted successfully" : "Asignatura not inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio3_Alumnos(int cod) {

        int res = connectors.executeUpdate(String.format("DELETE FROM alumnos WHERE codigo=%d", cod));

        System.out.println(res == 1 ? "Alumno deleted successfully" : "Alumno not deleted");
    }

    public static void ejercicio3_Asignaturas(int cod) {
        int res = connectors.executeUpdate(String.format("DELETE FROM asignaturas WHERE cod=%d", cod));

        System.out.println(res == 1 ? "Asignatura deleted successfully" : "Asignatura not deleted");
    }

    public static void ejercicio4_Alumnos(Student student) {

        int res = connectors.executeUpdate(String.format(
                "UPDATE alumnos SET nombre=IFNULL(%s,nombre), apellidos=IFNULL(%s,apellidos), altura=IFNULL(%d,altura), aula=IFNULL(%d,aula) WHERE codigo=%d",
                student.getNombreSQL(), student.getApellidosSQL(), student.altura, student.aula, student.id));

        System.out.println(res == 1 ? "Alumno modified successfully" : "Alumno not modified");
    }

    public static void ejercicio4_Students(Subject subject) {
        int res = connectors.executeUpdate(String.format("UPDATE asignaturas SET nombre=IFNULL(%s,nombre) WHERE cod=%d",
                subject.getNombreSQL(), subject.id));

        System.out.println(res == 1 ? "Asignatura modified successfully" : "Asignatura not modified");
    }

    public static void ejercicio5_1_AulasConAlumnos() {
        ResultSet res = connectors
                .executeQuery("SELECT nombreAula FROM aulas WHERE numero IN (SELECT aula FROM alumnos)");

        try {
            while (res.next()) {
                System.out.println(res.getString("nombreAula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio5_2_AlumnosAsignaturasNotas() {
        ResultSet res = connectors.executeQuery(
                "SELECT alumnos.nombre, asignaturas.NOMBRE as asignaturas, notas.NOTA FROM notas JOIN asignaturas ON notas.asignatura=asignaturas.COD JOIN alumnos ON notas.alumno=alumnos.codigo WHERE nota>=5");

        try {
            while (res.next()) {
                System.out.println(
                        String.format("%-15s %-35s %-15d", res.getString("nombre"), res.getString("asignaturas"),
                                res.getInt("nota")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio5_3_AsignaturasSinAlumnos() {
        ResultSet res = connectors
                .executeQuery("SELECT nombre FROM asignaturas WHERE cod!=All(SELECT asignatura FROM notas)");

        try {
            while (res.next()) {
                System.out.println(res.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio6_1_WithOutPreparedStatement(int height, String pattern) {
        ResultSet res = connectors.executeQuery(
                "SELECT nombre FROM alumnos WHERE altura>" + height + " AND nombre LIKE '" + pattern + "'");

        try {
            while (res.next()) {
                System.out.println(res.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio6_2_WithPreparedStatement(Integer height, String pattern) {
        ResultSet res = connectors.executePreparedStatement(
                "SELECT nombre FROM alumnos WHERE altura>? AND nombre LIKE ?", new Object[] { height, pattern });

        try {
            while (res.next()) {
                System.out.println(res.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio8_AddTable(String table, String column, String dateType, String properties) {
        connectors
                .executeUpdate("ALTER TABLE " + table + " ADD " + column + " " + dateType + " " + properties);
    }

    public static void ejercicio9() {

    }

    public static void main(String[] args) {

        // Ejercicio 7
        int runs = 1;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < runs; i++) {

            // ejercicio1("Larry");
            // ejercicio2_Alumnos(new Student("Gabriel", "Otero", 184, 20));
            // ejercicio2_Asignaturas(new Subject("Mates"));
            // ejercicio3_Alumnos(11);
            // ejercicio3_Asignaturas(9);
            // ejercicio4_Alumnos(new Student(10, null, null, null, 21));
            // ejercicio4_Students(new Subject(9, "Lengua"));
            // ejercicio5_1_AulasConAlumnos();
            // ejercicio5_2_AlumnosAsignaturasNotas();
            // ejercicio5_3_AsignaturasSinAlumnos();
            // ejercicio6_1_WithOutPreparedStatement(180, "%hili%");
            // ejercicio6_2_WithPreparedStatement(180, "%hili%");
            // ejercicio8_AddTable("alumnos", "test", "varchar(20)", "NOT NULL");
            // ejerciocio9
        }

        // Ejercicio 7
        long resultTime = System.currentTimeMillis() - startTime;
        System.out.println("\nExecution time: " + resultTime + " ms");
    }

}
