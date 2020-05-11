package videoconferencia1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class TestV {
    private static Scanner sc = null;
    private  static Connection  conexion = null;
    private static Statement statement = null;
    private static  PreparedStatement preparedStatement = null;
    public static void main(String[] args) throws IOException, SQLException {
       conexion = ConexionV.getConexion();
       ejecutarScript("scripts/crear_tablas.sql");
       ejecutarScript("scripts/crear_indice.sql");
       ejecutarScript("scripts/crear_vista.sql");
       ejecutarScript("scripts/insertar_datos.sql");
       ejecutarScript("scripts/crear_trigger.sql");
       consultarTablaAlumno();
       consultarVistaNotas(5, "Base de datos");  //datos vista cuya nota >= 5



        //   ConexionV.cerrarConexion();

    }

    private static void consultarVistaNotas(int nota, String modulo) throws SQLException {
      //  String sql = "SELECT * FROM curso2A WHERE notas >=" + nota + " AND nombreModulo = \""+ modulo + "\";";
        String sql = "SELECT * FROM curso2A WHERE notas >= ? AND nombreModulo = ?;";
      //  statement = conexion.createStatement();
        preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setInt(1, nota);
        preparedStatement.setString(2, modulo);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int notas = resultSet.getInt("notas");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            String nombreModulo = resultSet.getString("nombreModulo");
            System.out.printf("%-15s%-20s%-25s%-5d%n", nombre, apellidos, nombreModulo, notas);
        }
    }

    private static void consultarTablaAlumno() throws SQLException {
        String sql = "SELECT * FROM alumno;";
        statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString(3);
            System.out.printf("%-4d%-15s%-20s%n", id, nombre, apellidos);
        }
    }

    private static void ejecutarScript(String script) throws FileNotFoundException, SQLException {
        sc = new Scanner(new File(script));
        sc.useDelimiter(";");
        statement = conexion.createStatement();
      //  int contador = 0;
        while (sc.hasNext()) {
            String  sentencia = sc.next();
            if (sentencia.contains("TRIGGER"))
                sc.useDelimiter(";;");
            statement.addBatch(sentencia);
//            System.out.println(contador + " " + sc.next());
        }
        statement.executeBatch();
        System.out.println("Ejecutado script: " + script);
    }
}
