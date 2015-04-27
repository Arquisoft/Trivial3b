package es.uniovi.asw.game.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import game.Graph;

import org.junit.Test;

public class GraphTest {
    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método existNode de la clase Graph
     */
    public void existNodeTest() {
        Graph<Integer> g = new Graph<Integer>(3);
        g.addNode(2);

        // Pruebas Positivas
        assertEquals(true, g.existNode(2));

        // Pruebas Negativas
        assertEquals(false, g.existNode(5));
        assertEquals(false, g.existNode(null));
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método getEdge de la clase Graph
     */
    public void getEdgeTest() {
        Graph<Integer> g = new Graph<Integer>(4);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addEdge(1, 2, 10);
        g.addEdge(1, 3, -2);

        // Pruebas Positivas
        assertEquals(10, g.getEdge(1, 2), 0.01);
        assertEquals(-2, g.getEdge(1, 3), 0.01);

        // Pruebas Negativas
        assertEquals(-1, g.getEdge(3, 4), 0.01);
        assertEquals(-1, g.getEdge(1, null), 0.01);
        assertEquals(-1, g.getEdge(null, 2), 0.01);
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método addNode de la clase Graph
     */
    public void addNodeTest() {
        Graph<Integer> g = new Graph<Integer>(3);

        // Prueba positiva
        assertEquals(0, g.addNode(1));

        // Pruebas negativa de que si añado un nodo ya existente no lo añade
        g.addNode(2);
        assertEquals(-1, g.addNode(2));

        // Prueba negativa de que si añado un nodo null no se añade
        assertEquals(-1, g.addNode(null));

        // Prueba negativa de que si intento añadir un nodo cuando el array est�
        // lleno no se añade
        g.addNode(3);
        assertEquals(-1, g.addNode(4));
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método removeNode de la clase Graph
     */
    public void removeNodeTest() {
        Graph<Integer> g = new Graph<Integer>(3);
        g.addNode(1);

        // Prueba positiva
        assertEquals(0, g.removeNode(1));

        // Pruebas negativas
        assertEquals(-1, g.removeNode(2));
        assertEquals(-1, g.removeNode(null));
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método addEdge de la clase Graph
     */
    public void addEdgeTest() {
        Graph<Integer> g = new Graph<Integer>(3);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);

        // Prueba positiva
        assertEquals(0, g.addEdge(1, 2, 2));
        assertEquals(0, g.addEdge(1, 2, 4));
        assertEquals(0, g.addEdge(1, 3, -2));

        // Pruebas negativas
        assertEquals(-1, g.addEdge(1, null, 2));
        assertEquals(-1, g.addEdge(null, 2, 2));
        assertEquals(-1, g.addEdge(4, 5, 2));

    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método removeEdge de la clase Graph
     */
    public void removeEdgeTest() {
        Graph<Integer> g = new Graph<Integer>(3);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addEdge(1, 2, 4);

        // Prueba positiva
        assertEquals(0, g.removeEdge(1, 2));

        // Prueba negativa
        assertEquals(-1, g.removeEdge(1, null));
        assertEquals(-1, g.removeEdge(null, 2));
        assertEquals(-1, g.removeEdge(4, 5));
        assertEquals(-1, g.removeEdge(2, 3));
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método recorridoProfundidad de la clase Graph
     */
    public void recorridoProfunidadTest() {
        // Prueba 1
        Graph<String> g = new Graph<String>(4);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addEdge("A", "B", 1);
        g.addEdge("A", "C", 5);
        g.addEdge("B", "C", 2);
        g.addEdge("B", "D", 3);
        g.addEdge("D", "A", 4);

        assertEquals(0, g.recorridoProfundidad("A"));
        assertEquals(0, g.recorridoProfundidad("B"));
        assertEquals(-1, g.recorridoProfundidad("C"));
        assertEquals(0, g.recorridoProfundidad("D"));

        // Prueba negativa
        assertEquals(-1, g.recorridoProfundidad(null));
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método dijkstra de la clase Graph
     */
    public void dijkstraTest() {
        // Prueba 1
        Graph<String> g = new Graph<String>(4);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addEdge("A", "B", 1);
        g.addEdge("A", "C", 5);
        g.addEdge("B", "C", 2);
        g.addEdge("B", "D", 3);
        g.addEdge("D", "A", 4);

        assertArrayEquals(new double[] { 0.0, 1.0, 3.0, 4.0 }, g.dijkstra("A"),
                0.01);

        // Prueba 2
        Graph<Integer> g2 = new Graph<Integer>(6);
        g2.addNode(1);
        g2.addNode(2);
        g2.addNode(3);
        g2.addNode(4);
        g2.addNode(5);
        g2.addNode(6);
        g2.addEdge(1, 2, 3);
        g2.addEdge(1, 3, 4);
        g2.addEdge(1, 5, 8);
        g2.addEdge(2, 5, 5);
        g2.addEdge(3, 5, 3);
        g2.addEdge(5, 6, 3);
        g2.addEdge(5, 4, 7);
        g2.addEdge(6, 4, 2);

        assertArrayEquals(new double[] { 0.0, 3.0, 4.0, 12.0, 7.0, 10.0 },
                g2.dijkstra(1), 0.01);
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método floyd de la clase Graph
     */
    public void floydTest() {
        Graph<String> g = new Graph<String>(4);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addEdge("A", "B", 1.0);
        g.addEdge("B", "C", 2.0);
        g.addEdge("C", "D", 3.0);
        g.addEdge("A", "D", 10.0);

        assertArrayEquals(new int[][] { { -1, -1, 1, 2 }, { -1, -1, -1, 2 },
                { -1, -1, -1, -1 }, { -1, -1, -1, -1 } }, g.floyd());

        // Prueba negativa 1. Hay alguna arista con peso negativo en el grafo
        Graph<Integer> g2 = new Graph<Integer>(3);
        g2.addNode(1);
        g2.addNode(2);
        g2.addNode(3);
        g2.addEdge(1, 2, 2.0);
        g2.addEdge(2, 3, -2.0);

        assertNull(g2.floyd());

        // Prueba negativa 2. No hay nodos en el grafo
        Graph<Integer> g3 = new Graph<Integer>(3);
        assertNull(g3.floyd());
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método path de la clase Graph
     */
    public void pathTest() {
        Graph<String> g = new Graph<String>(4);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addEdge("A", "B", 1.0);
        g.addEdge("B", "C", 2.0);
        g.addEdge("C", "D", 3.0);
        g.addEdge("A", "D", 10.0);

        assertEquals(6.0, g.path("A", "D"), 0.01);
        assertEquals(0.0, g.path("A", "A"), 0.01);

        // Prueba negativa 1. Existen los nodos pasados por parámetro pero no
        // hay camino entre ellos
        Graph<String> g2 = new Graph<String>(4);
        g2.addNode("A");
        g2.addNode("B");
        g2.addNode("C");
        g2.addNode("D");
        g2.addEdge("A", "B", 1.0);
        g2.addEdge("C", "D", 2.0);

        assertEquals(-1.0, g2.path("A", "D"), 0.01);

        // Prueba negativa 2. No existen los dos nodos pasados por parámetro, o
        // uno de ellos
        assertEquals(-1.0, g2.path("X", "Y"), 0.01);
        assertEquals(-1.0, g2.path("A", null), 0.01);
        assertEquals(-1.0, g2.path(null, "B"), 0.01);

        // Prueba negativa 3. Hay alguna arista con peso negativo y por tanto no
        // podríamos utilizar el método floyd para calcular el path
        Graph<Integer> g3 = new Graph<Integer>(3);
        g3.addNode(1);
        g3.addNode(2);
        g3.addNode(3);
        g3.addEdge(1, 2, 2.0);
        g3.addEdge(2, 3, -2.0);

        assertEquals(-1.0, g3.path(1, 2), 0.01);
    }

    @Test
    /**
     * Test que comprueba el correcto funcionamiento del método
    excentricidad() de la clase Graph
     */
    public void excentricidadTest() {
        Graph<String> g = new Graph<String>(4);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addEdge("A", "B", 1.0);
        g.addEdge("B", "C", 2.0);
        g.addEdge("C", "D", 3.0);
        g.addEdge("A", "D", 10.0);

        assertEquals(Double.POSITIVE_INFINITY, g.excentricidad("A"), 0.01);
        assertEquals(Double.POSITIVE_INFINITY, g.excentricidad("B"), 0.01);
        assertEquals(Double.POSITIVE_INFINITY, g.excentricidad("C"), 0.01);
        assertEquals(6.0, g.excentricidad("D"), 0.01);

        // Prueba negativa
        assertEquals(-1.0, g.excentricidad("X"), 0.01);
    }
}
