package es.uniovi.asw.game;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.graph.Graph;
import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Tablero;

public class GameTest {

	@Test
	public void test() {
		Graph<Casilla> tablero = Tablero.getTablero(1);

		Casilla c = tablero.getNode(10);

		List<Casilla> lista = tablero.getNodesDestino(c, 2.0);

		assertTrue(lista.contains(tablero.getNode(2)));
		assertTrue(lista.contains(tablero.getNode(40)));

		// Todavia no están los radios centrales
		// assertTrue(lista.contains(tablero.getNode(43)));

		List<Casilla> lista2 = tablero.getNodesDestino(c, 6.0);

		assertTrue(lista2.contains(tablero.getNode(4)));
		assertTrue(lista2.contains(tablero.getNode(16)));

		// Todavia no están los radios centrales
		// assertTrue(lista2.contains(tablero.getNode(72)));
	}
}
