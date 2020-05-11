package videoconferencia2;

import org.sqlite.SQLiteConfig;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Connection conexion = null;

    private Conexion() throws SQLException, IOException {
        // String url = "BD/uno.db";
        // String driver = "jdbc:sqlite:";
        //driver + url :    jdbc:sqlite:BD/uno.db
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Cierre());
        Properties properties = new Properties();
        properties.load(new FileReader("config/video.propierties"));
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.enforceForeignKeys(true); //permitir claves foráneas
        conexion = DriverManager.getConnection(driver + url, sqLiteConfig.toProperties());
    }

    public static Connection getConexion() throws IOException, SQLException {
        if (conexion == null)
            new Conexion();

        return conexion;
    }

    public static  void  cerrarConexion() throws SQLException {
        if (conexion != null)
            conexion.close();
    }

    static class Cierre extends Thread {
        @Override
        public void run() {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
