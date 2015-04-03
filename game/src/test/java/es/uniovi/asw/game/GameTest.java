package es.uniovi.asw.game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.graph.Graph;
import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Tablero;

public class GameTest {

    @Test
    public void test() {
        Graph<Casilla> tablero = Tablero.getTablero(1);

<<<<<<< HEAD
		Casilla c = tablero.getNode(10);
=======
        Casilla c = tablero.getNode(0);
>>>>>>> 1ba9e3a55b39a9418e64149f3312c3d1cd218baa

        // Movimiento 1
        List<Casilla> lista = tablero.getNodesDestino(c, 2.0);

        assertEquals(3, lista.size());

        assertTrue(lista.contains(tablero.getNode(2)));
        assertTrue(lista.contains(tablero.getNode(40)));

        assertTrue(lista.contains(tablero.getNode(43)));

<<<<<<< HEAD
		assertTrue(lista2.contains(tablero.getNode(4)));
		assertTrue(lista2.contains(tablero.getNode(16)));
=======
        // Movimiento 2
        List<Casilla> lista2 = tablero.getNodesDestino(c, 6.0);
>>>>>>> 1ba9e3a55b39a9418e64149f3312c3d1cd218baa

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
        
        Graph<Casilla> tableroCuadrado = Tablero.getTablero(2);

        Casilla cc = tableroCuadrado.getNode(0);

        // Movimiento 1
        List<Casilla> listaC = tableroCuadrado.getNodesDestino(cc, 2.0);

        assertEquals(2, listaC.size());

        assertTrue(listaC.contains(tableroCuadrado.getNode(2)));

    }
}
