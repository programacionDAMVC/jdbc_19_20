package videoconferencia2.modelo;

public class Evaluacion {
    private Integer idAlumno;
    private Integer idModulo;
    private Integer idCurso;
    private int notas;

    public Evaluacion(Integer idAlumno, Integer idModulo, Integer idCurso, int notas) {
        this.idAlumno = idAlumno;
        this.idModulo = idModulo;
        this.idCurso = idCurso;
        this.notas = notas;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public int getNotas() {
        return notas;
    }

    public void setNotas(int notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return String.format("%-5d%-5d%-5d%-5d", idAlumno, idModulo, idCurso, notas);
    }
}
