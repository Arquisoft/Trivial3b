package cucumber.steps;

import static org.junit.Assert.*;
import game.GameFactory;
import game.GameService;
import models.Player;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import cucumber.api.java.Before;
import play.test.TestBrowser;
import play.test.TestServer;
import static play.test.Helpers.*;

public class GameSteps {
	
    public static int PORT = 3334;
    private static TestServer TEST_SERVER;
    private static boolean initialised = false;

    @Before
    public void before() {
        if (!initialised) {
            TEST_SERVER = testServer(PORT, fakeApplication(inMemoryDatabase()));
            start(TEST_SERVER);
            initialised = true;
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    TEST_SERVER.stop();
                }
            });
        }
    }

	private GameService game;

	@Dado("^un juego creado$")
	public void un_juego_creado() throws Throwable {
		game = GameFactory.newGameService();
	}

	@Cuando("^creo un jugador de nombre \"(.*?)\" y pass \"(.*?)\"$")
	public void creo_un_jugador_de_nombre(String nombre, String pass) throws Throwable {
		Player player = new Player(nombre, pass);
		game.addPlayer(player);
	}

	@Entonces("^el jugador actual es \"(.*?)\"$")
	public void el_jugador_actual_es(String nombre) throws Throwable {
		assertEquals(nombre, game.CurrentTurnPlayer().getId());
	}
	
	@Entonces("^el jugador actual no es \"(.*?)\"$")
	public void el_jugador_actual_o_es(String nombre) throws Throwable {
		assertNotEquals(nombre, game.CurrentTurnPlayer().getId());
	}

	@Cuando("^elijo el tablero \"(.*?)\"$")
	public void elijo_el_tablero(int tipo) throws Throwable {
		game.setTablero(tipo);
	}

	@Entonces("^el tablero actual es \"(.*?)\"$")
	public void el_tablero_actual_es(String nombre) throws Throwable {
		assertEquals(nombre, game.getTipo().toString());
	}

	@Cuando("^un jugador acierta o falla una pregunta$")
	public void un_jugador_acierta_o_falla_una_pregunta() throws Throwable {
		game.respuestaCorrecta();
		game.respuestaIncorrecta();
	}

	@Entonces("^se almacena esa informacion$")
	public void se_almacena_esa_informacion() throws Throwable {
		assertEquals(true, game.isSaveStats());
	}

	@Cuando("^un jugador acierta una pregunta$")
	public void un_jugador_acierta_una_pregunta() throws Throwable {
		game.respuestaCorrecta();
	}

	@Entonces("^se comprueba si ha ganado la partida$")
	public void se_comprueba_si_ha_ganado_la_partida() throws Throwable {
		assertEquals(null, game.getWinner());
	}

	@Entonces("^le toca tirar otra vez$")
	public void le_toca_tirar_otra_vez() throws Throwable {
		assertEquals(true, game.canThrowDice());
	}
}