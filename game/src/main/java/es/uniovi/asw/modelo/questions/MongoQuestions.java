package es.uniovi.asw.modelo.questions;

import java.net.UnknownHostException;
import java.util.Random;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

import es.uniovi.asw.modelo.model.Category;
import es.uniovi.asw.modelo.model.Pregunta;

public class MongoQuestions {
    
    private static final String DEFAULT_MONGO = "mongodb://user:av65~w@ds037571.mongolab.com:37571/trivial3b-preguntas";
    private String server;

    public MongoQuestions() {
        this(DEFAULT_MONGO);
    }

    public MongoQuestions(String server) {
        this.server = server;
    }

    public Pregunta getQuestion(Category categ) {
        try {
            // Creamos la conexion
            MongoClientURI uri = new MongoClientURI(server);
            MongoClient mongo = new MongoClient(uri);

            // Obtenemos la base de datos y la coleccion
            DB db = mongo.getDB(uri.getDatabase());
            DBCollection collection = db.getCollection("questions");

            // Creamos un objeto para buscar por categoría
            BasicDBObject query = new BasicDBObject("category", categ.name());

            // Obtenemos un numero al azar entre 0 y el número de preguntas
            int count = (int) collection.count(query);
            int toSkip = new Random().nextInt(count);

            // Obtenemos la pregunta en la posicion dada
            DBObject dbo = collection.find(query).skip(toSkip).next();

            // Y la parseamos desde JSON
            Pregunta pregunta = new Gson().fromJson(dbo.toString(), Pregunta.class);
            pregunta.setId(dbo.get("_id").toString());

            return pregunta;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
