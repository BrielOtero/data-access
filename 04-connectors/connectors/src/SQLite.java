import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	public Connection conexion;

	public SQLite() {
		openConnection("database/bdsqlite.db");
		
	}

	public void openConnection(String servidor) {
		try {
			try {
				Class.forName ("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			String url = String.format("jdbc:sqlite:%s", servidor);
			// Establecemos la conexión con la BD
			this.conexion = DriverManager.getConnection(url);
			if (this.conexion != null) {
				System.out.println("Conectado a " + servidor);
			} else {
				System.out.println("No conectado a " + servidor);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getLocalizedMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Código error: " + e.getErrorCode());
		}
	}

}
