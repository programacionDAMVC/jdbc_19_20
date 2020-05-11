package videoconferencia2.dao;

import videoconferencia2.Conexion;
import videoconferencia2.modelo.Alumno;
import videoconferencia2.modelo.Evaluacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAO implements InterfazDAOEvaluacion {
    private static Connection conexion;

    static {
        try {
            conexion = Conexion.getConexion();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consultarVista() {
        String sql = "SELECT * FROM curso2A";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                //alumno.nombre as nombre, alumno.apellidos, evaluacion.notas, modulo.nombre as nombreModulo
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                int notas = resultSet.getInt("notas");
                String nombreModulo = resultSet.getString("nombreModulo");
                System.out.printf("%-15s%-20s%5d     %-15s%n", nombre, apellidos, notas, nombreModulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Evaluacion> leerTodos() {
      //  List<Alumno> listaTodosAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM evaluacion;";
        //idAlumno|idModulo|idCurso|notas
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int idAlumno = resultSet.getInt(1);
                int idModulo = resultSet.getInt(2);
                int idCurso  = resultSet.getInt(3);
                int notas    = resultSet.getInt(4);
                Evaluacion evaluacion = new Evaluacion(idAlumno, idModulo, idCurso, notas);
                System.out.println(evaluacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Evaluacion leerAlumnoPorId(Integer integer) {
        return null;
    }

    @Override
    public boolean borrarPorId(Integer integer) {
        return false;
    }

    @Override
    public boolean actualizarPorId(Evaluacion evaluacion) {
        return false;
    }

    @Override
    public boolean crear(Evaluacion evaluacion) {
        return false;
    }
}
