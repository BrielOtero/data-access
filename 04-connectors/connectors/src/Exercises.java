import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;

public class Exercises {
	public static Connectors connectors = new Connectors();
	public static SQLite sqLite = new SQLite();

	public static void ejercicio1(String cad) {
		try {
			ResultSet rs = connectors
					.executeQuery(
							"SELECT altura, apellidos,aula,nombre,codigo FROM alumnos WHERE nombre LIKE '%" + cad
									+ "%'");
			int cont = 0;
			// rs.last();s
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
		try {
			DatabaseMetaData dbmd = connectors.getDatabaseMetaData();
			ResultSet res1 = dbmd.getPrimaryKeys(database, null, null);
			ResultSet res2 = dbmd.getExportedKeys(database, null, null);

			System.out.println(String.format("%-20s", "COLUMN_NAME"));
			while (res1.next()) {
				System.out.println(String.format("%-20s",
						res1.getString("COLUMN_NAME")));
			}

			System.out.println(String.format("%-20s", "FKTABLE_NAME "));
			while (res2.next()) {
				System.out.println(String.format("%-20s | %-20s | %-20s",
						res2.getString("FKCOLUMN_NAME"), res2.getString("FKCOLUMN_NAME"),
						res2.getString("FKCOLUMN_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ejercicio10(String database, String query) {
		try {
			connectors.makeStatement();
			ResultSet rows = connectors.executeQuery(query);
			ResultSetMetaData rsmd = rows.getMetaData();
			System.out.println(String.format("%-20s | %-20s | %-20s | %-15s | %-10s", "Name", "Label", "Type",
					"AutoIncrement", "Nullable"));

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println(String.format("%-20s | %-20s | %-20s | %-15b | %-10d", rsmd.getColumnName(i),
						rsmd.getColumnLabel(i), rsmd.getColumnTypeName(i), rsmd.isAutoIncrement(i),
						rsmd.isNullable(i)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio11() {
		try {
			Enumeration<Driver> drivers = DriverManager.getDrivers();

			while (drivers.hasMoreElements()) {
				System.out.println(drivers.nextElement().getClass().getName());
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejercicio12() {
		try {

			/**
			 * En el primer caso tendríamos que hacer un prepare stament deshabilitando el
			 * auto commit
			 */
			connectors.conexion.setAutoCommit(false);

			/**
			 * En el segundo caso primero debemos guardar un savepoint en el rollbackal
			 * detectar que la consulta a sido fallida debemos
			 * hacer un rollback
			 */
			connectors.conexion.rollback(null);
			connectors.conexion.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ejercicio13() {
		try {
			ResultSet res = connectors.executeQuery("SELECT * FROM imagenes LIMIT 1");
			InputStream file = res.getBinaryStream("imagen");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ejercicio14() {

	}

	public static void ejercicio15() {

	}

	public static void ejercicio16() {

	}

	public static void ejercicio17() {

	}

	public static void sqlite1() {
		// Ready
	}

	public static void sqlite2() {
		// Ready
	}

	public static void sqlite3() {
		try (Statement st = sqLite.conexion.createStatement()) {

			ResultSet rs = st.executeQuery(
					"select * from aulas where puestos in (select distinct puestos from aulas order by puestos desc limit 1,2) order by puestos desc;");

			System.out.println("\nnombreAula\n");
			while (rs.next()) {
				System.out.println(rs.getString("nombreAula"));
			}

		} catch (Exception e) {
			System.out.println("Error executing SQlite query" + e.getMessage());
		}
	}

	public static void sqlite4(int positions) {
		String queryWithoutPreparedStatement = "SELECT * FROM aulas WHERE puestos >" + positions;
		String queryWithPreparedStatement = "SELECT * FROM aulas WHERE puestos > ?";

		try (Statement st = sqLite.conexion.createStatement()) {

			ResultSet rs = st.executeQuery(queryWithoutPreparedStatement);

			System.out.println("Without prepared statement");
			while (rs.next()) {
				System.out.println(rs.getString("nombreAula"));

			}

		} catch (Exception e) {
			System.out.println("Error executing SQlite query" + e.getMessage());
		}

		try (PreparedStatement preparedStatement = sqLite.conexion.prepareStatement(queryWithPreparedStatement)) {

			preparedStatement.setInt(1, positions);

			ResultSet rs = preparedStatement.executeQuery();

			System.out.println("With prepared statement");

			while (rs.next()) {
				System.out.println(rs.getString("nombreAula"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void sqlite5(String name, int positions) {
		try {

			int maxNumber = -1;
			Statement st = sqLite.conexion.createStatement();

			ResultSet rs = st.executeQuery("SELECT max(numero) as 'numero' FROM aulas;");

			while (rs.next()) {
				maxNumber = rs.getInt("numero");
			}

			if (maxNumber == -1)
				return;

			maxNumber++;
			String query = String.format("INSERT INTO aulas VALUES(%d,'%s',%d)", maxNumber, name, positions);

			System.out.println("Query: " + query);
			int res = st.executeUpdate(query);
			System.out.println(res == 1 ? "Aula inserted successfully" : "Aula not inserted");

		} catch (Exception e) {
			System.out.println("Error executing SQlite query" + e.getMessage());
		}
	}

	public static void sqlite6(int number, String name, int positions) {
		try {
			Statement st = sqLite.conexion.createStatement();

			String query = String.format(
					"REPLACE INTO aulas VALUES(%d,'%s',%d)", number,
					name, positions);

			System.out.println("Query: " + query);

			int res = st.executeUpdate(query);

			System.out.println(res == 1 ? "Aula modified successfully" : "Aula not modified");
		} catch (Exception e) {
			System.out.println("Error executing SQlite query" + e.getMessage());
		}
	}

	public static void sqlite7(Student student) {
		try {
			String query = String.format(
					"INSERT INTO alumnos (nombre,apellidos,altura,aula) VALUES (\"%s\",\"%s\",%d,%d)",
					student.nombre, student.apellidos, student.altura, student.aula);

			int resSQL = connectors.executeUpdate(query);

			System.out.println(resSQL == 1 ? "Alumno inserted on SQL" : "Alumno not inserted in SQL");

			Statement st = sqLite.conexion.createStatement();

			int resSQLite = st.executeUpdate(query);

			System.out.println(resSQLite == 1 ? "Alumno inserted on SQLite" : "Alumno not inserted in SQLite");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void sqlite8(String searchName) {
		try {
			String query = " Select * from aulas where nombreAula like '%" + searchName + "%'";

			ResultSet resSQL = connectors.executeQuery(query);

			System.out.println(String.format("%6s || %12s || %6s", "Numero", "NombreAula", "Puestos"));

			while (resSQL.next()) {
				System.out.println(String.format("%6d || %12s || %6d", resSQL.getInt("numero"),
						resSQL.getString("nombreAula"), resSQL.getInt("puestos")));
			}

			System.out.println();

			System.out.println(String.format("%6s || %12s || %6s", "Numero", "NombreAula", "Puestos"));

			ResultSet resSQLite = sqLite.conexion.createStatement().executeQuery(query);

			while (resSQLite.next()) {
				System.out.println(String.format("%6d || %12s || %6d", resSQLite.getInt("numero"),
						resSQLite.getString("nombreAula"), resSQLite.getInt("puestos")));
			}

			// sqlite con like diferencia entre acentuar

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void sqlite9(Student student) {
		Savepoint saveSQLite;
		Savepoint saveSQL;
		Statement stSQLite;
		try {
			String query = String.format(
					"INSERT INTO alumnos (nombre,apellidos,altura,aula) VALUES (\"%s\",\"%s\",%d,%d)",
					student.nombre, student.apellidos, student.altura, student.aula);

			saveSQL = connectors.conexion.setSavepoint();

			int resSQL = connectors.executeUpdate(query);

			System.out.println(resSQL == 1 ? "Alumno inserted on SQL" : "Alumno not inserted in SQL");

			if (resSQL != 1) {
				connectors.conexion.rollback(saveSQL);
				return;
			}

			stSQLite = sqLite.conexion.createStatement();

			saveSQLite = sqLite.conexion.setSavepoint();

			int resSQLite = stSQLite.executeUpdate(query);

			System.out.println(resSQLite == 1 ? "Alumno inserted on SQLite" : "Alumno not inserted in SQLite");

			if (resSQLite != 1) {
				connectors.conexion.rollback(saveSQL);
				sqLite.conexion.rollback(saveSQLite);
				System.out.println("Alumno removed in SQL");
			} else {
				connectors.conexion.commit();
				sqLite.conexion.commit();
			}

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
	}

	private static void sqlite10_A(String name, String date) {
		try {

			String query = String.format("INSERT INTO fechas (nombre,fecha)  VALUES (\"%s\",\"%s\")", name, date);

			int resSQL = connectors.executeUpdate(query);

			System.out.println(resSQL == 1 ? "Fecha inserted on SQL" : "Fecha not inserted in SQL");

			Statement st = sqLite.conexion.createStatement();

			int resSQLite = st.executeUpdate(query);

			System.out.println(resSQLite == 1 ? "Fecha inserted on SQLite" : "Fecha not inserted in SQLite");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private static void sqlite10_B(String name, String date) {
		//Sin el parametro el sql trunca
		sqlite10_A(name, date);
	}

	private static void sqlite10_C(String name, String date) {
		//Inserted
		sqlite10_A(name, date);
	}

	private static void sqlite10_D(String name) {
		//SQLite mete UTC , SQL mete utc pero muestra la actual del sistema
		try {

			String querySQL = String.format("INSERT INTO fechas (nombre,fecha)  VALUES (\"%s\",NOW())", name);

			int resSQL = connectors.executeUpdate(querySQL);

			System.out.println(resSQL == 1 ? "Fecha inserted on SQL" : "Fecha not inserted in SQL");

			String querySQLite = String.format("INSERT INTO fechas (nombre,fecha)  VALUES (\"%s\",DATETIME('now'))", name);

			Statement st = sqLite.conexion.createStatement();

			int resSQLite = st.executeUpdate(querySQLite);

			System.out.println(resSQLite == 1 ? "Fecha inserted on SQLite" : "Fecha not inserted in SQLite");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void sqlite10_E(String name) {
		//SQLite mete una cadena vacia, SQL mete 0000-00-00 00:00:00
		sqlite10_A(name, "");
	}

	public static void main(String[] args) {

		// Exercise 7
		int runs = 1;
		long startTime = System.currentTimeMillis();

		for (int i = 0; i < runs; i++) {

			// Connectors

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
			// ejercicio9_H("ADD");
			// ejercicio10("ADD", "select *, nombre as non from alumnos");
			// ejercicio11();

			// SQLite

			// sqlite3();
			// sqlite4(34);
			// sqlite5("Programacion", 25);
			// sqlite6(34, "Programacion", 2);
			// sqlite7(new Student("Manuel","Marín",185,21));
			// sqlite8("í");
			// sqlite9(new Student("Manuel","Marín",185,21));
			// sqlite10_A("Test", LocalDate.now().toString());
			// sqlite10_B("ABCDEFGHIJKLM", LocalDate.now().toString());
			// sqlite10_C("ABCDEFGHIJKLM", LocalDate.now().toString());
			sqlite10_D("Test10");
			// sqlite10_E("ABCDEFGHIJKLM");

		}

		// Ejercicio 7
		long resultTime = System.currentTimeMillis() - startTime;
		System.out.println("\nExecution time: " + resultTime + " ms");
	}

}
