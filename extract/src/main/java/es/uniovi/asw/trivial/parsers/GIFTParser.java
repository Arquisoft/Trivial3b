package es.uniovi.asw.trivial.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.CharMatcher;

import es.uniovi.asw.trivial.preguntas.Category;
import es.uniovi.asw.trivial.preguntas.Pregunta;
import es.uniovi.asw.trivial.util.FileUtil;

/**
 * Esta clase se encarga de parsear el formato GIFT de preguntas y respuestas
 * multiples. El formato es el siguiente:
 * 
 * <code>
 *  Titulo de la pregunta {
 * 		= Respuesta correcta
 * 		~ Respuesta incorrecta
 * 		~ Respuesta incorrecta
 * 		~ Respuesta incorrecta
 *  }
 *  </code>
 * 
 * El orden de las respuestas no es importante, ni es necesario que estén
 * tabuladas, pero se debe poner una por línea. Para indicar la categoria de
 * preguntas, usar:
 * 
 * $Categoria: Deportes
 * 
 * Esto se aplicará a todas las preguntas siguientes, hasta que se cambie de
 * nuevo. Todas las preguntas necesitan una categoria, así es necesario indicar
 * la categoría al principio del archivo, para que se aplique a todas las
 * preguntas siguientes (a no ser que se cambien).
 * 
 * Los comentarios se escriben con //, todo lo que halla desde // hasta el fin
 * de linea es ignorado
 * 
 * @author Daniel García García
 *
 */
public class GIFTParser implements Parser {

	private Map<String, Pregunta> preguntas = new HashMap<String, Pregunta>();
	private String categoriaActual;

	private Pregunta preguntaActual;
	private int numIncorrectas;
	private boolean preguntaInvalida;

	@Override
	public List<Pregunta> parse(String fileName) {
		String file = FileUtil.getFile(fileName);

		// Dividimos el archivo en lineaas
		String[] lineas = file.split("[\r\n]");

		// Por cada linea
		for (int i = 0; i < lineas.length; ++i) {
			// Quitar comentarios
			String linea = trim(lineas[i].split("//")[0]);

			// Quitar lineas vacias
			if (linea.isEmpty())
				continue;

			// Si es una linea de categoria...
			if (linea.toLowerCase().startsWith("$categoria:")
					|| linea.toLowerCase().startsWith("$category:")) {

				if (preguntaActual != null) {
					System.err.println("No se puede poner categoría dentro de la pregunta (linea "
							+ i + ")");
					continue;
				}

				// Guardamos la categoria de forma temporal
				categoriaActual = trim(linea.split(":")[1]);
				continue;
			}

			// Empieza una nueva pregunta
			if (linea.contains("{")) {
				if (preguntaActual != null) {
					System.err.println("Una pregunta termina abruptamente en linea " + i
							+ ", saltando");
				}

				// Reseteamos los valores
				numIncorrectas = 0;
				preguntaInvalida = false;

				// Y creamos una nueva pregunta
				preguntaActual = new Pregunta();

				String titulo = trim(linea.split("\\{")[0]);

				preguntaActual.setQuestion(titulo);
				preguntaActual.setCategory(Category.parse(categoriaActual));

				if (categoriaActual == null) {
					System.err.println("Una pregunta en linea " + i
							+ "no tiene categoría, saltando");
					preguntaInvalida = true;
				}

				continue;
			}

			if (linea.startsWith("=")) {

				if (preguntaActual.getCorrectAnswer() != null) {
					System.err.println("La pregunta en linea " + i
							+ " tiene varias respuestas correctas, saltando");
					preguntaInvalida = true;
				}

				preguntaActual.setCorrectAnswer(trim(linea.substring(1)));
				continue;
			}

			if (linea.startsWith("~")) {

				if (numIncorrectas >= Pregunta.NUM_ANSWERS - 1) {
					System.err.println("La pregunta en linea " + i
							+ " tiene mas respuestas de las que deberia, saltando");
					preguntaInvalida = true;
				}

				preguntaActual.getWrongAnswers()[numIncorrectas++] = trim(linea.substring(1));
				continue;
			}

			if (linea.startsWith("}")) {
				if (preguntaActual.getCorrectAnswer() == null) {
					System.err.println("La pregunta en linea " + i
							+ " no tiene respuesta correcta, saltando");
					preguntaInvalida = true;
				}

				if (numIncorrectas != Pregunta.NUM_ANSWERS - 1) {
					System.err.println("La pregunta en linea " + i
							+ " tiene un número incorrecto de respuestas, saltando");
					preguntaInvalida = true;
				}

				if (!preguntaInvalida) {
					if (preguntas.containsKey(preguntaActual.getQuestion())) {
						System.err.println("La pregunta en linea " + i + " es repetida, saltando");
					}

					preguntas.put(preguntaActual.getQuestion(), preguntaActual);
					preguntaActual = null;
				}
			}
		}

		return new ArrayList<Pregunta>(preguntas.values());
	}

	private static String trim(String in) {
		return CharMatcher.WHITESPACE.trimFrom(in);
	}
}
