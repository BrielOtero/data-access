import java.sql.Connection;
import java.sql.DatabaseMetaData;
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

    public DatabaseMetaData getDatabaseMetaData() {
        try {
            return this.conexion.getMetaData();

        } catch (SQLException e) {
            System.out.println("Error getting data " + e.getLocalizedMessage());
        }

        return null;
    }

    // public DatabaseMetaData getDatabaseMetaData(String table) {
    // DatabaseMetaData dbmt;
    // ResultSet tables;
    // ResultSet columns;

    // try {
    // dbmt = this.conexion.getMetaData();
    // tables = dbmt.getTables(table, null, null, null);

    // while (tables.next()) {
    // System.out.println(
    // String.format("%s %s", tables.getString("TABLE_NAME"),
    // tables.getString("TABLE_TYPE")));
    // columns = dbmt.getColumns(table, null, tables.getString("TABLE_NAME"), null);

    // while (columns.next()) {
    // String.format(" %s %s %d %s %s",
    // columns.getString("COLUMN_NAME"),
    // columns.getString("COLUMN_NAME"),
    // columns.getString("COLUMN_NAME"),
    // columns.getInt("COLUMN_SIZE"),
    // columns.getString("IS_NULLABLE"),
    // columns.getString("IS_AUTOINCREMENT"));
    // }
    // }
    // } catch (SQLException e) {
    // System.out.println("Error getting data "+e.getLocalizedMessage());
    // }

    // }

}
