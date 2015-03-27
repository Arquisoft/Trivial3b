package es.uniovi.asw.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface JugadorDao {
    void setConnection(Connection con);
    List<Map<String, Object>> listarJugadores();
    void insertarJugadores(Map<String, Object> player);
    void modificarJugadores(Map<String, Object> player);
    boolean existeUsuario(String username, String pass, String rol);
}
