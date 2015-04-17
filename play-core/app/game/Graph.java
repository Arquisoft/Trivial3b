package game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
	private T[] nodes; // Vector de nodos
	private boolean[][] edges; // Matriz de aristas
	private double[][] weights; // Matriz de pesos
	private int numNodes; // Numero de nodos del grafo
	private double[][] aFloyd;

	/**
	 * Constructor con parámetros de la clase Grafo
	 * 
	 * @param tam tamaño máximo que tendrá el array de nodos
	 */
	@SuppressWarnings("unchecked")
	public Graph(int tam) {
		// nodos = new T[tam]; //No se puede crear un array de genéricos
		nodes = (T[]) new Object[tam];
		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
		numNodes = 0;
	}

	/**
	 * Método de acceso al valor de la variable numNodes. Utilizado para las
	 * pruebas JUnit
	 * 
	 * @return devuelve el valor de numNodes
	 */
	public int getNumNodes() {
		return numNodes;
	}

	/**
	 * Método de acceso a la matriz nodes. Utilizado para las pruebas JUnit
	 * 
	 * @return devuelve una referencia a la matriz de indices nodes
	 */
	public T[] getNodes() {
		return nodes;
	}

	/**
	 * Método de acceso a la matriz edges. Utilizado para las pruebas JUnit
	 * 
	 * @return devuelve una referencia a la matriz de booleanos edges
	 */
	public boolean[][] getEdges() {
		return edges;
	}

	/**
	 * Método de acceso a la matriz edges. Utilizado para las pruebas JUnit
	 * 
	 * @return devuelve una referencia a la matriz de pesos weights
	 */
	public double[][] getWeights() {
		return weights;
	}

	/**
	 * Método de acceso a la matriz weight. Utilizado para las pruebas JUnit
	 * 
	 * @return devuelve una referencia a la matriz de booleanos weight
	 */
	public double[][] getWeight() {
		return weights;
	}

	/**
	 * Método que añade un nodo al grafo, siempre que dicho nodo todavía no esté
	 * en el grafo
	 * 
	 * @param node Nodo que queremos añadir
	 * @return Devuelve 0 si se completa la tarea correctamente y -1 si se ha
	 *         producido algún tipo de error
	 */
	public int addNode(T node) {
		// Comprobar el nodo no es null, si cabe el nodo y si no está repetido
		if ((node != null) && (numNodes < nodes.length) && (!existNode(node))) {
			nodes[numNodes] = node;

			// Poner falsos en la fila y columna del nodo añadido en la matriz
			// de aristas
			// Poner a 0 los pesos de 0 las filas y columnos del nodo añadido
			for (int i = 0; i <= numNodes; i++) {
				edges[numNodes][i] = false;
				edges[i][numNodes] = false;
				weights[numNodes][i] = 0;
				weights[i][numNodes] = 0;
			}

			// Aumentar el numero de nodos al haber añadido el nuevo
			numNodes++;
			return 0;
		}

		return -1;
	}

	/**
	 * Método que añade una arista nueva entre dos nodos ya existentes Si se
	 * añade una arista ya existente, se cambia el peso a la nueva
	 * 
	 * @param source Nodo fuente desde el que sale la arista
	 * @param target Nodo destino al que llegará la arista
	 * @param edgeWeight Peso que tendrá la nueva arista
	 * @return Devuelve 0 si se completó la tarea correctamente y -1 si se ha
	 *         producido algún tipo de error
	 */
	public int addEdge(T source, T target, double edgeWeight) {
		// Obtener posición en el array de Nodos del nodo fuente y el destino
		int i = getNode(source);
		int j = getNode(target);

		// Comprobar que existen los dos nodos, o que no se está intentando
		// añadir
		if ((i >= 0) && (j >= 0)) {
			edges[i][j] = true;
			weights[i][j] = edgeWeight;

			return 0;
		}

		return -1;
	}

	/**
	 * Método que borra un nodo ya existente en el grafo y, como consecuencia,
	 * todas sus aristas
	 * 
	 * @param node Nodo que se desea borrar
	 * @return Devuelve 0 si se completó la tarea correctamente y -1 si se ha
	 *         producido algún tipo de error
	 */
	public int removeNode(T node) {
		int i = getNode(node);

		// Comprueba que existe el nodo que queremos borrar
		if (i == -1) {
			return -1;
		} else {
			// Decrementa el número de nodos del grafo
			numNodes--;

			// Si el nodo que queremos borrar es un nodo central (no es el
			// último nodo)
			if (i != numNodes + 1) {
				// Sustituye el nodo que queremos borrar por el último nodo del
				// grafo
				nodes[i] = nodes[numNodes];
				nodes[numNodes] = null;

				for (int j = 0; j < numNodes; j++) {
					// Cambia las filas y columnas respectivas al nodo que
					// queremos borrar por la del último
					edges[i][j] = edges[numNodes][j];
					edges[j][i] = edges[j][numNodes];
					weights[i][j] = weights[numNodes][j];
					weights[j][i] = weights[j][numNodes];

					// Pone a false y a 0 las filas y columnas del último
					edges[numNodes][j] = false;
					edges[j][numNodes] = false;
					weights[numNodes][j] = 0;
					weights[j][numNodes] = 0;
				}

				// Arregla la diagonal para evitar que se superpongan los datos
				edges[i][i] = edges[numNodes][numNodes];
				weights[i][i] = weights[numNodes][numNodes];

				edges[numNodes][numNodes] = false;
				weights[numNodes][numNodes] = 0;
			}

			return 0;
		}
	}

	/**
	 * Método para borrar una arista entre dos nodos
	 * 
	 * @param source Nodo fuente desde el que sale la arista
	 * @param target Nodo destino al que llega la arista
	 * @return Devuelve 0 si se completó la tarea correctamente y -1 si se ha
	 *         producido algún tipo de error
	 */
	public int removeEdge(T source, T target) {
		int i = getNode(source);
		int j = getNode(target);

		// Comprobar que existen los nodos y la arista que queremos borrar
		if ((i >= 0) && (j >= 0) && (edges[i][j] == true)) {
			edges[i][j] = false;
			weights[i][j] = 0;
			return 0;
		}

		return -1;
	}

	/**
	 * Método que devuelve el índice de la posición de el nodo pasado por
	 * parámetro en el array de Nodos
	 * 
	 * @param node Nodo que se desea buscar
	 * @return Devuelve -1 si el nodo no está en el array y si lo está, devuelve
	 *         la posición de este
	 */
	private int getNode(T node) {
		// Comprobar que el nodo pasado por parámetro no es nulo
		if (node != null) {
			// Busca dicho nodo en el vector de nodos y si lo encuentra devuelve
			// el índice
			for (int i = 0; i < numNodes; i++) {
				if (node.equals(nodes[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	public T getNode(int index) {
		return nodes[index];
	}

	/**
	 * Método que devuelve el peso de una arista
	 * 
	 * @param source Nodo fuente desde el que sale la arista
	 * @param target Nodo destino al que llega la arista
	 * @return Devuelve el peso de la arista y -1 si se ha producido algún tipo
	 *         de error
	 */
	public double getEdge(T source, T target) {
		int i = getNode(source);
		int j = getNode(target);

		// Comprueba que existen los dos nodos y la arista que queremos
		// comprobar
		if ((i >= 0) && (j >= 0) && (edges[i][j] == true)) {
			return weights[i][j];
		}

		return -1;
	}

	/**
	 * Método que busca en el array de Nodos si existe uno que se le pasa por
	 * parámetro
	 * 
	 * @param node Nodo que se desea buscar
	 * @return Devuelve true si existe ya un nodo o false si no
	 */
	public boolean existNode(T node) {
		return (getNode(node) != -1);
	}

	/**
	 * Método que comprueba si existe una arista entre un par de nodos pasados
	 * por parametro
	 * 
	 * @param source Nodo fuente
	 * @param target Nodo destino
	 * @return Devuelve true si existe una arista entre los nodos
	 */
	public boolean existEdge(T source, T target) {
		int i = getNode(source);
		int j = getNode(target);

		// Si existen los nodos pasados por parámetro, accede a la matriz de
		// aristas para saber si existe la arista
		if ((i >= 0) && (j >= 0)) {
			return edges[i][j];
		}

		return false;
	}

	/**
	 * Método que realiza un recorrido en profundidad del grafo empezando desde
	 * el nodo pasado por parámetro No necesariamente recorre todos los nodos Al
	 * recorrer cada nodo hace un tratamiento del mismo programado en el método
	 * tratarNodo() Usa un método auxiliar recursivo Se puede proponer como
	 * prueba de evaluación que los recorran todos aunque siga devolviendo -1 si
	 * no llegaba a todos desde el inicial
	 * 
	 * @param node Nodo inicial desde el que comenzaremos el recorrido en
	 *            profundidad del grafo
	 * @return Si recorre todos los nodos del grafo devuelve 0, si algún nodo
	 *         queda sin recorrer devuelve -1
	 */
	public int recorridoProfundidad(T node) {
		// Creamos un array de booleanos que indicará si todos los nodos han
		// sido visitados
		boolean[] v = new boolean[numNodes];
		int indice = getNode(node);

		// Comprobamos que existe el nodo que se pasa por parámetro
		if (indice == -1) {
			return -1;
		}

		// Llamamos al método recursivo del recorrido en profundidad
		recProf(indice, v);

		// Comprobamos si todos los nodos han sido recorridos y devolvemos -1 si
		// hay alguno no visitado
		for (int i = 0; i < numNodes; i++) {
			if (!v[i]) {
				return -1;
			}
		}
		return 0;
	}

	/**
	 * Método envoltorio que consigue que el método recorridoProfundidad sea
	 * recursivo No hace falta devolver un array, debido a que al modificarlo
	 * dentro del método se modifica el array (al pasarlo por parámetro, se pasa
	 * la dirección)
	 * 
	 * @param indice índice del vector de nodos en el que se encuentra el nodo
	 *            en el que comienza el algoritmo
	 * @param v vector de booleanos que almacena la información de si se ha
	 *            recorrido un nodo concreto
	 */
	private void recProf(int indice, boolean[] v) {
		// Comprobamos que no ha sido visitado el nodo inicial
		if (!v[indice]) {
			// Tramos el nodo
			tratarNodo(indice);

			// Lo marcamos como visitado
			v[indice] = true;

			// Recorremos la matriz edges para comprobar si desde dicho nodo se
			// puede acceder a otros
			// Si es así, hacemos una llamada recursiva sobre dicho nodo
			for (int i = 0; i < numNodes; i++) {
				if (edges[indice][i] == true) {
					recProf(i, v);
				}
			}
		}
	}

	/**
	 * Método que utiliza el algoritmo de Dijkstra para calcular los caminos
	 * mínimos desde un nodo del grafo, a los demás
	 * 
	 * @param node Nodo inicial desde el que se calcula el algoritmo
	 * @return Devuelve un array con los pesos de los caminos minimos desde el
	 *         nodo pasado por parámetro a los demás. Si el nodo inicial no
	 *         existe, devuelve el array lleno de 0
	 * */
	public double[] dijkstra(T node) {
		// INICIALIZACIÓN
		// Nodo de partida
		int inicial = getNode(node);

		// Creamos los conjuntos necesarios para el algoritmo
		double[] d = new double[numNodes];
		int[] p = new int[numNodes];
		boolean[] s = new boolean[numNodes];

		// Comprobamos que dicho nodo no es null y que no hay aristas con peso
		// negativo en el grafo
		if ((inicial == -1) || (aristasPositivas() == -1)) {
			return d;
		}

		// Bucle que inicializa los conjuntos anteriores
		for (int i = 0; i < numNodes; i++) {
			// Si desde el nodo inicial a cada nodo no hay camino directo
			// colocamos infinito en el vector D y -1 en el P
			if (!edges[inicial][i]) {
				d[i] = Double.POSITIVE_INFINITY;
				p[i] = -1;
			}
			// En caso de que haya camino directo, colocamos el peso del camino
			// en D y el indice del nodo fuente en P
			else {
				d[i] = weights[inicial][i];
				p[i] = inicial;
			}
		}

		// Además, colocamos como visitado el nodo inicial
		s[inicial] = true;
		d[inicial] = 0;

		// CUERPO DEL ALGORITMO
		// Buscamos el nodo con coste mínimo desde el inicial
		int siguiente = minCoste(d, s);

		// Hasta que recorramos todos los nodos...
		while (siguiente != -1) {
			// Colocamos el nodo siguiente como visitado
			s[siguiente] = true;

			// Bucle que recorrerá el vector D y P para actualizarlo con la
			// información del nodo siguiente
			for (int i = 0; i < numNodes; i++) {
				// Si hay camino directo desde el nodo que utilizamos...
				if (edges[siguiente][i] == true) {
					// Compara si utilizando dicho nodo, se reducen los pesos
					// almacenados en D
					if (d[siguiente] + weights[siguiente][i] < d[i]) {
						// Actualiza los pesos en el vector D y coloca el indice
						// del nodo utilizado en P
						d[i] = d[siguiente] + weights[siguiente][i];
						p[i] = siguiente;
					}
				}
			}

			// Buscamos el siguiente nodo con camino mínimo, que no haya sido
			// visitado
			siguiente = minCoste(d, s);
		}

		return d;
	}

	/**
	 * Método privado que comprueba si hay alguna arista con peso negativo, ya
	 * que si es así, tanto el método de floyd como el de dijkstra no pueden
	 * funcionar
	 * 
	 * @return devuelve -1 si hay alguna arista con peso negativo, y 0 si todas
	 *         tienen peso positivo
	 */
	private int aristasPositivas() {
		// Bucle que recorre las filas de la matriz de pesos
		for (int i = 0; i < numNodes; i++) {
			// Bucle que recorre las columnas de la matriz de pesos
			for (int j = 0; j < numNodes; j++) {
				// Comprueba si el peso de la arista es negativo
				if (weights[i][j] < 0) {
					return -1;
				}
			}
		}

		return 0;
	}

	/**
	 * Método que devuelve el índice del nodo que tenga un valor más pequeño,
	 * teniendo en cuenta que es a uno de los nodos no visitados
	 * 
	 * @return Devuelve -1 si no todos los nodos han sido visitados ya
	 */
	private int minCoste(double[] d, boolean[] s) {
		// Partimos de que no hay ningún nodo con camino directo mínimo
		int siguiente = -1;
		double minimo = Double.POSITIVE_INFINITY;

		// Recorre el vector S de dijkstra para comprobar si el coste se
		// disminuye
		for (int i = 0; i < s.length; i++) {
			// Si dicho nodo no ha sido visitado..
			if (s[i] == false) {
				// Y además reduce el mínimo que tenemos almacenado..
				if (d[i] <= minimo) {
					// Este nodo pasa a ser el que tiene un camino directo con
					// coste mínimo
					minimo = d[i];
					siguiente = i;
				}
			}
		}

		return siguiente;
	}

	/**
	 * Método que utiliza el algoritmo de Floyd-Warshall para calcular los
	 * caminos mínimos entre todo par de nodos de un grafo Hay que tener cuidado
	 * porque infinito más algo no tiene porqué salir infinito
	 * 
	 * @return Devuelve la matriz "P" que contiene cual es el nodo intermedio
	 *         para cada par de nodos (Si es -1 puede que no haya camino o que
	 *         el camino sea intermedio)
	 */
	public int[][] floyd() {
		// Comprueba haya algún nodo en el grafo y que no existan aristas con
		// peso negativo
		if ((aristasPositivas() == -1) || (numNodes == 0)) {
			return null;
		}

		// INICIALIZACIÓN
		// Creamos la matriz de costes A y P que nos indica por que nodo hay que
		// pasar para el minimo coste
		double[][] a = new double[numNodes][numNodes];
		int[][] p = new int[numNodes][numNodes];

		// Bucles que inicializan las matrices anteriormente creadas.
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				// Por defecto, la matriz P estará llena de -1
				p[i][j] = -1;

				// Si estamos en la diagonal, colocamos 0 en la matriz de costes
				// A
				if (i == j) {
					a[i][j] = 0;

				}
				// Si existe camino directo entre ambos nodos, colocamos el peso
				// de la arista que los une en la matriz A
				else if (edges[i][j]) {
					a[i][j] = weights[i][j];
				}
				// En los demás casos (cuando no hay camino directo) colocamos
				// infinito en la matriz A
				else {
					a[i][j] = Double.POSITIVE_INFINITY;

				}
			}
		}

		// CUERPO DEL ALGORITMO
		// Recorremos todos los nodos del grafo
		for (int k = 0; k < numNodes; k++) {
			// Recorremos las filas de la matriz A
			for (int i = 0; i < numNodes; i++) {
				// Recorremos las columnas de la matriz A
				for (int j = 0; j < numNodes; j++) {
					// Comprobamos si utilizando el nodo k como
					// "nodo intermedio" se reducen los costes de los caminos a
					// cada nodo
					if (a[i][k] + a[k][j] < a[i][j]) {
						// Si lo anterior se cumple, actualizamos la matriz A
						// con el coste nuevo y en la matriz P colocamos el
						// indice del nodo intermedio
						a[i][j] = a[i][k] + a[k][j];
						p[i][j] = k;
					}
				}
			}
		}

		// Utilizado para calcular la excentricidad en un método externo
		aFloyd = a;

		return p;
	}

	/**
	 * Método que recorre el camino de coste mínimo, tratando cada nodo por el
	 * que pasa y mostando el coste acumulado del camino
	 * 
	 * @param origen Nodo desde el que parte el camino
	 * @param destino Nodo en el que debe finalizar el camino return Devuelve el
	 *            coste acumulado del camino. En caso de devolver -1.0, el
	 *            camino no existe. Si devuelve 0, es que el origen y el destino
	 *            es el mismo
	 * @return Coste del camino
	 */
	public double path(T origen, T destino) {
		// Obtenemos la matriz P calculada en el método floyd()
		int[][] pFloyd = floyd();

		int i = getNode(origen);
		int j = getNode(destino);

		// Si los nodos no existen o el método floyd no consiguió calcular la
		// matriz P, devolvemos -1
		if ((i == -1) || (j == -1) || (pFloyd == null)) {
			return -1.0;
		}
		// Si el nodo fuente y el destino son el mismo, tratamos dicho nodo y
		// devolvemos 0
		else if (i == j) {
			tratarNodo(i);
			return 0.0;
		}
		// Si ambos nodos existen pero no hay camino entre ellos, devolvemos -1
		// si tratar el inicial
		else if (!existeCamino(i, j, pFloyd)) {
			return -1.0;
		}
		// En caso de que existan los nodos y haya camino entre ellos, tratamos
		// el nodo inicial y llamamos al método recursivo para tratar los demás
		else {
			tratarNodo(i);
			return pathRec(i, j, pFloyd);
		}
	}

	/**
	 * Método envoltorio que consigue que el método path sea recursivo
	 * 
	 * @return
	 */
	private double pathRec(int i, int j, int[][] pFloyd) {
		// Calcula si hay un nodo intermedio entre los pasados por parámetro
		int intermedio = pFloyd[i][j];

		// Si no existe nodo intermedio entre los nodos pasados por parámetro...
		if (intermedio == -1) {
			// ...tratamos el nodo y devolvemos el peso de la arista que los une
			tratarNodo(j);
			return weights[i][j];
		}

		// Si hay un nodo intermedio, llama recursivamente al método dos veces
		// La primera desde el nodo fuente hasta el intermedio y la segunda
		// desde el intermedio hasta el destino, sumando los pesos obtenidos
		return pathRec(i, intermedio, pFloyd) + pathRec(intermedio, j, pFloyd);
	}

	/**
	 * Método privado que calcula si hay camino entre dos nodos del grafo, sin
	 * tratarlos Utilizado en el método path para comprobar que no hay camino
	 * entre dos nodos
	 * 
	 * @param i nodo origen
	 * @param j nodo destino
	 * @return devuelve false si no hay camino y true si lo hay
	 */
	private boolean existeCamino(int i, int j, int[][] pFloyd) {
		// Si en la matriz de floyd entre los nodos fuente y destino hay un
		// número, este indica que podemos acceder al segundo desde el primero
		// pasando por el nodo intermedio. Y por tanto, hay camino entre ellos
		if (pFloyd[i][j] != -1) {
			return true;
		}
		// Si en la matriz de floyd entre los nodos fuente y destino hay un -1
		// quiere decir que no hay nodo intermedio entre ellos y por tanto, se
		// divide en dos casos...
		else {
			// Si hay camino directo entre ellos, sí hay camino
			if (edges[i][j]) {
				return true;
			}
			// Si no hay camino directo, entonces es que no hay camino entre
			// ambos nodos
			else {
				return false;
			}
		}
	}

	/**
	 * Método que trata un nodo que se encuentra en la posición que se le pasa
	 * por parámetro mostrandolo por pantalla
	 * 
	 * @param indice indice del nodo que se va a mostrar por pantalla
	 */
	private void tratarNodo(int indice) {
		// System.out.print(nodes[indice].toString() + "\t");
	}

	/**
	 * Método que devuelve una cadena con la información del grafo
	 * 
	 * @return información del grafo
	 */
	/*public String toStringAssert() {
		String cadena = "";
		T[] nodos = getNodes();
		boolean[][] aristas = getEdges();
		double[][] pesos = getWeights();
		int numNodos = getNumNodes();

		cadena += "VECTOR NODOS\n{";
		for (int i = 0; i < nodos.length; i++) {
			if (nodos[i] == null || i >= numNodos) {
				cadena += "null";
			} else {
				cadena += nodos[i].toString();
			}
			if (i < nodos.length - 1) {
				cadena += ", ";
			}
		}

		cadena += "}";
		cadena += "\n\nMATRIZ ARISTAS\n";
		cadena += "{";
		for (int i = 0; i < aristas.length; i++) {
			cadena += "{";
			for (int j = 0; j < aristas.length; j++) {
				if (i < numNodes && j < numNodes) {
					cadena += aristas[i][j] ? "T" : "F";
				} else {
					cadena += "F";
				}
				if (j < aristas.length - 1) {
					cadena += ", ";
				}
			}
			cadena += "}";
			if (i < aristas.length - 1) {
				cadena += ",\n ";
			}
		}
		cadena += "}";

		cadena += "\n\nMATRIZ PESOS\n";
		DecimalFormat df = new DecimalFormat(" #.## ");
		cadena += "{";
		for (int i = 0; i < pesos.length; i++) {
			cadena += "{";
			for (int j = 0; j < nodes.length; j++) {
				if (i < numNodes && j < numNodes) {
					cadena += df.format(pesos[i][j]);
				} else {
					cadena += df.format(0);
				}
				if (j < pesos.length - 1) {
					cadena += ",";
				}
			}
			cadena += "}";
			if (i < pesos.length - 1) {
				cadena += ",\n ";
			}
		}
		cadena += "}\n";
		return cadena;
	}*/

	/**
	 * Devuelve la excentricidad del nodo que se le pasa por parámetro La
	 * excentricidad de un nodo es el mayor de los costes mínimos que tienen a
	 * ese nodo como destino, desde cualquier otro
	 * 
	 * @param nodo Nodo a calcular
	 * 
	 * @return Si el nodo no existe devuelve -1
	 */
	public double excentricidad(T nodo) {
		int j = getNode(nodo);

		// Comprueba si el nodo existe
		if (j >= 0) {
			// Calcula la matriz A de floyd
			floyd();
			double excentricidad = 0.0;

			// Comprueba cual es el peso máximo en la columna del nodo pasado
			// por parámetro
			for (int i = 0; i < numNodes; i++) {
				if (aFloyd[i][j] > excentricidad) {
					excentricidad = aFloyd[i][j];
				}
			}

			return excentricidad;
		} else {
			return -1.0;
		}
	}

    
	/**
     * Devuelve una lista con todos los nodos destino para los caminos de longitud k desde un nodo determinado 
	 * @param node
	 * @param length
	 * @return
	 */
	public List<T> getNodesDestino(T node, double length) {
		double[] longCaminos = this.dijkstra(node);
		List<T> nodes = new ArrayList<T>();
		for (int i = 0; i < longCaminos.length; i++) {
			if (longCaminos[i] == length)
				nodes.add(this.nodes[i]);
		}
		return nodes;
	}

}
