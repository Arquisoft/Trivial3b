package es.uniovi.asw.trivial.serializers;

import java.util.List;

import es.uniovi.asw.trivial.preguntas.Pregunta;

public interface Serializer {

    String serialize(List<Pregunta> preguntas);

}
