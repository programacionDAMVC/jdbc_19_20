package videosIntroduccion;

import org.sqlite.SQLiteConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Test {
    private static Connection conexion;
    private static Scanner sc;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    public static void main(String[] args) throws IOException, SQLException {
        conexion = Conexion.getConexion();
        cargarScript("scripts/crear_tablas.sql");
        cargarScript("scripts/crear_indice.sql");
        cargarScript("scripts/crear_vista.sql");
        cargarScript("scripts/insertar_datos.sql");
        cargarScript("scripts/crear_trigger.sql");
        consultarTablaAlumno();
        System.out.println("--------------------1---------------------------");
        consultarVista1(4, "Base de datos");
        System.out.println("--------------------2---------------------------");
        consultarVista2(4, "Base de datos");
        sc = new Scanner(System.in);
        System.out.println("Introduce nombre del alumno");
        String nombreAlumno = sc.nextLine();
        System.out.println("Introduce apellidos del alumno");
        String apellidos = sc.nextLine();
        Alumno alumno = new Alumno(nombreAlumno, apellidos);
        insertarAlumno(alumno);
        System.out.println("Borrar alumno, introduce id:");
        int id = sc.nextInt();
        borrarPorId(id);
        System.out.println("Alumno alumno, introduce id:");
        id = sc.nextInt();
        System.out.println("Introduce nombre del alumno");
        nombreAlumno = sc.next();
        actualizarNombreAlumnoPorId(id, nombreAlumno);
        consultarTablaAlumno();
        cerrarFlujos();



    }

    private static void cerrarFlujos() throws SQLException {
        if (sc != null)
            sc.close();
        if (statement != null)
            statement.close();
        if (preparedStatement != null)
            preparedStatement.close();
    }


    private static void actualizarNombreAlumnoPorId(int id, String nombreAlumno) throws SQLException {
        String sql = "UPDATE alumno SET nombre = ? WHERE id = ?";
        preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, nombreAlumno);
        preparedStatement.setInt(2,id);
        System.out.println("ACTUALIZACIÓN CORRECTA: " + preparedStatement.executeUpdate());
    }

    private static void borrarPorId(int id) throws SQLException {
        String sql = "DELETE FROM alumno WHERE id = ?";
        preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        System.out.println("BORRADO CORRECTO: " + preparedStatement.executeUpdate());
    }

    private static void insertarAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO alumno (nombre, apellidos) VALUES (?, ?);";
        preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, alumno.getNombreAlumno());
        preparedStatement.setString(2, alumno.getApellidos());
        System.out.println("INSERCCIÓN CORRECTA: " + preparedStatement.executeUpdate());

    }

    private static void consultarVista2(int nota, String modulo) throws SQLException {
        String sql = "SELECT * FROM curso2A WHERE notas > ? AND nombreModulo= ?;";
        preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setInt(1, nota );
        preparedStatement.setString(2, modulo);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            System.out.printf("%-15s%-20s%-5d%-15s%n", resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getString(4));    }

    private static void consultarVista1(int nota, String modulo) throws SQLException {

        String sql = "SELECT * FROM curso2A WHERE notas >" + nota +" AND nombreModulo=\"" + modulo +"\";";
        statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
            System.out.printf("%-15s%-20s%-5d%-15s%n", resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getString(4));
    }

    private static void consultarTablaAlumno() throws SQLException {
        String sql = "SELECT * FROM alumno;";
        statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
            System.out.printf("%-15s%-15s%n", resultSet.getString("nombre"), resultSet.getString("apellidos"));
    }

    private static void cargarScript(String fichero) throws FileNotFoundException, SQLException {
        List<String> sentencias = crearSentencias(fichero);
       /* for (int i = 0; i < sentencias.size(); i++) {
            System.out.println("sentencia numero: " + i);
            System.out.println(sentencias.get(i));
        }*/
        statement = conexion.createStatement();
        for (String sentencia: sentencias) {
            statement.addBatch(sentencia);
        }
        statement.executeBatch();
        System.out.printf("Ejecutado script %s%n", fichero);
   //     statement.close();

    }

    private static List<String> crearSentencias(String fichero) throws FileNotFoundException {
        sc = new Scanner(new File(fichero));
        List<String> sentecias = new ArrayList<>();
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            String valor = sc.next();
            if (valor.contains("TRIGGER"))
                sc.useDelimiter(";;");
            sentecias.add(valor);
        }
        return sentecias;
    }



}

