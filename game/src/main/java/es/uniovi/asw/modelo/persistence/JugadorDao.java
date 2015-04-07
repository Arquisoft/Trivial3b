package es.uniovi.asw.modelo.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.modelo.model.Player;

public interface JugadorDao {
    void setConnection(Connection con);

    List<Player> listarJugadores();

    void insertarJugadores(String name, String pass);

    void modificarJugadores(Map<String, Object> player);

    boolean existeUsuario(String username, String pass, String rol);
}
