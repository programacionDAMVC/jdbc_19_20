package patronDAO.daos;

import patronDAO.modelos.Alumno;

public interface InterfazAlumnoDAO extends IntrafazDAO<Alumno, Integer> {
    Alumno buscarPorApellidos(String apellidos);
}
