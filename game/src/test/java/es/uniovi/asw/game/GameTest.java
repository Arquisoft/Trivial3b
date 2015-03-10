package es.uniovi.asw.game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Graph;

public class GameTest {

	@Test
	public void test() {
		assertEquals(1, 1);

		Game game = new Game(1);

		Graph<Casilla> tablero = game.getTablero();

		Casilla c = tablero.getNode(0);

		List<Casilla> lista = tablero.getNodesDestino(c, 2.0);

		assertTrue(lista.contains(tablero.getNode(2)));
		assertTrue(lista.contains(tablero.getNode(40)));

		// Todavia no están los radios centrales
		// assertTrue(lista.contains(tablero.getNode(43)));

		List<Casilla> lista2 = tablero.getNodesDestino(c, 6.0);

		assertTrue(lista2.contains(tablero.getNode(6)));
		assertTrue(lista2.contains(tablero.getNode(36)));

		// Todavia no están los radios centrales
		// assertTrue(lista2.contains(tablero.getNode(72)));
	}
}
