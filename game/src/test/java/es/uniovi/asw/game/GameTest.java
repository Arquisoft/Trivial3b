package es.uniovi.asw.game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.controller.game.Casilla;
import es.uniovi.asw.controller.game.Tablero;
import es.uniovi.asw.controller.graph.Graph;

public class GameTest {

    @Test
    public void test() {
        Graph<Casilla> tablero = Tablero.getTablero(Tablero.CIRCULAR);

        Casilla c = tablero.getNode(0);

        // Movimiento 1
        List<Casilla> lista = tablero.getNodesDestino(c, 2.0);

        assertEquals(3, lista.size());

        assertTrue(lista.contains(tablero.getNode(2)));
        assertTrue(lista.contains(tablero.getNode(40)));

        assertTrue(lista.contains(tablero.getNode(43)));

        // Movimiento 2
        List<Casilla> lista2 = tablero.getNodesDestino(c, 6.0);

        assertTrue(lista2.contains(tablero.getNode(6)));
        assertTrue(lista2.contains(tablero.getNode(36)));

        assertTrue(lista2.contains(tablero.getNode(72)));

        // Movimiento 3
        Casilla c2 = tablero.getNode(70);
        List<Casilla> lista3 = tablero.getNodesDestino(c2, 5.0);

        assertEquals(7, lista3.size());

        assertTrue(lista3.contains(tablero.getNode(34)));
        assertTrue(lista3.contains(tablero.getNode(36)));
        assertTrue(lista3.contains(tablero.getNode(44)));
        assertTrue(lista3.contains(tablero.getNode(49)));
        assertTrue(lista3.contains(tablero.getNode(54)));
        assertTrue(lista3.contains(tablero.getNode(59)));
        assertTrue(lista3.contains(tablero.getNode(64)));

        // Tablero cuadrado
        Graph<Casilla> tableroCuadrado = Tablero.getTablero(2);

        Casilla cc = tableroCuadrado.getNode(0);

        // Movimiento 1
        List<Casilla> listaC = tableroCuadrado.getNodesDestino(cc, 2.0);

        assertEquals(2, listaC.size());

        assertTrue(listaC.contains(tableroCuadrado.getNode(2)));
    }
}
