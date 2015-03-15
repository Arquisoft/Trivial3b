package es.uniovi.asw.persistence;

import java.sql.Connection;

public interface PreguntaDao {

	void setConexion(Connection conexion);
	List<Map<String, Object>> findAll();
	void insertar(Map<String, Object> pregunta);
	void guardarResultado(Map<String, Object> pregunta, boolean acertada);
	Map<String, Object> findByID(int idPregunta);
}
