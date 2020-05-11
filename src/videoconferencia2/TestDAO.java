package videoconferencia2;

import videoconferencia2.dao.AlumnoDAO;
import videoconferencia2.dao.EvaluacionDAO;
import videoconferencia2.modelo.Alumno;

import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
      //  System.out.println(alumnoDAO.borrarPorId(15));
      //  System.out.println(alumnoDAO.actualizarPorId(new Alumno(1, "Amalia", "Medina Medina")));
        System.out.println(alumnoDAO.crear(new Alumno("Amalia", "Medina Medina")));
        List<Alumno> listaAlumnos = alumnoDAO.leerTodos();
        listaAlumnos.forEach(System.out::println);
        EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
        evaluacionDAO.leerTodos();
        System.out.println("-----------------------------------------");
        evaluacionDAO.consultarVista();
    }
}
