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
	 * Metodo que añade un jugador a l apartida y lo coloca en la casilla de salida
	 * @param p jugador nuevo.
	 */
	public void addPlayer(Player p) {
		players.add(p);
		p.setPosition(tablero.getNode(0));
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
}