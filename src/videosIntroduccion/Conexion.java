package videosIntroduccion;

import org.sqlite.SQLiteConfig;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Connection conexion = null;
    private Conexion() throws IOException, SQLException {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new CierreDeConexion());
        Properties properties = new Properties();
        properties.load(new FileReader("/home/manuel/jdbc/config/config.propierties"));
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.enforceForeignKeys(true);
        conexion = DriverManager.getConnection(url, sqLiteConfig.toProperties());
    }
    public static Connection getConexion() throws IOException, SQLException {
        if (conexion == null)
            new Conexion();
        return conexion;
    }

    static class  CierreDeConexion extends Thread {
        @Override
        public void run() {
            try {
                Connection connection = getConexion();
                if (connection != null)
                    connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
