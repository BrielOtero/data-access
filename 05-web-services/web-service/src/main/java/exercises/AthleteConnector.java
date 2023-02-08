package exercises;

import java.sql.Statement;
import java.util.ArrayList;

import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AthleteConnector {
	Connection conexion;

	String bd = "athletesDB";
	String server = "127.0.0.1";
	String user = "root";
	String password = "";

	public AthleteConnector() {
	}

	private void openConnection() {

		try {
			String url = String.format("jdbc:mariadb://%s:3306/%s", server, bd);
			this.conexion = DriverManager.getConnection(url, user, password);

			if (this.conexion != null) {
				System.out.println("Connected to " + bd + " on " + server);
			} else {
				System.out.println("NO Connected to " + bd + " on " + server);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getLocalizedMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Código error: " + e.getErrorCode());
		}
	}

	public ResultSet executeQuery(String query) {
		openConnection();

		try (Statement st = this.conexion.createStatement()) {
			return st.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Error executing query");
		}
		return null;
	}

	public Response select(String query) {
		ArrayList<Athlete> athletes = new ArrayList<>();

		try {
			ResultSet res = executeQuery(query);

			while (res.next()) {
				athletes.add(new Athlete(res.getInt("id"), res.getString("name"), res.getString("sport"),
						res.getBoolean("active"), res.getString("genre")));
			}
			return Response.ok(athletes).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
