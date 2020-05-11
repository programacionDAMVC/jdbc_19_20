package videoconferencia2.dao;

import videoconferencia2.modelo.Alumno;

import java.util.List;

public interface InterfazDAO <T, K> {
      List<T> leerTodos();
      T leerAlumnoPorId(K k);
      boolean borrarPorId(K k);
      boolean actualizarPorId(T t);
      boolean crear(T t);
}
