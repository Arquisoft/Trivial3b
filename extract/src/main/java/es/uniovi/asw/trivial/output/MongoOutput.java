package es.uniovi.asw.trivial.output;

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
 * - Desde http://www.mongodb.org/downloads descargar el .zip y extraerlo
 * - Dentro de la carpeta extraida, crear una carpeta data al lado de la carpeta bin ya existente
 * - entrar en bin y abrir la consola de comandos
 * - ejecutar: mongod.exe --dbpath=../data/
 * - Ahora mongo ya está funcionando y es posible ejecutar los test
 * 
 * Para ver el estado de la base de datos, se puede usar la GUI http://edgytech.com/umongo/
 * 
 * @author Daniel García García
 *
 */
public class MongoOutput implements Output {

	private static final String MONGO_URL = "localhost";
	private static final int MONGO_PORT = 27017;

	@Override
	public void save(String out) {
		try {
			// Creamos la conexion
			MongoClient mongo = new MongoClient(MONGO_URL, MONGO_PORT);

			// Obtenemos la base de datos y la coleccion
			DB db = mongo.getDB("trivial");
			DBCollection collection = db.getCollection("questions");

			// Creamos un indice para que no halla preguntas repetidas
			collection.createIndex(new BasicDBObject("question", 1), "question", true);

			// Obtenemos la lista de objetos a partir del JSON
			BasicDBList dbList = (BasicDBList) JSON.parse(out);

			// Y los insertamos, si están repetidas, damos un aviso
			for (Object object : dbList) {
				try {
					collection.insert((DBObject) object);
				} catch (DuplicateKeyException e) {
					System.err.println("Pregunta duplicada, saltando");
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
