package es.uniovi.asw.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EstadisticasJugadorDao {

	void setConexion(Connection conexion);
	List<Map<String, Object>> findAll();
	void insertar(Map<String, Object> estadisticaJugador);
}
