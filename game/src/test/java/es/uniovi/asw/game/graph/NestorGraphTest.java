package es.uniovi.asw.game.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.uniovi.asw.model.Graph;

public class NestorGraphTest {
    int tam = 11;
    Graph<Integer> g = new Graph<Integer>(tam);

    // Graph g = new Graph();

    @Test
    public void testInsercionesNodos() {
        for (int i = 1; i <= 10; i++) {
            assertEquals("addNode(" + i + ")", 0, g.addNode(i));
            assertEquals("addNode(" + i + ")", -1, g.addNode(i));
        }
        for (int i = 10; i > 0; i--) {
            assertEquals("addNode(" + i + ")", -1, g.addNode(i));
        }

        for (int i = 1; i <= 10; i++) {
            assertTrue("existsNode(" + i + ")", g.existNode(i));
            assertFalse("existsNode(" + i + ")", g.existNode(i + 10));
        }
    }

    private void insercionesAristas() {
        int arista = 14;
        for (int i = 2; i <= 4; i++) {
            assertEquals("addEdge(1," + i + ")", 0, g.addEdge(1, i, arista--));
        }

        for (int i = 5; i <= 6; i++) {
            assertEquals("addEdge(2," + i + ")", 0, g.addEdge(2, i, arista--));
        }

        for (int i = 7; i <= 8; i++) {
            assertEquals("addEdge(3," + i + ")", 0, g.addEdge(3, i, arista--));
        }

        assertEquals(0, g.addEdge(5, 6, arista--));

        for (int i = 9; i <= 10; i++) {
            assertEquals("addEdge(5," + i + ")", 0, g.addEdge(5, i, arista--));
        }

        assertEquals(0, g.addEdge(6, 1, arista--));
        assertEquals(0, g.addEdge(7, 8, arista--));
        assertEquals(0, g.addEdge(8, 1, arista--));
        assertEquals(0, g.addEdge(9, 10, arista--));
        assertEquals(0, g.addEdge(10, 1, 20));

        for (int i = 10; i > 0; i--) {
            assertEquals("addEdge(10," + i + ")", -1,
                    g.addEdge(10, 10 + i, i + 30));
        }

    }

    private void modificacionAristas() {
        double tmp = g.getEdge(3, 8);
        assertEquals(8, tmp, 0.001);
        assertEquals(0, g.addEdge(3, 8, 100 + g.getEdge(3, 8)));
        assertEquals(108, g.getEdge(3, 8), 0.001);
    }

    private void borradoAristas() {
        assertEquals(0, g.addNode(11));
        for (int i = 1; i <= 10; i++) {
            assertEquals("removeEdge 11-" + i, -1, g.removeEdge(11, i));
            assertEquals("getEdge 11-" + i, -1, g.getEdge(11, i), 0.001);
            assertEquals("addEdge 11-" + i, 0, g.addEdge(11, i, 50));
            assertEquals("getEdge 11-" + i, 50, g.getEdge(11, i), 0.001);
            assertEquals("removeEdge 11-" + i, 0, g.removeEdge(11, i));
            assertEquals("removeEdge 11-" + i, -1, g.removeEdge(11, i));
        }

        for (int i = 2; i < 10; i++) {
            //
            assertEquals("removeEdge 10-" + i, -1, g.removeEdge(10, i));
            assertEquals("getEdge 10-" + i, -1, g.getEdge(10, i), 0.001);
            assertEquals("addEdge 10-" + i, 0, g.addEdge(10, i, 50));
            assertEquals("getEdge 10-" + i, 50, g.getEdge(10, i), 0.001);
            assertEquals("removeEdge 10-" + i, 0, g.removeEdge(10, i));
            assertEquals("removeEdge 10-" + i, -1, g.removeEdge(10, i));
        }
    }

    private void borradoNodos() {
        assertEquals(0, g.removeNode(11));
        assertEquals(-1, g.removeNode(11));
        assertArrayEquals(new boolean[][] {
                { false, true, true, true, false, false, false, false, false,
                        false, false },
                { false, false, false, false, true, true, false, false, false,
                        false, false },
                { false, false, false, false, false, false, true, true, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { false, false, false, false, false, true, false, false, true,
                        true, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, true, false,
                        false, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, true, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false } }, g.getEdges());

        assertArrayEquals(new double[][] {
                { 0, 14, 13, 12, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 11, 10, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 9, 108, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 7, 0, 0, 6, 5, 0 },
                { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, g.getWeight());

        assertEquals(0, g.removeNode(1));
        assertEquals(-1, g.removeNode(1));

        assertArrayEquals(new boolean[][] {
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { false, false, false, false, true, true, false, false, false,
                        false, false },
                { false, false, false, false, false, false, true, true, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { true, false, false, false, false, true, false, false, true,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { false, false, false, false, false, false, false, true, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false } }, g.getEdges());

        assertArrayEquals(new double[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 11, 10, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 9, 108, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 5, 0, 0, 0, 0, 7, 0, 0, 6, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, g.getWeight());

        for (int i = 2; i <= 10; i++) {
            assertEquals("removeNode(" + i + ")", 0, g.removeNode(i));
        }
        for (int i = 1; i <= 11; i++) {
            assertEquals("removeNode(" + i + ")", -1, g.removeNode(i));
        }
    }

    @Test
    public void test1() {
        testInsercionesNodos();
        insercionesAristas();
        modificacionAristas();
        assertArrayEquals(new boolean[][] {
                { false, true, true, true, false, false, false, false, false,
                        false, false },
                { false, false, false, false, true, true, false, false, false,
                        false, false },
                { false, false, false, false, false, false, true, true, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false },
                { false, false, false, false, false, true, false, false, true,
                        true, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, true, false,
                        false, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, true, false },
                { true, false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false, false } }, g.getEdges());

        assertArrayEquals(new double[][] {
                { 0, 14, 13, 12, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 11, 10, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 9, 108, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 7, 0, 0, 6, 5, 0 },
                { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, g.getWeight());

        borradoAristas();
        borradoNodos();

    }
}
