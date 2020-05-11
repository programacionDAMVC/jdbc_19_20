package patronDAO.daos;

import patronDAO.Conexion;
import patronDAO.modelos.Alumno;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements InterfazAlumnoDAO {
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
    public List<Alumno> listarTodo() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno;";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            while (resultSet.next())
                listaAlumnos.add(new Alumno(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellidos")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }

    @Override
    public Alumno listarPorId(Integer integer) {
        return null;
    }

    @Override
    public boolean crearRegistro(Alumno alumno) {
        boolean exito = false;
        String sql = "INSERT INTO alumno (nombre, apellidos) VALUES ( ?, ?);";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellidos());
            exito  = preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return exito;
    }

    @Override
    public boolean actualizarRegistro(Alumno alumno)
    {
        String sql = "UPDATE alumno SET apellidos =? WHERE id = ?;";
        boolean exito = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(2, alumno.getId());
            preparedStatement.setString(1, alumno.getApellidos());
            exito  = preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return exito;
    }

    @Override
    public boolean borrarRegistroPorId(Integer id) {
        String sql = "DELETE FROM alumno WHERE id = ?;";
        boolean exito = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            exito  = preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return exito;
    }

    @Override
    public Alumno buscarPorApellidos(String apellidos) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE apellidos = ?";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);)
                 {
            preparedStatement.setString(1, apellidos);
            resultSet = preparedStatement.executeQuery();
                     while (resultSet.next())
                         alumno = new Alumno(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellidos"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return alumno;
    }

    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        List<Alumno> lista = alumnoDAO.listarTodo();
        lista.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println(alumnoDAO.buscarPorApellidos("Nuñez Garcías"));
        Alumno alumno = new Alumno("felipe", "somoaguas del valle");
     //   System.out.println(alumnoDAO.crearRegistro(alumno));
         alumno = new Alumno(18, "luis", "somoaguas");
        System.out.println(alumnoDAO.actualizarRegistro(alumno));
        System.out.println(alumnoDAO.borrarRegistroPorId(16));


    }
}
