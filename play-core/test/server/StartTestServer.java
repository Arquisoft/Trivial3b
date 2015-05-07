package server;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;
import static play.test.Helpers.testServer;

import java.util.HashMap;
import java.util.Map;

import play.test.TestServer;

public class StartTestServer {

	public static int PORT = 3333;
	private static TestServer TEST_SERVER;

	private static Map<String, String> db;

	public static TestServer initServer() {
		if (TEST_SERVER == null) {
			TEST_SERVER = testServer(PORT, fakeApplication(initDB()));
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					stopServer();
				}
			});

			startServer();
		}
		return TEST_SERVER;
	}

	public static void startServer() {
		start(TEST_SERVER);
	}

	public static void stopServer() {
		stop(TEST_SERVER);
	}

	public static Map<String, String> initDB() {
		if (db == null) {
			Map<String, String> params = new HashMap<>();
			params.put("DB_CLOSE_DELAY", "-1");

			db = new HashMap<>(inMemoryDatabase("default", params));
			db.putAll(inMemoryDatabase("default", params));
			db.put("evolutionplugin", "enabled");
			db.put("applyEvolutions.default", "true");
		}

		return db;
	}
}
