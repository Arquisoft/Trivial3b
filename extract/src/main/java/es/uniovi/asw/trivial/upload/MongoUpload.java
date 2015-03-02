package es.uniovi.asw.trivial.upload;

import java.net.UnknownHostException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * Esta clase se encarga del guardado del JSON en la base de datos MongoDB
 *
 * A continuación está explicado como hacer que funcione MongoDB
 *
 * <ul>
 * <li>Desde http://www.mongodb.org/downloads descargar el .zip y extraerlo
 * <li>Dentro de la carpeta extraida, crear una carpeta data al lado de la
 * carpeta bin ya existente
 * <li>entrar en bin y abrir la consola de comandos
 * <li>ejecutar: mongod.exe --dbpath=../data/
 * <li>Ahora mongo ya está funcionando y es posible ejecutar los test
 * </ul>
 *
 * Para ver el estado de la base de datos, se puede usar la GUI
 * http://edgytech.com/umongo/
 *
 * @author Daniel García García
 *
 */
public class MongoUpload implements Uploader {

    private static final String DEFAULT_MONGO = "localhost:27017";
    private String server = DEFAULT_MONGO;

    public MongoUpload(String server) {
        if (server != null) {
            this.server = server;
        }
    }

    @Override
    public void upload(String content) {
        try {
            // Creamos la conexion
            MongoClient mongo = new MongoClient(server);

            // Obtenemos la base de datos y la coleccion
            DB db = mongo.getDB("trivial");
            DBCollection collection = db.getCollection("questions");

            // Creamos un indice para que no halla preguntas repetidas
            BasicDBObject key = new BasicDBObject("question", 1);
            collection.createIndex(key, "question", true);

            // Obtenemos la lista de objetos a partir del JSON
            BasicDBList dbList = (BasicDBList) JSON.parse(content);

            // Y los insertamos, si están repetidas, damos un aviso
            for (Object object : dbList) {
                try {
                    collection.insert((DBObject) object);
                } catch (DuplicateKeyException e) {
                    System.err.println("Pregunta duplicada, saltando: "
                            + ((BasicDBObject) object).getString("question"));
                }
            }

            // Para comprobar que funciona, recorremos la coleccion y mostramos
            // la salida
            DBCursor cursorDoc = collection.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
