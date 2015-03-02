package es.uniovi.asw.trivial.parsers;

import java.util.List;

import es.uniovi.asw.trivial.preguntas.Pregunta;

public interface Parser {
    List<Pregunta> parse(String file);
}
