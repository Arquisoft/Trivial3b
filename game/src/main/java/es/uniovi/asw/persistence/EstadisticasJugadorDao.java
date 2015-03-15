package es.uniovi.asw.persistence;

public interface EstadisticasJugadorDao {

	void setConexion(Connection conexion);
	List<Map<String, Object>> findAll();
	void insertar(Map<String, Object> estadisticaJugador);
	
	
}
