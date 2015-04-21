package game;

import java.util.List;

import models.Player;
import models.Pregunta;
import controllers.MongoQuestions;



public interface GameService {

	/**
	 * Devuelve true si la partida ya finalizo
	 */
	boolean partidaFinalizada();
	public List<Casilla> getMoves();
	/**
	 * Devuelve al ganador si lo hay, o null si no
	 */
	int getDiceNumber();
	/**
	 * Metodo que añade un jugador a la partida y lo coloca en la casilla de
	 * salida
	 *
	 * @param player
	 *            jugador nuevo.
	 */
	boolean addPlayer(Player player);

	/**
	 * Devuelve una lista inmutable de los jugadores
	 */
	List<Player> getPlayers();

	/**
	 * Devuelve el jugador del turno actual, o null si no hay jugadores
	 */
	Player CurrentTurnPlayer();

	/**
	 * Indica si el jugador actual puede tirar el dado
	 */
	boolean canThrowDice();

	/**
	 * Tira el dado y devuelve el valor para mostrarlo (entre 1 y 6).
	 *
	 * Para obtener la lista de posibles movimientos llamar a
	 * {@link #move()}
	 */
	Integer throwDice();

	/**
	 * Devuelve si el jugador actual se puede mover
	 */
	boolean canMove();

	/**
	 * Obtiene la lista de movimientos posibles para el jugador actual, o null
	 * si no es el momento de mover
	 *
	 */
	List<Casilla> move();

	/**
	 * Cambia la posicion de un jugador al final del turno, devuelve true si es
	 * posible el movimiento
	 *
	 * @param casilla
	 *            , nueva casilla en la que se va a situar el jugador.
	 */
	boolean moveTo(Casilla casilla);

	/**
	 * Metodo que obtiene una pregunta a partir de una casilla, o null si no es
	 * posible en este momento
	 */
	

	/**
	 * Llamar a este metodo cuando la respuesta sea correcta
	 */
	void respuestaCorrecta();

	boolean userExists(String user, String pass);

	boolean isAdmin(String user, String pass);

	/**
	 * Llamar a este metodo cuando la respuesta sea incorrecta
	 */
	void respuestaIncorrecta();

	boolean registerUser(String user, String pass);

	Casilla getCasilla(int i);
	
	

	public MongoQuestions getMongoQuestions();

	public void setMongoQuestions(MongoQuestions mongoQuestions);
	public int getActivePlayer();

	public void setActivePlayer(int activePlayer);

	public boolean isDiceThrown();
	public void setDiceThrown(boolean diceThrown);

	public Pregunta getQuestionGiven();

	public void setQuestionGiven(Pregunta questionGiven);

	public Graph<Casilla> getTablero();

	public Player getWinner();

	public boolean isSaveStats();

	public void setPlayers(List<Player> players);

	public void setDiceNumber(int diceNumber);

}
