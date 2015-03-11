package es.uniovi.asw.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Graph;
import es.uniovi.asw.model.Player;
import es.uniovi.asw.model.Pregunta;
import es.uniovi.asw.model.Tablero;
import es.uniovi.asw.questions.MongoQuestions;

public class Game {

	private MongoQuestions mongoQuestions = new MongoQuestions();

	private List<Player> players = new ArrayList<Player>();
	private Graph<Casilla> tablero;
	private Integer jugadorActivo=null;

	public static void main(String[] args) {
		System.out.println("Juego");
	}

	public Game(int var) {
		tablero = Tablero.getTablero(var);
	}	
	
	public Graph<Casilla> getTablero() {
		return tablero;
	}

	/**
	 * Metodo que añade un jugador a la partida y lo coloca en la casilla de salida
	 * @param p jugador nuevo.
	 */
	public void addPlayer(Player p) {
		if(jugadorActivo==null)
			jugadorActivo=players.size();
		players.add(p);
		p.setPosition(tablero.getNode(0));
		
	}
	
	/**
	 * Devuelve una lista con los posibles movimientos del jugador
	 * @param p el jugador
	 * @param steps numero de pasos
	 * @return lista de casillas posibles
	 */
	public List<Casilla> getPossibleMoves(Player p, int steps) {
		return tablero.getNodesDestino(p.getPosition(), steps);
	}

	/**
	 * Cambia la posicion de un jugador al final del turno
	 * @param player, jugador que va a cambiar de posicion
	 * @param c, nueva casilla en la que se va a situar el jugador.
	 */
	public void movePlayer(Player player, Casilla c) {
		player.setPosition(c);
	}

	/**
	 * Metodo que obtiene una pregunta a partir de una casilla
	 * @param c, casilla en la que se encuentra el jugador que necesita la pregunta
	 * @return pregunta acorde a la categoria de la casilla
	 */
	public Pregunta getPregunta(Casilla c) {
		return mongoQuestions.getQuestion(c.getCategoria());
	}

	/**
	 * Devuelve la tirada del dado aleatoriamente
	 * @return numero del dado entre 1 y 6
	 */
	public int getTirada() {
		return new Random().nextInt(6) + 1;
	}
	
	/**
	 * Metodo encargado de cambiar el jugador activo, el que pasara a ser el de la posicion siguiente del array 
	 * En caso de que no haya vuelve el turno al primero. 
	 * @return player, nuevo jugador activo. 
	 */ 
	public Player cambiarJugadorActivo(){
		this.jugadorActivo++;
		if(jugadorActivo>players.size()){
			jugadorActivo=0;
			return players.get(0);
		}
		return players.get(this.jugadorActivo);
	}
}