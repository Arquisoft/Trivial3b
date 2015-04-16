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
	
	@Cuando("^\"(.*?)\" consigue el ultimo quesito$")
	public void consigue_el_ultimo_quesito(String nombre) throws Throwable {
		Player player = new Player(nombre);
		game.addPlayer(player);
		game.getGanador();
	}

	@Entonces("^el ganador es \"(.*?)\"$")
	public void el_ganador_es(String nombre) throws Throwable {
		assertEquals(nombre, game.getCurrentTurnPlayer().getUsername());
	}
	
	@Cuando("^un jugador falla una pregunta$")
	public void un_jugador_falla_una_pregunta() throws Throwable {
		game.respuestaIncorrecta();
	}

	@Entonces("^le toca tirar al otro jugador$")
	public void le_toca_tirar_al_otro_jugador() throws Throwable {
		assertEquals(null, game.throwDice());		
	}
	
	@Dado("^una lista vacia de jugadores$")
	public void una_lista_vacia_de_jugadores() throws Throwable {
		game = GameFactory.newGameService();
		game.getPlayers().isEmpty();
	}

	@Cuando("^creo un usuario \"(.*?)\"$")
	public void creo_un_usuario(String nombre) throws Throwable {
		Player player = new Player(nombre);
		game.addPlayer(player);
	}

	@Entonces("^el numero de usuarios es \"(.*?)\"$")
	public void el_numero_de_usuarios_es(int n) throws Throwable {
		assertEquals(n, game.getPlayers().size());		
	}
	
	@Cuando("^un jugador tira el dado$")
	public void un_jugador_tira_el_dado() throws Throwable {
	    game.throwDice();
	}

	@Entonces("^le toca mover la ficha$")
	public void le_toca_mover_la_ficha() throws Throwable {
		assertEquals(null, game.getMoves());	
	}
}