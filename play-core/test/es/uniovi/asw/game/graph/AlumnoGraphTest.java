package es.uniovi.asw.game.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.Graph;

import org.junit.Test;

public class AlumnoGraphTest {
    double infinito = Double.POSITIVE_INFINITY;

    int tam = 11;
    Graph<Integer> g = new Graph<Integer>(tam);

    public void testInsercionesNodos() {
        assertEquals(0, g.addNode(1));
        assertEquals(-1, g.addNode(1));
        assertEquals(0, g.addNode(2));
        assertEquals(-1, g.addNode(2));
        assertEquals(0, g.addNode(3));
        assertEquals(-1, g.addNode(3));
        assertEquals(0, g.addNode(4));
        assertEquals(-1, g.addNode(4));
        assertEquals(0, g.addNode(5));
        assertEquals(-1, g.addNode(5));
        assertEquals(0, g.addNode(6));
        assertEquals(-1, g.addNode(6));
        assertEquals(0, g.addNode(7));
        assertEquals(-1, g.addNode(7));
        assertEquals(0, g.addNode(8));
        assertEquals(-1, g.addNode(8));
        assertEquals(0, g.addNode(9));
        assertEquals(-1, g.addNode(9));
        assertEquals(0, g.addNode(10));
        assertEquals(-1, g.addNode(10));
        assertEquals(-1, g.addNode(10));
        assertEquals(-1, g.addNode(9));
        assertEquals(-1, g.addNode(8));
        assertEquals(-1, g.addNode(7));
        assertEquals(-1, g.addNode(6));
        assertEquals(-1, g.addNode(5));
        assertEquals(-1, g.addNode(4));
        assertEquals(-1, g.addNode(3));
        assertEquals(-1, g.addNode(2));
        assertEquals(-1, g.addNode(1));
        assertTrue(g.existNode(1));
        assertFalse(g.existNode(11));
        assertTrue(g.existNode(2));
        assertFalse(g.existNode(12));
        assertTrue(g.existNode(3));
        assertFalse(g.existNode(13));
        assertTrue(g.existNode(4));
        assertFalse(g.existNode(14));
        assertTrue(g.existNode(5));
        assertFalse(g.existNode(15));
        assertTrue(g.existNode(6));
        assertFalse(g.existNode(16));
        assertTrue(g.existNode(7));
        assertFalse(g.existNode(17));
        assertTrue(g.existNode(8));
        assertFalse(g.existNode(18));
        assertTrue(g.existNode(9));
        assertFalse(g.existNode(19));
        assertTrue(g.existNode(10));
        assertFalse(g.existNode(20));

    }

    private void testInsercionesAristas() {
        assertEquals(0, g.addEdge(1, 2, 14));
        assertEquals(0, g.addEdge(1, 3, 13));
        assertEquals(0, g.addEdge(1, 4, 12));
        assertEquals(0, g.addEdge(2, 5, 11));
        assertEquals(0, g.addEdge(2, 6, 10));
        assertEquals(0, g.addEdge(3, 7, 9));
        assertEquals(0, g.addEdge(3, 8, 8));
        assertEquals(0, g.addEdge(5, 6, 7));
        assertEquals(0, g.addEdge(5, 9, 6));
        assertEquals(0, g.addEdge(5, 10, 5));
        assertEquals(0, g.addEdge(6, 1, 4));
        assertEquals(0, g.addEdge(7, 8, 3));
        assertEquals(0, g.addEdge(8, 1, 2));
        assertEquals(0, g.addEdge(9, 10, 1));
        assertEquals(0, g.addEdge(10, 1, 20));
        assertEquals(-1, g.addEdge(10, 20, 40));
        assertEquals(-1, g.addEdge(10, 19, 39));
        assertEquals(-1, g.addEdge(10, 18, 38));
        assertEquals(-1, g.addEdge(10, 17, 37));
        assertEquals(-1, g.addEdge(10, 16, 36));
        assertEquals(-1, g.addEdge(10, 15, 35));
        assertEquals(-1, g.addEdge(10, 14, 34));
        assertEquals(-1, g.addEdge(10, 13, 33));
        assertEquals(-1, g.addEdge(10, 12, 32));
        assertEquals(-1, g.addEdge(10, 11, 31));
    }

    private void testDijkstra(double[][] aux) {
        for (int i = 1; i <= 10; i++) {
            assertArrayEquals("Dijkstra " + i, (aux[i - 1]), g.dijkstra(i),
                    0.0001);
        }
    }

    private void testFloyd(int[][] aux) {
        assertArrayEquals(aux, g.floyd());
    }

    private void testmodificacionAristas() {
        double tmp = g.getEdge(3, 8);
        assertEquals(8, tmp, 0.001);
        assertEquals(0, g.addEdge(3, 8, 100 + g.getEdge(3, 8)));
        assertEquals(108, g.getEdge(3, 8), 0.001);
    }

    private void testBorradoAristas() {
        assertEquals(0, g.addNode(11));
        assertEquals(-1, g.removeEdge(11, 1));
        assertEquals(-1, g.getEdge(11, 1), 0.001);
        assertEquals(0, g.addEdge(11, 1, 50));
        assertEquals(50, g.getEdge(11, 1), 0.001);
        assertEquals(0, g.removeEdge(11, 1));
        assertEquals(-1, g.removeEdge(11, 1));
        assertEquals(-1, g.removeEdge(11, 2));
        assertEquals(-1, g.getEdge(11, 2), 0.001);
        assertEquals(0, g.addEdge(11, 2, 50));
        assertEquals(50, g.getEdge(11, 2), 0.001);
        assertEquals(0, g.removeEdge(11, 2));
        assertEquals(-1, g.removeEdge(11, 2));
        assertEquals(-1, g.removeEdge(11, 3));
        assertEquals(-1, g.getEdge(11, 3), 0.001);
        assertEquals(0, g.addEdge(11, 3, 50));
        assertEquals(50, g.getEdge(11, 3), 0.001);
        assertEquals(0, g.removeEdge(11, 3));
        assertEquals(-1, g.removeEdge(11, 3));
        assertEquals(-1, g.removeEdge(11, 4));
        assertEquals(-1, g.getEdge(11, 4), 0.001);
        assertEquals(0, g.addEdge(11, 4, 50));
        assertEquals(50, g.getEdge(11, 4), 0.001);
        assertEquals(0, g.removeEdge(11, 4));
        assertEquals(-1, g.removeEdge(11, 4));
        assertEquals(-1, g.removeEdge(11, 5));
        assertEquals(-1, g.getEdge(11, 5), 0.001);
        assertEquals(0, g.addEdge(11, 5, 50));
        assertEquals(50, g.getEdge(11, 5), 0.001);
        assertEquals(0, g.removeEdge(11, 5));
        assertEquals(-1, g.removeEdge(11, 5));
        assertEquals(-1, g.removeEdge(11, 6));
        assertEquals(-1, g.getEdge(11, 6), 0.001);
        assertEquals(0, g.addEdge(11, 6, 50));
        assertEquals(50, g.getEdge(11, 6), 0.001);
        assertEquals(0, g.removeEdge(11, 6));
        assertEquals(-1, g.removeEdge(11, 6));
        assertEquals(-1, g.removeEdge(11, 7));
        assertEquals(-1, g.getEdge(11, 7), 0.001);
        assertEquals(0, g.addEdge(11, 7, 50));
        assertEquals(50, g.getEdge(11, 7), 0.001);
        assertEquals(0, g.removeEdge(11, 7));
        assertEquals(-1, g.removeEdge(11, 7));
        assertEquals(-1, g.removeEdge(11, 8));
        assertEquals(-1, g.getEdge(11, 8), 0.001);
        assertEquals(0, g.addEdge(11, 8, 50));
        assertEquals(50, g.getEdge(11, 8), 0.001);
        assertEquals(0, g.removeEdge(11, 8));
        assertEquals(-1, g.removeEdge(11, 8));
        assertEquals(-1, g.removeEdge(11, 9));
        assertEquals(-1, g.getEdge(11, 9), 0.001);
        assertEquals(0, g.addEdge(11, 9, 50));
        assertEquals(50, g.getEdge(11, 9), 0.001);
        assertEquals(0, g.removeEdge(11, 9));
        assertEquals(-1, g.removeEdge(11, 9));
        assertEquals(-1, g.removeEdge(11, 10));
        assertEquals(-1, g.getEdge(11, 10), 0.001);
        assertEquals(0, g.addEdge(11, 10, 50));
        assertEquals(50, g.getEdge(11, 10), 0.001);
        assertEquals(0, g.removeEdge(11, 10));
        assertEquals(-1, g.removeEdge(11, 10));
        assertEquals(-1, g.removeEdge(10, 2));
        assertEquals(-1, g.getEdge(10, 2), 0.001);
        assertEquals(0, g.addEdge(10, 2, 50));
        assertEquals(50, g.getEdge(10, 2), 0.001);
        assertEquals(0, g.removeEdge(10, 2));
        assertEquals(-1, g.removeEdge(10, 2));
        assertEquals(-1, g.removeEdge(10, 3));
        assertEquals(-1, g.getEdge(10, 3), 0.001);
        assertEquals(0, g.addEdge(10, 3, 50));
        assertEquals(50, g.getEdge(10, 3), 0.001);
        assertEquals(0, g.removeEdge(10, 3));
        assertEquals(-1, g.removeEdge(10, 3));
        assertEquals(-1, g.removeEdge(10, 4));
        assertEquals(-1, g.getEdge(10, 4), 0.001);
        assertEquals(0, g.addEdge(10, 4, 50));
        assertEquals(50, g.getEdge(10, 4), 0.001);
        assertEquals(0, g.removeEdge(10, 4));
        assertEquals(-1, g.removeEdge(10, 4));
        assertEquals(-1, g.removeEdge(10, 5));
        assertEquals(-1, g.getEdge(10, 5), 0.001);
        assertEquals(0, g.addEdge(10, 5, 50));
        assertEquals(50, g.getEdge(10, 5), 0.001);
        assertEquals(0, g.removeEdge(10, 5));
        assertEquals(-1, g.removeEdge(10, 5));
        assertEquals(-1, g.removeEdge(10, 6));
        assertEquals(-1, g.getEdge(10, 6), 0.001);
        assertEquals(0, g.addEdge(10, 6, 50));
        assertEquals(50, g.getEdge(10, 6), 0.001);
        assertEquals(0, g.removeEdge(10, 6));
        assertEquals(-1, g.removeEdge(10, 6));
        assertEquals(-1, g.removeEdge(10, 7));
        assertEquals(-1, g.getEdge(10, 7), 0.001);
        assertEquals(0, g.addEdge(10, 7, 50));
        assertEquals(50, g.getEdge(10, 7), 0.001);
        assertEquals(0, g.removeEdge(10, 7));
        assertEquals(-1, g.removeEdge(10, 7));
        assertEquals(-1, g.removeEdge(10, 8));
        assertEquals(-1, g.getEdge(10, 8), 0.001);
        assertEquals(0, g.addEdge(10, 8, 50));
        assertEquals(50, g.getEdge(10, 8), 0.001);
        assertEquals(0, g.removeEdge(10, 8));
        assertEquals(-1, g.removeEdge(10, 8));
        assertEquals(-1, g.removeEdge(10, 9));
        assertEquals(-1, g.getEdge(10, 9), 0.001);
        assertEquals(0, g.addEdge(10, 9, 50));
        assertEquals(50, g.getEdge(10, 9), 0.001);
        assertEquals(0, g.removeEdge(10, 9));
        assertEquals(-1, g.removeEdge(10, 9));

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
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, g.getWeights());

        assertEquals(0, g.removeNode(1));
        assertEquals(-1, g.removeNode(1));

        assertEquals(0, g.removeNode(3));
        assertEquals(0, g.removeNode(4));
        assertEquals(0, g.removeNode(5));
        assertEquals(0, g.removeNode(6));
        assertEquals(0, g.removeNode(7));
        assertEquals(0, g.removeNode(8));
        assertEquals(0, g.removeNode(9));
        assertEquals(0, g.removeNode(10));
        assertEquals(-1, g.removeNode(3));
        assertEquals(-1, g.removeNode(4));
        assertEquals(-1, g.removeNode(5));
        assertEquals(-1, g.removeNode(6));
        assertEquals(-1, g.removeNode(7));
        assertEquals(-1, g.removeNode(8));
        assertEquals(-1, g.removeNode(9));
        assertEquals(-1, g.removeNode(10));
        assertEquals(-1, g.removeNode(11));

    }

    @Test
    public void test1() {
        Object[] nodos;
        int[][] p;
        boolean[][] a;
        double coste;

        testInsercionesNodos();
        testInsercionesAristas();

        assertEquals(0, g.recorridoProfundidad(1));

        assertEquals(0, g.recorridoProfundidad(2));

        assertEquals(0, g.recorridoProfundidad(3));

        assertEquals(-1, g.recorridoProfundidad(4));

        assertEquals(0, g.recorridoProfundidad(5));

        assertEquals(0, g.recorridoProfundidad(6));

        assertEquals(0, g.recorridoProfundidad(7));

        assertEquals(0, g.recorridoProfundidad(8));

        assertEquals(0, g.recorridoProfundidad(9));

        assertEquals(0, g.recorridoProfundidad(10));

        testDijkstra(new double[][] {
                { 0.0, 14.0, 13.0, 12.0, 25.0, 24.0, 22.0, 21.0, 31.0, 30.0 },
                { 14.0, 0.0, 27.0, 26.0, 11.0, 10.0, 36.0, 35.0, 17.0, 16.0 },
                { 10.0, 24.0, 0.0, 22.0, 35.0, 34.0, 9.0, 8.0, 41.0, 40.0 },
                { infinito, infinito, infinito, 0.0, infinito, infinito,
                        infinito, infinito, infinito, infinito },
                { 11.0, 25.0, 24.0, 23.0, 0.0, 7.0, 33.0, 32.0, 6.0, 5.0 },
                { 4.0, 18.0, 17.0, 16.0, 29.0, 0.0, 26.0, 25.0, 35.0, 34.0 },
                { 5.0, 19.0, 18.0, 17.0, 30.0, 29.0, 0.0, 3.0, 36.0, 35.0 },
                { 2.0, 16.0, 15.0, 14.0, 27.0, 26.0, 24.0, 0.0, 33.0, 32.0 },
                { 21.0, 35.0, 34.0, 33.0, 46.0, 45.0, 43.0, 42.0, 0.0, 1.0 },
                { 20.0, 34.0, 33.0, 32.0, 45.0, 44.0, 42.0, 41.0, 51.0, 0.0 } });

        testFloyd(new int[][] { { -1, -1, -1, -1, 1, 1, 2, 2, 4, 4 },
                { 5, -1, 5, 5, -1, -1, 5, 5, 4, 4 },
                { 7, 7, -1, 7, 7, 7, -1, -1, 7, 7 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 5, 5, 5, 5, -1, -1, 5, 5, -1, -1 },
                { -1, 0, 0, 0, 1, -1, 2, 2, 4, 4 },
                { 7, 7, 7, 7, 7, 7, -1, -1, 7, 7 },
                { -1, 0, 0, 0, 1, 1, 2, -1, 4, 4 },
                { 9, 9, 9, 9, 9, 9, 9, 9, -1, -1 },
                { -1, 0, 0, 0, 1, 1, 2, 2, 4, -1 } });

        nodos = g.getNodes();
        p = g.floyd();
        a = g.getEdges();

        coste = g.path((Integer) nodos[0], (Integer) nodos[0]);

        assertTrue((p[0][0] == -1 && !a[0][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[1]);

        assertTrue((p[0][1] == -1 && !a[0][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[2]);

        assertTrue((p[0][2] == -1 && !a[0][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[3]);

        assertTrue((p[0][3] == -1 && !a[0][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[4]);

        assertTrue((p[0][4] == -1 && !a[0][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[5]);

        assertTrue((p[0][5] == -1 && !a[0][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[6]);

        assertTrue((p[0][6] == -1 && !a[0][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[7]);

        assertTrue((p[0][7] == -1 && !a[0][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[8]);

        assertTrue((p[0][8] == -1 && !a[0][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[9]);

        assertTrue((p[0][9] == -1 && !a[0][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[0]);

        assertTrue((p[1][0] == -1 && !a[1][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[1]);

        assertTrue((p[1][1] == -1 && !a[1][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[2]);

        assertTrue((p[1][2] == -1 && !a[1][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[3]);

        assertTrue((p[1][3] == -1 && !a[1][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[4]);

        assertTrue((p[1][4] == -1 && !a[1][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[5]);

        assertTrue((p[1][5] == -1 && !a[1][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[6]);

        assertTrue((p[1][6] == -1 && !a[1][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[7]);

        assertTrue((p[1][7] == -1 && !a[1][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[8]);

        assertTrue((p[1][8] == -1 && !a[1][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[9]);

        assertTrue((p[1][9] == -1 && !a[1][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[0]);

        assertTrue((p[2][0] == -1 && !a[2][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[1]);

        assertTrue((p[2][1] == -1 && !a[2][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[2]);

        assertTrue((p[2][2] == -1 && !a[2][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[3]);

        assertTrue((p[2][3] == -1 && !a[2][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[4]);

        assertTrue((p[2][4] == -1 && !a[2][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[5]);

        assertTrue((p[2][5] == -1 && !a[2][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[6]);

        assertTrue((p[2][6] == -1 && !a[2][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[7]);

        assertTrue((p[2][7] == -1 && !a[2][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[8]);

        assertTrue((p[2][8] == -1 && !a[2][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[9]);

        assertTrue((p[2][9] == -1 && !a[2][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[0]);

        assertTrue((p[3][0] == -1 && !a[3][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[1]);

        assertTrue((p[3][1] == -1 && !a[3][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[2]);

        assertTrue((p[3][2] == -1 && !a[3][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[3]);

        assertTrue((p[3][3] == -1 && !a[3][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[4]);

        assertTrue((p[3][4] == -1 && !a[3][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[5]);

        assertTrue((p[3][5] == -1 && !a[3][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[6]);

        assertTrue((p[3][6] == -1 && !a[3][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[7]);

        assertTrue((p[3][7] == -1 && !a[3][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[8]);

        assertTrue((p[3][8] == -1 && !a[3][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[9]);

        assertTrue((p[3][9] == -1 && !a[3][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[0]);

        assertTrue((p[4][0] == -1 && !a[4][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[1]);

        assertTrue((p[4][1] == -1 && !a[4][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[2]);

        assertTrue((p[4][2] == -1 && !a[4][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[3]);

        assertTrue((p[4][3] == -1 && !a[4][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[4]);

        assertTrue((p[4][4] == -1 && !a[4][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[5]);

        assertTrue((p[4][5] == -1 && !a[4][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[6]);

        assertTrue((p[4][6] == -1 && !a[4][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[7]);

        assertTrue((p[4][7] == -1 && !a[4][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[8]);

        assertTrue((p[4][8] == -1 && !a[4][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[9]);

        assertTrue((p[4][9] == -1 && !a[4][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[0]);

        assertTrue((p[5][0] == -1 && !a[5][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[1]);

        assertTrue((p[5][1] == -1 && !a[5][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[2]);

        assertTrue((p[5][2] == -1 && !a[5][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[3]);

        assertTrue((p[5][3] == -1 && !a[5][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[4]);

        assertTrue((p[5][4] == -1 && !a[5][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[5]);

        assertTrue((p[5][5] == -1 && !a[5][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[6]);

        assertTrue((p[5][6] == -1 && !a[5][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[7]);

        assertTrue((p[5][7] == -1 && !a[5][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[8]);

        assertTrue((p[5][8] == -1 && !a[5][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[9]);

        assertTrue((p[5][9] == -1 && !a[5][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[0]);

        assertTrue((p[6][0] == -1 && !a[6][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[1]);

        assertTrue((p[6][1] == -1 && !a[6][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[2]);

        assertTrue((p[6][2] == -1 && !a[6][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[3]);

        assertTrue((p[6][3] == -1 && !a[6][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[4]);

        assertTrue((p[6][4] == -1 && !a[6][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[5]);

        assertTrue((p[6][5] == -1 && !a[6][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[6]);

        assertTrue((p[6][6] == -1 && !a[6][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[7]);

        assertTrue((p[6][7] == -1 && !a[6][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[8]);

        assertTrue((p[6][8] == -1 && !a[6][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[9]);

        assertTrue((p[6][9] == -1 && !a[6][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[0]);

        assertTrue((p[7][0] == -1 && !a[7][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[1]);

        assertTrue((p[7][1] == -1 && !a[7][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[2]);

        assertTrue((p[7][2] == -1 && !a[7][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[3]);

        assertTrue((p[7][3] == -1 && !a[7][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[4]);

        assertTrue((p[7][4] == -1 && !a[7][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[5]);

        assertTrue((p[7][5] == -1 && !a[7][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[6]);

        assertTrue((p[7][6] == -1 && !a[7][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[7]);

        assertTrue((p[7][7] == -1 && !a[7][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[8]);

        assertTrue((p[7][8] == -1 && !a[7][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[9]);

        assertTrue((p[7][9] == -1 && !a[7][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[0]);

        assertTrue((p[8][0] == -1 && !a[8][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[1]);

        assertTrue((p[8][1] == -1 && !a[8][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[2]);

        assertTrue((p[8][2] == -1 && !a[8][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[3]);

        assertTrue((p[8][3] == -1 && !a[8][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[4]);

        assertTrue((p[8][4] == -1 && !a[8][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[5]);

        assertTrue((p[8][5] == -1 && !a[8][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[6]);

        assertTrue((p[8][6] == -1 && !a[8][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[7]);

        assertTrue((p[8][7] == -1 && !a[8][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[8]);

        assertTrue((p[8][8] == -1 && !a[8][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[9]);

        assertTrue((p[8][9] == -1 && !a[8][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[0]);

        assertTrue((p[9][0] == -1 && !a[9][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[1]);

        assertTrue((p[9][1] == -1 && !a[9][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[2]);

        assertTrue((p[9][2] == -1 && !a[9][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[3]);

        assertTrue((p[9][3] == -1 && !a[9][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[4]);

        assertTrue((p[9][4] == -1 && !a[9][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[5]);

        assertTrue((p[9][5] == -1 && !a[9][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[6]);

        assertTrue((p[9][6] == -1 && !a[9][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[7]);

        assertTrue((p[9][7] == -1 && !a[9][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[8]);

        assertTrue((p[9][8] == -1 && !a[9][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[9]);

        assertTrue((p[9][9] == -1 && !a[9][9] && coste == -1) || coste >= 0);

        testmodificacionAristas();

        assertEquals(0, g.recorridoProfundidad(1));

        assertEquals(0, g.recorridoProfundidad(2));

        assertEquals(0, g.recorridoProfundidad(3));

        assertEquals(-1, g.recorridoProfundidad(4));

        assertEquals(0, g.recorridoProfundidad(5));

        assertEquals(0, g.recorridoProfundidad(6));

        assertEquals(0, g.recorridoProfundidad(7));

        assertEquals(0, g.recorridoProfundidad(8));

        assertEquals(0, g.recorridoProfundidad(9));

        assertEquals(0, g.recorridoProfundidad(10));

        testDijkstra(new double[][] {
                { 0, 14, 13, 12, 25, 24, 22, 25, 31, 30 },
                { 14, 0, 27, 26, 11, 10, 36, 39, 17, 16 },
                { 14, 28, 0, 26, 39, 38, 9, 12, 45, 44 },
                { infinito, infinito, infinito, 0.0, infinito, infinito,
                        infinito, infinito, infinito, infinito },
                { 11, 25, 24, 23, 0, 7, 33, 36, 6, 5 },
                { 4, 18, 17, 16, 29, 0, 26, 29, 35, 34 },
                { 5, 19, 18, 17, 30, 29, 0, 3, 36, 35 },
                { 2, 16, 15, 14, 27, 26, 24, 0, 33, 32 },
                { 21, 35, 34, 33, 46, 45, 43, 46, 0, 1 },
                { 20, 34, 33, 32, 45, 44, 42, 45, 51, 0 } });

        testFloyd(new int[][] { { -1, -1, -1, -1, 1, 1, 2, 6, 4, 4 },
                { 5, -1, 5, 5, -1, -1, 5, 6, 4, 4 },
                { 7, 7, -1, 7, 7, 7, -1, 6, 7, 7 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 5, 5, 5, 5, -1, -1, 5, 6, -1, -1 },
                { -1, 0, 0, 0, 1, -1, 2, 6, 4, 4 },
                { 7, 7, 7, 7, 7, 7, -1, -1, 7, 7 },
                { -1, 0, 0, 0, 1, 1, 2, -1, 4, 4 },
                { 9, 9, 9, 9, 9, 9, 9, 9, -1, -1 },
                { -1, 0, 0, 0, 1, 1, 2, 6, 4, -1 } });

        nodos = g.getNodes();
        p = g.floyd();
        a = g.getEdges();
        coste = g.path((Integer) nodos[0], (Integer) nodos[0]);

        assertTrue((p[0][0] == -1 && !a[0][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[1]);

        assertTrue((p[0][1] == -1 && !a[0][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[2]);

        assertTrue((p[0][2] == -1 && !a[0][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[3]);

        assertTrue((p[0][3] == -1 && !a[0][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[4]);

        assertTrue((p[0][4] == -1 && !a[0][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[5]);

        assertTrue((p[0][5] == -1 && !a[0][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[6]);

        assertTrue((p[0][6] == -1 && !a[0][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[7]);

        assertTrue((p[0][7] == -1 && !a[0][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[8]);

        assertTrue((p[0][8] == -1 && !a[0][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[9]);

        assertTrue((p[0][9] == -1 && !a[0][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[0]);

        assertTrue((p[1][0] == -1 && !a[1][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[1]);

        assertTrue((p[1][1] == -1 && !a[1][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[2]);

        assertTrue((p[1][2] == -1 && !a[1][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[3]);

        assertTrue((p[1][3] == -1 && !a[1][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[4]);

        assertTrue((p[1][4] == -1 && !a[1][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[5]);

        assertTrue((p[1][5] == -1 && !a[1][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[6]);

        assertTrue((p[1][6] == -1 && !a[1][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[7]);

        assertTrue((p[1][7] == -1 && !a[1][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[8]);

        assertTrue((p[1][8] == -1 && !a[1][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[9]);

        assertTrue((p[1][9] == -1 && !a[1][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[0]);

        assertTrue((p[2][0] == -1 && !a[2][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[1]);

        assertTrue((p[2][1] == -1 && !a[2][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[2]);

        assertTrue((p[2][2] == -1 && !a[2][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[3]);

        assertTrue((p[2][3] == -1 && !a[2][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[4]);

        assertTrue((p[2][4] == -1 && !a[2][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[5]);

        assertTrue((p[2][5] == -1 && !a[2][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[6]);

        assertTrue((p[2][6] == -1 && !a[2][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[7]);

        assertTrue((p[2][7] == -1 && !a[2][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[8]);

        assertTrue((p[2][8] == -1 && !a[2][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[9]);

        assertTrue((p[2][9] == -1 && !a[2][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[0]);

        assertTrue((p[3][0] == -1 && !a[3][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[1]);

        assertTrue((p[3][1] == -1 && !a[3][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[2]);

        assertTrue((p[3][2] == -1 && !a[3][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[3]);

        assertTrue((p[3][3] == -1 && !a[3][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[4]);

        assertTrue((p[3][4] == -1 && !a[3][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[5]);

        assertTrue((p[3][5] == -1 && !a[3][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[6]);

        assertTrue((p[3][6] == -1 && !a[3][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[7]);

        assertTrue((p[3][7] == -1 && !a[3][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[8]);

        assertTrue((p[3][8] == -1 && !a[3][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[9]);

        assertTrue((p[3][9] == -1 && !a[3][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[0]);

        assertTrue((p[4][0] == -1 && !a[4][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[1]);

        assertTrue((p[4][1] == -1 && !a[4][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[2]);

        assertTrue((p[4][2] == -1 && !a[4][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[3]);

        assertTrue((p[4][3] == -1 && !a[4][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[4]);

        assertTrue((p[4][4] == -1 && !a[4][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[5]);

        assertTrue((p[4][5] == -1 && !a[4][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[6]);

        assertTrue((p[4][6] == -1 && !a[4][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[7]);

        assertTrue((p[4][7] == -1 && !a[4][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[8]);

        assertTrue((p[4][8] == -1 && !a[4][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[9]);

        assertTrue((p[4][9] == -1 && !a[4][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[0]);

        assertTrue((p[5][0] == -1 && !a[5][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[1]);

        assertTrue((p[5][1] == -1 && !a[5][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[2]);

        assertTrue((p[5][2] == -1 && !a[5][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[3]);

        assertTrue((p[5][3] == -1 && !a[5][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[4]);

        assertTrue((p[5][4] == -1 && !a[5][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[5]);

        assertTrue((p[5][5] == -1 && !a[5][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[6]);

        assertTrue((p[5][6] == -1 && !a[5][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[7]);

        assertTrue((p[5][7] == -1 && !a[5][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[8]);

        assertTrue((p[5][8] == -1 && !a[5][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[9]);

        assertTrue((p[5][9] == -1 && !a[5][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[0]);

        assertTrue((p[6][0] == -1 && !a[6][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[1]);

        assertTrue((p[6][1] == -1 && !a[6][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[2]);

        assertTrue((p[6][2] == -1 && !a[6][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[3]);

        assertTrue((p[6][3] == -1 && !a[6][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[4]);

        assertTrue((p[6][4] == -1 && !a[6][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[5]);

        assertTrue((p[6][5] == -1 && !a[6][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[6]);

        assertTrue((p[6][6] == -1 && !a[6][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[7]);

        assertTrue((p[6][7] == -1 && !a[6][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[8]);

        assertTrue((p[6][8] == -1 && !a[6][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[9]);

        assertTrue((p[6][9] == -1 && !a[6][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[0]);

        assertTrue((p[7][0] == -1 && !a[7][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[1]);

        assertTrue((p[7][1] == -1 && !a[7][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[2]);

        assertTrue((p[7][2] == -1 && !a[7][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[3]);

        assertTrue((p[7][3] == -1 && !a[7][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[4]);

        assertTrue((p[7][4] == -1 && !a[7][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[5]);

        assertTrue((p[7][5] == -1 && !a[7][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[6]);

        assertTrue((p[7][6] == -1 && !a[7][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[7]);

        assertTrue((p[7][7] == -1 && !a[7][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[8]);

        assertTrue((p[7][8] == -1 && !a[7][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[9]);

        assertTrue((p[7][9] == -1 && !a[7][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[0]);

        assertTrue((p[8][0] == -1 && !a[8][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[1]);

        assertTrue((p[8][1] == -1 && !a[8][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[2]);

        assertTrue((p[8][2] == -1 && !a[8][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[3]);

        assertTrue((p[8][3] == -1 && !a[8][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[4]);

        assertTrue((p[8][4] == -1 && !a[8][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[5]);

        assertTrue((p[8][5] == -1 && !a[8][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[6]);

        assertTrue((p[8][6] == -1 && !a[8][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[7]);

        assertTrue((p[8][7] == -1 && !a[8][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[8]);

        assertTrue((p[8][8] == -1 && !a[8][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[9]);

        assertTrue((p[8][9] == -1 && !a[8][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[0]);

        assertTrue((p[9][0] == -1 && !a[9][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[1]);

        assertTrue((p[9][1] == -1 && !a[9][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[2]);

        assertTrue((p[9][2] == -1 && !a[9][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[3]);

        assertTrue((p[9][3] == -1 && !a[9][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[4]);

        assertTrue((p[9][4] == -1 && !a[9][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[5]);

        assertTrue((p[9][5] == -1 && !a[9][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[6]);

        assertTrue((p[9][6] == -1 && !a[9][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[7]);

        assertTrue((p[9][7] == -1 && !a[9][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[8]);

        assertTrue((p[9][8] == -1 && !a[9][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[9]);

        assertTrue((p[9][9] == -1 && !a[9][9] && coste == -1) || coste >= 0);

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
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, g.getWeights());

        testBorradoAristas();

        assertEquals(-1, g.recorridoProfundidad(1));

        assertEquals(-1, g.recorridoProfundidad(2));

        assertEquals(-1, g.recorridoProfundidad(3));

        assertEquals(-1, g.recorridoProfundidad(4));

        assertEquals(-1, g.recorridoProfundidad(5));

        assertEquals(-1, g.recorridoProfundidad(6));

        assertEquals(-1, g.recorridoProfundidad(7));

        assertEquals(-1, g.recorridoProfundidad(8));

        assertEquals(-1, g.recorridoProfundidad(9));

        assertEquals(-1, g.recorridoProfundidad(10));

        testDijkstra(new double[][] {
                { 0, 14, 13, 12, 25, 24, 22, 25, 31, 30, infinito },
                { 14, 0, 27, 26, 11, 10, 36, 39, 17, 16, infinito },
                { 14, 28, 0, 26, 39, 38, 9, 12, 45, 44, infinito },
                { infinito, infinito, infinito, 0, infinito, infinito,
                        infinito, infinito, infinito, infinito, infinito },
                { 11, 25, 24, 23, 0, 7, 33, 36, 6, 5, infinito },
                { 4, 18, 17, 16, 29, 0, 26, 29, 35, 34, infinito },
                { 5, 19, 18, 17, 30, 29, 0, 3, 36, 35, infinito },
                { 2, 16, 15, 14, 27, 26, 24, 0, 33, 32, infinito },
                { 21, 35, 34, 33, 46, 45, 43, 46, 0, 1, infinito },
                { 20, 34, 33, 32, 45, 44, 42, 45, 51, 0, infinito },
                { infinito, infinito, infinito, infinito, infinito, infinito,
                        infinito, infinito, infinito, infinito, 0 } });

        testFloyd(new int[][] { { -1, -1, -1, -1, 1, 1, 2, 6, 4, 4, -1 },
                { 5, -1, 5, 5, -1, -1, 5, 6, 4, 4, -1 },
                { 7, 7, -1, 7, 7, 7, -1, 6, 7, 7, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 5, 5, 5, 5, -1, -1, 5, 6, -1, -1, -1 },
                { -1, 0, 0, 0, 1, -1, 2, 6, 4, 4, -1 },
                { 7, 7, 7, 7, 7, 7, -1, -1, 7, 7, -1 },
                { -1, 0, 0, 0, 1, 1, 2, -1, 4, 4, -1 },
                { 9, 9, 9, 9, 9, 9, 9, 9, -1, -1, -1 },
                { -1, 0, 0, 0, 1, 1, 2, 6, 4, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } });

        nodos = g.getNodes();
        p = g.floyd();
        a = g.getEdges();
        coste = g.path((Integer) nodos[0], (Integer) nodos[0]);

        assertTrue((p[0][0] == -1 && !a[0][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[1]);

        assertTrue((p[0][1] == -1 && !a[0][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[2]);

        assertTrue((p[0][2] == -1 && !a[0][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[3]);

        assertTrue((p[0][3] == -1 && !a[0][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[4]);

        assertTrue((p[0][4] == -1 && !a[0][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[5]);

        assertTrue((p[0][5] == -1 && !a[0][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[6]);

        assertTrue((p[0][6] == -1 && !a[0][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[7]);

        assertTrue((p[0][7] == -1 && !a[0][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[8]);

        assertTrue((p[0][8] == -1 && !a[0][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[9]);

        assertTrue((p[0][9] == -1 && !a[0][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[0], (Integer) nodos[10]);

        assertTrue((p[0][10] == -1 && !a[0][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[0]);

        assertTrue((p[1][0] == -1 && !a[1][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[1]);

        assertTrue((p[1][1] == -1 && !a[1][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[2]);

        assertTrue((p[1][2] == -1 && !a[1][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[3]);

        assertTrue((p[1][3] == -1 && !a[1][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[4]);

        assertTrue((p[1][4] == -1 && !a[1][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[5]);

        assertTrue((p[1][5] == -1 && !a[1][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[6]);

        assertTrue((p[1][6] == -1 && !a[1][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[7]);

        assertTrue((p[1][7] == -1 && !a[1][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[8]);

        assertTrue((p[1][8] == -1 && !a[1][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[9]);

        assertTrue((p[1][9] == -1 && !a[1][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[1], (Integer) nodos[10]);

        assertTrue((p[1][10] == -1 && !a[1][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[0]);

        assertTrue((p[2][0] == -1 && !a[2][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[1]);

        assertTrue((p[2][1] == -1 && !a[2][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[2]);

        assertTrue((p[2][2] == -1 && !a[2][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[3]);

        assertTrue((p[2][3] == -1 && !a[2][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[4]);

        assertTrue((p[2][4] == -1 && !a[2][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[5]);

        assertTrue((p[2][5] == -1 && !a[2][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[6]);

        assertTrue((p[2][6] == -1 && !a[2][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[7]);

        assertTrue((p[2][7] == -1 && !a[2][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[8]);

        assertTrue((p[2][8] == -1 && !a[2][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[9]);

        assertTrue((p[2][9] == -1 && !a[2][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[2], (Integer) nodos[10]);

        assertTrue((p[2][10] == -1 && !a[2][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[0]);

        assertTrue((p[3][0] == -1 && !a[3][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[1]);

        assertTrue((p[3][1] == -1 && !a[3][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[2]);

        assertTrue((p[3][2] == -1 && !a[3][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[3]);

        assertTrue((p[3][3] == -1 && !a[3][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[4]);

        assertTrue((p[3][4] == -1 && !a[3][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[5]);

        assertTrue((p[3][5] == -1 && !a[3][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[6]);

        assertTrue((p[3][6] == -1 && !a[3][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[7]);

        assertTrue((p[3][7] == -1 && !a[3][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[8]);

        assertTrue((p[3][8] == -1 && !a[3][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[9]);

        assertTrue((p[3][9] == -1 && !a[3][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[3], (Integer) nodos[10]);

        assertTrue((p[3][10] == -1 && !a[3][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[0]);

        assertTrue((p[4][0] == -1 && !a[4][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[1]);

        assertTrue((p[4][1] == -1 && !a[4][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[2]);

        assertTrue((p[4][2] == -1 && !a[4][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[3]);

        assertTrue((p[4][3] == -1 && !a[4][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[4]);

        assertTrue((p[4][4] == -1 && !a[4][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[5]);

        assertTrue((p[4][5] == -1 && !a[4][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[6]);

        assertTrue((p[4][6] == -1 && !a[4][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[7]);

        assertTrue((p[4][7] == -1 && !a[4][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[8]);

        assertTrue((p[4][8] == -1 && !a[4][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[9]);

        assertTrue((p[4][9] == -1 && !a[4][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[4], (Integer) nodos[10]);

        assertTrue((p[4][10] == -1 && !a[4][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[0]);

        assertTrue((p[5][0] == -1 && !a[5][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[1]);

        assertTrue((p[5][1] == -1 && !a[5][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[2]);

        assertTrue((p[5][2] == -1 && !a[5][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[3]);

        assertTrue((p[5][3] == -1 && !a[5][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[4]);

        assertTrue((p[5][4] == -1 && !a[5][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[5]);

        assertTrue((p[5][5] == -1 && !a[5][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[6]);

        assertTrue((p[5][6] == -1 && !a[5][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[7]);

        assertTrue((p[5][7] == -1 && !a[5][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[8]);

        assertTrue((p[5][8] == -1 && !a[5][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[9]);

        assertTrue((p[5][9] == -1 && !a[5][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[5], (Integer) nodos[10]);

        assertTrue((p[5][10] == -1 && !a[5][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[0]);

        assertTrue((p[6][0] == -1 && !a[6][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[1]);

        assertTrue((p[6][1] == -1 && !a[6][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[2]);

        assertTrue((p[6][2] == -1 && !a[6][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[3]);

        assertTrue((p[6][3] == -1 && !a[6][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[4]);

        assertTrue((p[6][4] == -1 && !a[6][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[5]);

        assertTrue((p[6][5] == -1 && !a[6][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[6]);

        assertTrue((p[6][6] == -1 && !a[6][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[7]);

        assertTrue((p[6][7] == -1 && !a[6][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[8]);

        assertTrue((p[6][8] == -1 && !a[6][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[9]);

        assertTrue((p[6][9] == -1 && !a[6][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[6], (Integer) nodos[10]);

        assertTrue((p[6][10] == -1 && !a[6][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[0]);

        assertTrue((p[7][0] == -1 && !a[7][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[1]);

        assertTrue((p[7][1] == -1 && !a[7][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[2]);

        assertTrue((p[7][2] == -1 && !a[7][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[3]);

        assertTrue((p[7][3] == -1 && !a[7][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[4]);

        assertTrue((p[7][4] == -1 && !a[7][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[5]);

        assertTrue((p[7][5] == -1 && !a[7][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[6]);

        assertTrue((p[7][6] == -1 && !a[7][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[7]);

        assertTrue((p[7][7] == -1 && !a[7][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[8]);

        assertTrue((p[7][8] == -1 && !a[7][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[9]);

        assertTrue((p[7][9] == -1 && !a[7][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[7], (Integer) nodos[10]);

        assertTrue((p[7][10] == -1 && !a[7][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[0]);

        assertTrue((p[8][0] == -1 && !a[8][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[1]);

        assertTrue((p[8][1] == -1 && !a[8][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[2]);

        assertTrue((p[8][2] == -1 && !a[8][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[3]);

        assertTrue((p[8][3] == -1 && !a[8][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[4]);

        assertTrue((p[8][4] == -1 && !a[8][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[5]);

        assertTrue((p[8][5] == -1 && !a[8][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[6]);

        assertTrue((p[8][6] == -1 && !a[8][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[7]);

        assertTrue((p[8][7] == -1 && !a[8][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[8]);

        assertTrue((p[8][8] == -1 && !a[8][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[9]);

        assertTrue((p[8][9] == -1 && !a[8][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[8], (Integer) nodos[10]);

        assertTrue((p[8][10] == -1 && !a[8][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[0]);

        assertTrue((p[9][0] == -1 && !a[9][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[1]);

        assertTrue((p[9][1] == -1 && !a[9][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[2]);

        assertTrue((p[9][2] == -1 && !a[9][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[3]);

        assertTrue((p[9][3] == -1 && !a[9][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[4]);

        assertTrue((p[9][4] == -1 && !a[9][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[5]);

        assertTrue((p[9][5] == -1 && !a[9][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[6]);

        assertTrue((p[9][6] == -1 && !a[9][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[7]);

        assertTrue((p[9][7] == -1 && !a[9][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[8]);

        assertTrue((p[9][8] == -1 && !a[9][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[9]);

        assertTrue((p[9][9] == -1 && !a[9][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[9], (Integer) nodos[10]);

        assertTrue((p[9][10] == -1 && !a[9][10] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[0]);

        assertTrue((p[10][0] == -1 && !a[10][0] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[1]);

        assertTrue((p[10][1] == -1 && !a[10][1] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[2]);

        assertTrue((p[10][2] == -1 && !a[10][2] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[3]);

        assertTrue((p[10][3] == -1 && !a[10][3] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[4]);

        assertTrue((p[10][4] == -1 && !a[10][4] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[5]);

        assertTrue((p[10][5] == -1 && !a[10][5] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[6]);

        assertTrue((p[10][6] == -1 && !a[10][6] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[7]);

        assertTrue((p[10][7] == -1 && !a[10][7] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[8]);

        assertTrue((p[10][8] == -1 && !a[10][8] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[9]);

        assertTrue((p[10][9] == -1 && !a[10][9] && coste == -1) || coste >= 0);
        coste = g.path((Integer) nodos[10], (Integer) nodos[10]);

        assertTrue((p[10][10] == -1 && !a[10][10] && coste == -1) || coste >= 0);

        borradoNodos();

        assertEquals(0, g.recorridoProfundidad(2));
        assertEquals(-1, g.recorridoProfundidad(1));

        assertArrayEquals(new double[] { 0 }, g.dijkstra(2), 0.001);

        assertArrayEquals(new int[][] { { -1 } }, g.floyd());

        assertEquals(0, g.path(2, 2), 0.001);

    }
}
