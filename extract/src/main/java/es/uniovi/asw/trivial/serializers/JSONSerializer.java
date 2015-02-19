package es.uniovi.asw.trivial.serializers;

import java.util.List;

import es.uniovi.asw.trivial.preguntas.Pregunta;

/**
 * Clase encargada de serializar una lista de preguntas a formato JSON. La
 * salida consiste de un array de objetos JSON con el siguiente formato:
 * 
 * <code>
 * {
 *   "question": "Pregunta",
 *   "category": "Categoría",
 *   "correct": "Respuesta correcta",
 *   "incorrect": [
 *     "Respuesta falsa",
 *     "Otra respuesta falsa",
 *     "Otra falsa más"
 *   ]
 * }
 * </code>
 * 
 * @author Daniel García García
 *
 */
public class JSONSerializer implements Serializer {

	@Override
	public String serialize(List<Pregunta> preguntas) {
		StringBuilder sb = new StringBuilder("[\n");

		for (Pregunta pregunta : preguntas) {
			sb.append(serializeQuestion(pregunta));
		}

		// Borrar la ultima coma
		sb.deleteCharAt(sb.length() - 2);

		sb.append("]\n");
		return sb.toString();
	}

	public String serializeQuestion(Pregunta p) {

		StringBuilder sb = new StringBuilder();
		String[] wrong = p.getWrongAnswers();

		sb.append("{\n");
		sb.append("  `question`: `" + escape(p.getQueryText()) + "`,\n");
		sb.append("  `category`: `" + escape(p.getCategory() + "") + "`,\n");
		sb.append("  `correct`: `" + escape(p.getCorrectAnswer()) + "`,\n");
		sb.append("  `incorrect`: [\n");

		for (int i = 0; i < wrong.length; ++i) {
			if (i >= wrong.length - 1)
				sb.append("    `" + escape(wrong[i]) + "`\n");
			else
				sb.append("    `" + escape(wrong[i]) + "`,\n");
		}

		sb.append("  ]\n");
		sb.append("},\n");

		return sb.toString().replace("`", "\"");
	}

	/**
	 * Escapa las dobles comillas de la entrada, que no son válidas en JSON
	 */
	private static String escape(String string) {
		return string.replace("\"", "\\\"");
	}
}
