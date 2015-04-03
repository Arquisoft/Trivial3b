package es.uniovi.asw.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD




=======
>>>>>>> 1ba9e3a55b39a9418e64149f3312c3d1cd218baa
import es.uniovi.asw.model.Pregunta;

public interface PreguntaDao {
	void setConnection(Connection con);
	List<Map<String, Object>> findAll();
	void guardarResultado(Map<String, Object> pregunta, boolean acertada);
	Map<String, Object> findByEnunciado(String pregunta);
	void insertar(Pregunta pregunta);	
}
