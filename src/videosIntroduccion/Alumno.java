package videosIntroduccion;

public class Alumno {
    private String nombreAlumno;
    private String apellidos;

    public Alumno(String nombreAlumno, String apellidos) {
        this.nombreAlumno = nombreAlumno;
        this.apellidos = apellidos;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}

