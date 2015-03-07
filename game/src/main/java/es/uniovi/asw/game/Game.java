package es.uniovi.asw.game;

import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Graph;

public class Game {
	public static void main(String[] args) {
		System.out.println("Juego");
	}

	public Graph getTablero() {
		
		Graph<Casilla> tablero = new Graph<Casilla>(73);

		Casilla c1 = new Casilla(1, Category.DEPORTES);
		Casilla c2 = new Casilla(2, Category.CIENCIAYTECNOLOGIA);
		// Casilla c3= new Casilla(3, );//es de volver a tirar
		Casilla c4 = new Casilla(4, Category.ESPECTACULOSYENTRETENIMIENTO);
		Casilla c5 = new Casilla(5, Category.GEOGRAFIA);
		// Casilla c6= new Casilla(6, );//volve a tirar
		Casilla c7 = new Casilla(7, Category.ARTEYLITERATURA);
		Casilla c8= new Casilla(8, Category.HISTORIA);
		Casilla c9=new Casilla(9, Category.ARTEYLITERATURA);
		//Casilla c10=new Casilla(10, )//volver a tirar 
		
		
		return tablero;

	}
}