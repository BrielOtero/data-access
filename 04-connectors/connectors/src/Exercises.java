import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercises {
	public static Connectors connectors = new Connectors();

	public static void ejercicio1(String cad) {
		try {
			ResultSet rs = connectors
					.executeQuery(
							"SELECT altura, apellidos,aula,nombre,codigo FROM alumnos WHERE nombre LIKE '%" + cad
									+ "%'");
			int cont = 0;
			// rs.last();
			// int numeroFilas = rs.getRow();
			// rs.beforeFirst();

			while (rs.next()) {
				System.out.println("Row: " + cont + " " + rs.getString("Nombre"));
				cont++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ejercicio2_Alumnos(Student student) {
		try {

			int res = connectors.executeUpdate(
					String.format("INSERT INTO alumnos (nombre,apellidos,altura,aula) VALUES (\"%s\",\"%s\",%d,%d)",
							student.nombre,
							student.apellidos, student.altura, student.aula));

			System.out.println(res == 1 ? "Alumno inserted successfully" : "Alumno not inserted");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio2_Asignaturas(Subject subject) {
		try {
			ResultSet result = connectors.executeQuery("SELECT cod FROM asignaturas ORDER BY cod desc LIMIT 1");

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
		try {
			int res = connectors.executeUpdate(String.format("DELETE FROM alumnos WHERE codigo=%d", cod));

			System.out.println(res == 1 ? "Alumno deleted successfully" : "Alumno not deleted");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio3_Asignaturas(int cod) {
		try {
			int res = connectors.executeUpdate(String.format("DELETE FROM asignaturas WHERE cod=%d", cod));

			System.out.println(res == 1 ? "Asignatura deleted successfully" : "Asignatura not deleted");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio4_Alumnos(Student student) {
		try {
			int res = connectors.executeUpdate(String.format(
					"UPDATE alumnos SET nombre=IFNULL(%s,nombre), apellidos=IFNULL(%s,apellidos), altura=IFNULL(%d,altura), aula=IFNULL(%d,aula) WHERE codigo=%d",
					student.getNombreSQL(), student.getApellidosSQL(), student.altura, student.aula, student.id));

			System.out.println(res == 1 ? "Alumno modified successfully" : "Alumno not modified");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio4_Students(Subject subject) {
		try {
			int res = connectors
					.executeUpdate(String.format("UPDATE asignaturas SET nombre=IFNULL(%s,nombre) WHERE cod=%d",
							subject.getNombreSQL(), subject.id));

			System.out.println(res == 1 ? "Asignatura modified successfully" : "Asignatura not modified");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio5_1_AulasConAlumnos() {
		try {
			ResultSet res = connectors
					.executeQuery("SELECT nombreAula FROM aulas WHERE numero IN (SELECT aula FROM alumnos)");

			while (res.next()) {
				System.out.println(res.getString("nombreAula"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio5_2_AlumnosAsignaturasNotas() {
		try {
			ResultSet res = connectors.executeQuery(
					"SELECT alumnos.nombre, asignaturas.NOMBRE as asignaturas, notas.NOTA FROM notas JOIN asignaturas ON notas.asignatura=asignaturas.COD JOIN alumnos ON notas.alumno=alumnos.codigo WHERE nota>=5");

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
		try {
			ResultSet res = connectors
					.executeQuery("SELECT nombre FROM asignaturas WHERE cod!=All(SELECT asignatura FROM notas)");

			while (res.next()) {
				System.out.println(res.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio6_1_WithOutPreparedStatement(int height, String pattern) {
		try {
			ResultSet res = connectors.executeQuery(
					"SELECT nombre FROM alumnos WHERE altura>" + height + " AND nombre LIKE '" + pattern + "'");

			while (res.next()) {
				System.out.println(res.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio6_2_WithPreparedStatement(Integer height, String pattern) {
		try {
			ResultSet res = connectors.executePreparedStatement(
					"SELECT nombre FROM alumnos WHERE altura>? AND nombre LIKE ?", new Object[] { height, pattern });

			while (res.next()) {
				System.out.println(res.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio8_AddColumn(String table, String column, String dateType, String properties) {
		try {
			int res = connectors
					.executeUpdate("ALTER TABLE " + table + " ADD " + column + " " + dateType + " " + properties);

			System.out.println(res >= 0 ? "Column added" : "Column not added");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio9_A() {
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("URL Conexion: " + dbmd.getURL());
			System.out.println("Connected User: " + dbmd.getUserName());
			System.out.println("SGBD Name: " + dbmd.getDatabaseProductName());
			System.out.println("SGBD Version: " + dbmd.getDatabaseProductVersion());
			System.out.println("Reserved words: " + dbmd.getSQLKeywords());

		} catch (SQLException e) {
			System.out.println("Error getting data");
		}
	}

	public static void ejercicio9_B() {
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
			ResultSet res = dbmd.getCatalogs();
			while (res.next()) {
				System.out.println(res.getString("TABLE_CAT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio9_C(String database) {
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();

			ResultSet res = dbmd.getTables(database, null, null, null);

			System.out.println(String.format("%-15s | %-15s", "Name", "Type"));
			while (res.next()) {
				System.out.println(
						String.format("%-15s | %-15s", res.getString("TABLE_NAME"), res.getString("TABLE_TYPE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio9_D(String database) {
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
			String[] types = { "VIEW" };

			ResultSet res = dbmd.getTables(database, null, null, types);
			System.out.println(String.format("%-15s | %-15s", "Name", "Type"));
			while (res.next()) {
				System.out.println(
						String.format("%-15s | %-15s", res.getString("TABLE_NAME"), res.getString("TABLE_TYPE")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ejercicio9_E() {
		ejercicio9_C(null);
	}

	public static void ejercicio9_F(String database) {
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
			ResultSet res = dbmd.getProcedureColumns(database, null, null, null);
			System.out.println(String.format("%-15s", "Name"));

			while (res.next()) {
				System.out.println(
						String.format("%-15s", res.getString("PROCEDURE_NAME")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio9_G(String database) {
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
			ResultSet res = dbmd.getColumns(database, null, null, "a%");

			System.out
					.println(String.format("%-20s | %-15s | %-20s | %-20s | %-10s | %-10s | %-10s", "ORDINAL_POSITION",
							"DATABASE", "TABLE_NAME", "COLUMN_NAME", "TYPE_NAME", "COLUMN_SIZE", "NULLABLE"));

			while (res.next()) {
				System.out.println(String.format("%-20s | %-15s | %-20s | %-20s | %-10s | %-10s | %-10s",
						res.getInt("ORDINAL_POSITION"), database, res.getString("TABLE_NAME"),
						res.getString("COLUMN_NAME"),
						res.getString("TYPE_NAME"),
						res.getInt("COLUMN_SIZE"), res.getBoolean("NULLABLE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ejercicio9_H(String database) {
		DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
		try {
			ResultSet res1 = dbmd.getPrimaryKeys(database, null, null);
			ResultSet res2 = dbmd.getExportedKeys(database, null, null);

			System.out.println(String.format("%-20s", "PK_NAME"));
			while (res1.next()) {
				System.out.println(String.format("%-20s",
						res1.getString("PK_NAME")));
			}

			System.out.println(String.format("%-20s", "FKTABLE_NAME "));
			while (res2.next()) {
				System.out.println(String.format("%-20s",
						res2.getString("FKTABLE_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			// ejercicio8_AddColumn("alumnos", "test5", "varchar(20)", "NOT NULL");
			// ejercicio9_A();
			// ejercicio9_B();
			// ejercicio9_C("ADD");
			// ejercicio9_D("ADD");
			// ejercicio9_E();
			// ejercicio9_F("ADD");
			// ejercicio9_G("ADD");
			ejercicio9_H("ADD");

		}

		// Ejercicio 7
		long resultTime = System.currentTimeMillis() - startTime;
		System.out.println("\nExecution time: " + resultTime + " ms");
	}

}
