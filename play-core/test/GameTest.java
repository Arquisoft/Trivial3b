import static org.junit.Assert.*;
import game.Casilla;
import game.GameService;
import game.GameServiceImpl;
import game.Graph;
import game.Tablero;

import java.util.List;

import models.Player;

import org.junit.Test;


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
    }
    
    /**
     * Prueba de la clase GameServiceImpl
     */
  /*  @Test 
    public void testGameService(){
    	//creamos el objeto juego, con el tablero circular
    	GameService game = new GameServiceImpl(1);
    	
    	for(int i=1;i<7;i++){
    			assertTrue(game.addPlayer(new Player("jugador"+i,"jugador"+i)));
    			assertEquals(i, game.getPlayers().size());
    	}
    	
    	assertFalse(game.addPlayer(new Player("error","error")));
	
    }*/
    
    
}
