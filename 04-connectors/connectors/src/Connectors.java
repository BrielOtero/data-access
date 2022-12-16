import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connectors {
    private Connection conexion;

    public Connectors() {
        openConnection("add", "127.0.0.1", "root", "");
    }

    public void openConnection(String bd, String servidor, String usuario,
            String password) {
        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
            // Establecemos la conexión con la BD
            this.conexion = DriverManager.getConnection(url, usuario, password);
            if (this.conexion != null) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public ResultSet executeQuery(String query) {
        try (Statement st = this.conexion.createStatement()) {
            return st.executeQuery(query);
        } catch (Exception e) {
        }
        return null;
    }

    public int executeUpdate(String query) {
        try (Statement st = this.conexion.createStatement()) {
            return st.executeUpdate(query);
        } catch (Exception e) {
        }
        return 0;
    }

    public ResultSet executePreparedStatement(String query, Object[] objects) {
        try {
            PreparedStatement ps = this.conexion.prepareStatement(query);
            int parameterIndex;

            for (int i = 0; i < objects.length; i++) {
                parameterIndex = i + 1;

                if (objects[i] instanceof String) {
                    ps.setString(parameterIndex, (String) objects[i]);
                } else if (objects[i] instanceof Integer) {
                    ps.setInt(parameterIndex, (Integer) objects[i]);
                }
            }

            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
