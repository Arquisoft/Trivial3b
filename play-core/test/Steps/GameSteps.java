public class GameSteps {

	private GameService game;

	@Dado("^un juego creado$")
	public void un_juego_creado() throws Throwable {
		game = GameFactory.newGameService();
	}
	
	@Cuando("^creo un jugador de nombre \"(.*?)\" y pass \"(.*?)\" $")
	public void creo_un_jugador_de_nombre(String nombre, String pass) throws Throwable {
		Player player = new Player(nombre, pass);
		game.addUser(player);
	}

	@Entonces("^el jugador actual es \"(.*?)\"$")
	public void el_jugador_actual_es(String nombre) throws Throwable {
		assertEquals(nombre, game.CurrentTurnPlayer().getId());
	}
	
	@Cuando("^elijo el tablero \"(.*?)\" $")
	public void elijo_el_tablero(int tipo) throws Throwable {
		game.setTablero(tipo);
	}

	@Entonces("^el tablero actual es \"(.*?)\"$")
	public void el_tablero_actual_es(String nombre) throws Throwable {
		assertEquals(tipo, game.getTipo());
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
	public void un jugador acierta una pregunta() throws Throwable {
		game.respuestaCorrecta();		
	}

	@Entonces("^se comprueba si ha ganado la partida$")
	public void se comprueba si ha ganado la partida() throws Throwable {
		assertEquals(null, game.getWinner());
	}
	
	@Entonces("^le toca tirar otra vez$")
	public void le_toca_tirar_otra_vez() throws Throwable {
		assertEquals(true, game.canThrowDice());
	}
	
	@Cuando("^un jugador no esta activo$")
	public void un jugador acierta una pregunta() throws Throwable {
		game.getActivePlayer();		
	}

	@Entonces("^no puede mover la ficha$")
	public void se comprueba si ha ganado la partida() throws Throwable {
		assertEquals(false, game.canMove());
	}		
}