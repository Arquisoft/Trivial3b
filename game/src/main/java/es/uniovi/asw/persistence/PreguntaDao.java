package es.uniovi.asw.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface PreguntaDao {
	void setConnection(Connection con);
	List<Map<String, Object>> findAll();
	void insertar(Map<String, Object> pregunta);
	void guardarResultado(Map<String, Object> pregunta, boolean acertada);
	Map<String, Object> findByID(int idPregunta);	
}
