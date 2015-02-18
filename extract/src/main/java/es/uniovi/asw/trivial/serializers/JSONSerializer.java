package es.uniovi.asw.trivial.serializers;

import java.util.List;

import es.uniovi.asw.trivial.preguntas.Pregunta;

public class JSONSerializer implements Serializer {

	@Override
	public String serialize(List<Pregunta> preguntas) {
		// Version cutre para ir probando que va

		String out = "";

		for (Pregunta pregunta : preguntas) {
			out += pregunta + "\n";
		}

		out += preguntas.size();
		return out;
	}

}
