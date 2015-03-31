package es.uniovi.asw.model;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.graph.Graph;
import es.uniovi.asw.util.FileUtil;

public class Tablero {
	public static final int CIRCULAR = 1;
	public static final int CUADRADO = 2;

	public static void main(String... args) {
		getTablero(CIRCULAR);
	}

	public static Graph<Casilla> getTablero(int type) {
		switch (type) {
		case CIRCULAR:
			return cargaGrafo("src/main/resources/grafoCircular.txt");
		case CUADRADO:
			return cargaGrafo("src/main/resources/grafoCuadrado.txt");

		default:
			return null;
		}
	}

	private static Graph<Casilla> cargaGrafo(String location) {
		String file = FileUtil.getFile(location);

		// Dividimos el archivo en lineaas
		String[] lineas = file.split("[\r\n]");

		List<String> nodos = new ArrayList<String>();
		List<String> aristas = new ArrayList<String>();

		// Separamos las lineas de nodo de las de arista
		for (String linea : lineas) {
			linea = linea.trim();

			if (linea.isEmpty() || linea.startsWith("#"))
				continue;

			if (linea.startsWith("N:"))
				nodos.add(linea.substring(2));
			else if (linea.startsWith("A:"))
				aristas.add(linea.substring(2));
		}

		// Creamos el tablero del tamaño adecuado
		Graph<Casilla> tablero = new Graph<Casilla>(nodos.size());

		// Y una lista de casillas para añadir luego las aristas
		List<Casilla> casillas = new ArrayList<Casilla>();

		for (String nodo : nodos) {
			Casilla cas = getCasilla(nodo);

			casillas.add(cas);
			tablero.addNode(cas);
		}

		for (String arista : aristas) {
			addEdge(tablero, casillas, arista);
		}
		return tablero;
	}

	private static Casilla getCasilla(String nodo) {
		String[] params = nodo.split("\\|");

		int id = Integer.parseInt(params[0].trim());

		// La categoria puede ser null
		Category category = null;
		if (!params[1].trim().isEmpty())
			category = Category.parse(params[1].trim());

		TipoCasilla type = TipoCasilla.valueOf(params[2]);

		return new Casilla(id, category, type);
	}

	private static void addEdge(Graph<Casilla> grafo, List<Casilla> casillas,
			String arista) {
		String[] params = arista.split("\\|");

		int id1 = Integer.parseInt(params[0].trim());
		int id2 = Integer.parseInt(params[1].trim());

		Casilla c1 = find(casillas, id1);
		Casilla c2 = find(casillas, id2);

		// Aristas bidireccionales
		grafo.addEdge(c1, c2, 1.0);
		grafo.addEdge(c2, c1, 1.0);
	}

	private static Casilla find(List<Casilla> casillas, int id) {
		for (Casilla casilla : casillas) {
			if (casilla.getId() == id)
				return casilla;
		}

		return null;
	}
}