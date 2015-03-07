package es.uniovi.asw.trivial.serializers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.uniovi.asw.model.Pregunta;

/**
 * Clase encargada de serializar una lista de preguntas a formato JSON. La
 * salida consiste de un array de objetos JSON con el siguiente formato:
 *
 * <code>
 * {
 *   "question": "Pregunta",
 *   "category": "Categoría",
 *   "correctAnswer": "Respuesta correcta",
 *   "wrongAnswers": [
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(preguntas);
    }
}
