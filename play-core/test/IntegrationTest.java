import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import game.Casilla;
import game.GameFactory;
import game.GameService;

import java.util.ArrayList;
import java.util.List;

import models.Player;

import org.junit.Test;

import play.libs.F.Callback;
import play.test.TestBrowser;

public class IntegrationTest {

	/**
	 * add your integration test here in this example we just check if the
	 * welcome page is being shown
	 */
	@Test
	public void test() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						browser.goTo("http://localhost:3333");
						assertThat(browser.pageSource()).contains("<html>");
						// browser.$("Registrarse").click();
						// browser.click("Registrarse");
						// assertThat(browser.url()).isEqualTo("http://localhost:3333/register");
					}
				});
	}

	@Test
	public void testGameService() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {

						GameService game = GameFactory.newGameService(1);
						assertNull(game.CurrentTurnPlayer());

						for (int i = 1; i < 7; i++) {
							assertTrue(game.addPlayer(new Player("jugador" + i,
									"jugador" + i)));
							assertEquals(i, game.getPlayers().size());
						}

						assertFalse(game
								.addPlayer(new Player("error", "error")));

						assertEquals(game.getPlayers().get(0),
								game.CurrentTurnPlayer());

						// comprobamos qu el partida no finalizo
						assertFalse(game.partidaFinalizada());
						// comprobamos qu eel jugador actual puede tirar el dado
						assertTrue(game.canThrowDice());

						// Tiramos el dado
						int a = game.throwDice();
						assertTrue(0 <= a);
						assertTrue(6 >= a);

						game.setDiceNumber(1);
						assertFalse(game.canThrowDice());
						assertNull(game.throwDice());

						// tratamos de movernos por el tablero
						assertTrue(game.canMove());

						List<Casilla> lista = game.move();
						assertFalse(lista == null);
						assertEquals(lista, game.getMoves());

						assertFalse(game.moveTo(new Casilla()));
						assertTrue(game.moveTo(lista.get(0)));

						assertEquals(game.getAnswers().size(), 4);
						game.respuestaCorrecta();
						Player p = game.CurrentTurnPlayer();
						// comprobamos que no tiene quesitos
						assertEquals(p.numeroQuesitos(), 0);
						game.respuestaIncorrecta();
						assertTrue(p.equals(game.CurrentTurnPlayer()));

						// volvemos a tirar y a fallar para que finalice el
						// turno
						game.throwDice();
						game.setDiceNumber(2);
						lista = game.move();
						assertTrue(game.moveTo(lista.get(0)));
						assertEquals(game.getAnswers().size(), 4);

						game.respuestaIncorrecta();

						// creamos un juego con el otro tablero
						GameService game2 = GameFactory.newGameService(2);
						List<Player> jugadores = new ArrayList<Player>();

						for (int i = 1; i < 7; i++) {
							jugadores.add(new Player("jugador" + i, "jugador"
									+ i));
						}
						game2.setPlayers(jugadores);
						assertEquals(jugadores.size(), game2.getPlayers()
								.size());
					}

				});
	}

}
