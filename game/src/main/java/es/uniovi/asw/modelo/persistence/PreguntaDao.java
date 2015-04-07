package es.uniovi.asw.modelo.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.modelo.model.Pregunta;

public interface PreguntaDao {
    void setConnection(Connection con);

    List<Map<String, Object>> findAll();

    void guardarResultado(Map<String, Object> pregunta, boolean acertada);

    Map<String, Object> findById(String id);

    void insertar(Pregunta pregunta);
}
