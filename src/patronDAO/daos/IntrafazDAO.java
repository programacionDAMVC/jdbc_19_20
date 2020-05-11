package patronDAO.daos;

import java.util.List;

public interface IntrafazDAO <T, K> {
    List<T> listarTodo();
    T listarPorId(K k);
    boolean crearRegistro(T t);
    boolean actualizarRegistro(T t);
    boolean borrarRegistroPorId(K k);
}
