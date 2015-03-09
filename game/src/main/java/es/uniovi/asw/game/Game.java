package es.uniovi.asw.game;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Graph;
import es.uniovi.asw.model.Player;
import es.uniovi.asw.model.Pregunta;
import es.uniovi.asw.model.Tablero;

public class Game {
	
	private List<Player> players = new ArrayList<Player>();;
	private Graph tablero;
	
	public static void main(String[] args) {
		System.out.println("Juego");
	}
	
	public Game(int var){
		tablero =Tablero.getTablero(var);
	}
	
	
	/**
	 * Metodo que añade un jugador a l apartida y lo coloca en la casilla de salida
	 * @param p jugador nuevo. 
	 */
	public void addPlayer(Player p){
		players.add(p);
		p.setPosition((Casilla) tablero.getNodes()[0]);
	}
	
	
	
	/**
	 * Cambia la posicion de un jugador al final del turno
	 * @param player, jugador que va  a cambiar de posicion
	 * @param c, nueva casilla en la que se va a situar el jugador. 
	 */
	public void movePlayer(Player player, Casilla c){
		player.setPosition(c);
		
	}
	
	/**
	 * Metodo que obtiene una pregunta a partir de una casilla
	 * @param c, casilla en la que se encuentra el jugador que necesita la pregunta
	 * @return pregunta acorde a la categoria de la casilla
	 */
	public Pregunta getPregunta(Casilla c){
		
		//crear el dao y obtener objeto pregunta a partir de la categoria
		c.getCategoria();
		return null;
	}
}