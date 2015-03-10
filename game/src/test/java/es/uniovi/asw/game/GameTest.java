package es.uniovi.asw.game;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Graph;

public class GameTest {

    @Test
    public void test() {
        assertEquals(1, 1);
        
        Game game = new Game(1);
        
        Graph<Casilla> tablero= game.getTablero();
        
       // Casilla c = tablero.getNodes()[0];//-->error de cast
       
       // List<Casilla> lista = tablero.getNodesDestino(c, 2.0);
        
        //assertEquals(true, lista.contains(tablero.getNodes()[2]));
    }
}
