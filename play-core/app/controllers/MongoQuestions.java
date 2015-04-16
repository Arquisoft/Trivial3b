package controllers;

import game.Category;

import java.util.Random;

import models.Pregunta;
import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.DBQuery.Query;
import net.vz.mongodb.jackson.JacksonDBCollection;
import play.libs.Json;
import play.modules.mongodb.jackson.MongoDB;
import play.mvc.Controller;
import play.mvc.Result;

public class MongoQuestions extends Controller {

    private static JacksonDBCollection<Pregunta, String> getCollection() {
        return MongoDB.getCollection("questions", Pregunta.class, String.class);
    }

    public static Pregunta getRandomQuestion() {
        // Creamos la coleccion
        JacksonDBCollection<Pregunta, String> col = getCollection();

        // Obtenemos un numero al azar entre 0 y el número de preguntas
        int count = (int) col.count();
        int toSkip = new Random().nextInt(count);

        return col.find().skip(toSkip).next();
    }

    public static Pregunta getRandomQuestion(Category cat) {
        // Creamos la coleccion
        JacksonDBCollection<Pregunta, String> col = getCollection();

        // Creamos un objeto para buscar por categoría
        Query query = DBQuery.is("category", cat.name());

        // Obtenemos un numero al azar entre 0 y el número de preguntas
        int count = (int) col.count(query);
        int toSkip = new Random().nextInt(count);

        return col.find(query).skip(toSkip).next();
    }

    public static Result get() {
        return ok(Json.toJson(getRandomQuestion()));
    }
}
