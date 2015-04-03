package es.uniovi.asw.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.model.Player;

public interface JugadorDao {
<<<<<<< HEAD
	void setConnection(Connection con);	
	List<Player> listarJugadores();
	void insertarJugadores(Map<String, Object> player);
	void modificarJugadores(Map<String, Object> player);
=======
    void setConnection(Connection con);
    List<Map<String, Object>> listarJugadores();
    void insertarJugadores(Map<String, Object> player);
    void modificarJugadores(Map<String, Object> player);
    boolean existeUsuario(String username, String pass, String rol);
>>>>>>> 1ba9e3a55b39a9418e64149f3312c3d1cd218baa
}
