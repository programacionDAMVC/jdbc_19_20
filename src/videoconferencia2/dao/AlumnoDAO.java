package videoconferencia2.dao;

import videoconferencia2.Conexion;
import videoconferencia2.modelo.Alumno;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements InterfazDAOAlumno {
    private static  Connection conexion;

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
    public List<Alumno> leerTodos() {
        List<Alumno> listaTodosAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno;";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                Alumno alumno = new Alumno(id, nombre, apellidos);
                listaTodosAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTodosAlumnos;
    }

    @Override
    public Alumno leerAlumnoPorId(Integer integer) {
        return null;
    }

    @Override
    public boolean borrarPorId(Integer id) {
        String sql = "DELETE FROM alumno WHERE id = ?;";
        PreparedStatement preparedStatement = null;
        int valor = 0;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            valor = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return valor == 1;
    }

    @Override
    public boolean actualizarPorId(Alumno alumno) {
        String sql = "UPDATE alumno SET nombre = ? , apellidos = ? WHERE id = ?;";
        PreparedStatement preparedStatement = null;
        int resultado = 0;
        try {
             preparedStatement = conexion.prepareStatement(sql);
             preparedStatement.setString(1, alumno.getNombre());
             preparedStatement.setString(2, alumno.getApellidos());
             preparedStatement.setInt(3, alumno.getId());
             resultado = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado == 1;
    }

    @Override
    public boolean crear(Alumno alumno) {
        String sql = "INSERT INTO alumno (nombre, apellidos) VALUES ( ?, ? );";
        PreparedStatement preparedStatement = null;
        int valor = 0;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellidos());
            valor = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return valor == 1;
    }
}
