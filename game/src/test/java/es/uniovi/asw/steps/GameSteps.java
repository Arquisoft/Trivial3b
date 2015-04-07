package es.uniovi.asw.steps;

import static org.junit.Assert.assertEquals;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import es.uniovi.asw.controller.game.GameFactory;
import es.uniovi.asw.controller.game.GameService;
import es.uniovi.asw.modelo.model.Player;

public class GameSteps {

	private GameService game;

	@Dado("^un juego creado$")
	public void un_juego_creado() throws Throwable {
		game = GameFactory.newGameService();
	}

	@Cuando("^creo un jugador de nombre \"(.*?)\"$")
	public void creo_un_jugador_de_nombre(String nombre) throws Throwable {
		Player player = new Player(nombre);
		game.addPlayer(player);
	}

	@Entonces("^el jugador actual es \"(.*?)\"$")
	public void el_jugador_actual_es(String nombre) throws Throwable {
		assertEquals(nombre, game.getCurrentTurnPlayer().getUsername());
	}
}
