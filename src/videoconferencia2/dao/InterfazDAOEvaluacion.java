package videoconferencia2.dao;

import videoconferencia2.modelo.Evaluacion;

public interface InterfazDAOEvaluacion  extends InterfazDAO <Evaluacion, Integer> {
    void consultarVista();
}
