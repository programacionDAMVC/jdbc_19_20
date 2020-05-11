package patronDAO.daos;

import patronDAO.Conexion;
import patronDAO.modelos.Evaluacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAO implements InterfazEvaluacionDAO {
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
    public List<Evaluacion> listarTodo() {
        List<Evaluacion> listaEvaluacion = new ArrayList<>();
        String sql = "SELECT * FROM evaluacion;";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                listaEvaluacion.add(new Evaluacion(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEvaluacion;
    }

    @Override
    public Evaluacion listarPorId(Integer integer) {
        return null;
    }

    @Override
    public boolean crearRegistro(Evaluacion evaluacion) {
        return false;
    }

    @Override
    public boolean actualizarRegistro(Evaluacion evaluacion) {
        return false;
    }

    @Override
    public boolean borrarRegistroPorId(Integer integer) {
        return false;
    }



    @Override
    public void mostrarDatos2A() {
        String sql = "SELECT * FROM curso2A;";
        try (Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                System.out.printf("%-15s%-20s%-5d%-15s%n", resultSet.getString(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4) );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
        evaluacionDAO.listarTodo().forEach(System.out::println);
        System.out.println("----------------------");
        evaluacionDAO.mostrarDatos2A();
    }
}
