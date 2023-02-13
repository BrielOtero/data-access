package exercises;

import java.sql.Statement;
import java.util.ArrayList;

import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AthleteConnector {
	public Connection conexion;

	private String bd = "athletesDB";
	private String server = "127.0.0.1";
	private String user = "root";
	private String password = "";

	private void openConnection() {

		try {

			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

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

	public int executeUpdate(String query) {
		openConnection();

		try (Statement st = this.conexion.createStatement()) {
			return st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Error executing query");
		}
		return 0;
	}

	public Response select(String query) {
		ArrayList<Athlete> athletes = new ArrayList<>();

		try {
			ResultSet res = executeQuery(query);
			while (res.next()) {
				athletes.add(new Athlete(res.getInt("id"), res.getString("name"), res.getString("sport"),
						res.getBoolean("active"), res.getString("genre")));
			}
			System.out.println("Size: " + athletes.size());
			return Response.ok(athletes).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	public Response selectSport(String query) {
		ArrayList<String> sports = new ArrayList<>();

		try {
			ResultSet res = executeQuery(query);
			while (res.next()) {
				sports.add(res.getString("sport"));
			}
			System.out.println("Size: " + sports.size());
			return Response.ok(sports).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	public Response count(String query) {
		try {
			ResultSet res = executeQuery(query);
			res.next();
			return Response.ok(res.getInt("count")).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	public Response insert(String query) {
		try {
			int res = executeUpdate(query);
			if (res != 0) {
				return Response.ok().build();
			} else {
				return Response.serverError().build();
			}

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
