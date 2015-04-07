package es.uniovi.asw.modelo.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EstadisticasJugadorDao {
    void setConexion(Connection conexion);

    List<Map<String, Object>> findAll();

    void guardarResultado(Map<String, Object> estadistica, boolean acertada);

    // Find by Jugador y Pregunta
    Map<String, Object> findByJyP(int idJugador, String idPregunta);

	void insertar(int idJugador, String idPregunta);
}
