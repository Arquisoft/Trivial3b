package es.uniovi.asw.trivial.serializers;

import java.util.List;

import es.uniovi.asw.model.Pregunta;

public interface Serializer {

    String serialize(List<Pregunta> preguntas);

}
