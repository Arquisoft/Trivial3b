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
		Casilla c3 = new Casilla(3, null);// es de volver a tirar
		Casilla c4 = new Casilla(4, Category.ESPECTACULOSYENTRETENIMIENTO);
		Casilla c5 = new Casilla(5, Category.GEOGRAFIA);
		Casilla c6 = new Casilla(6, null);// volve a tirar
		Casilla c7 = new Casilla(7, Category.ARTEYLITERATURA);
		Casilla c8 = new Casilla(8, Category.HISTORIA);
		Casilla c9 = new Casilla(9, Category.ARTEYLITERATURA);
		Casilla c10 = new Casilla(10, null);// volver a tirar
		Casilla c11 = new Casilla(11, Category.CIENCIAYTECNOLOGIA);
		Casilla c12 = new Casilla(12, Category.DEPORTES);
		Casilla c13 = new Casilla(13, null);// volver atirar
		Casilla c14 = new Casilla(14, Category.GEOGRAFIA);
		Casilla c15 = new Casilla(15, Category.ESPECTACULOSYENTRETENIMIENTO);
		Casilla c16 = new Casilla(16, Category.GEOGRAFIA);
		Casilla c17 = new Casilla(17, null);// volver a tirar
		Casilla c18 = new Casilla(18, Category.ARTEYLITERATURA);
		Casilla c19 = new Casilla(19, Category.HISTORIA);
		Casilla c20 = new Casilla(20, null);// volver a tirar
		Casilla c21 = new Casilla(21, Category.DEPORTES);
		Casilla c22 = new Casilla(22, Category.CIENCIAYTECNOLOGIA);
		Casilla c23 = new Casilla(23, Category.DEPORTES);
		Casilla c24 = new Casilla(24, null);// volver a tirar
		Casilla c25 = new Casilla(25, Category.GEOGRAFIA);
		Casilla c26 = new Casilla(26, Category.ESPECTACULOSYENTRETENIMIENTO);
		Casilla c27 = new Casilla(27, null);// volver a tirar
		Casilla c28 = new Casilla(28, Category.HISTORIA);
		Casilla c29 = new Casilla(29, Category.ARTEYLITERATURA);
		Casilla c30 = new Casilla(30, Category.HISTORIA);
		Casilla c31 = new Casilla(31, null);// volver a tirar
		Casilla c32 = new Casilla(32, Category.DEPORTES);
		Casilla c33 = new Casilla(33, Category.CIENCIAYTECNOLOGIA);
		Casilla c34 = new Casilla(34, null);// volver a tirar
		Casilla c35 = new Casilla(35, Category.ESPECTACULOSYENTRETENIMIENTO);
		Casilla c36 = new Casilla(36, Category.GEOGRAFIA);
		Casilla c37 = new Casilla(37, Category.ESPECTACULOSYENTRETENIMIENTO);
		Casilla c38 = new Casilla(38, null);// volver a tirar
		Casilla c39=new Casilla(39, Category.HISTORIA);
		Casilla c40=new Casilla(40,Category.ARTEYLITERATURA);
		Casilla c41=new Casilla(41,null);//volver a tirar
		Casilla c42=new Casilla(42,Category.CIENCIAYTECNOLOGIA);
		
		tablero.addNode(c1);
		tablero.addNode(c2);
		tablero.addNode(c3);
		tablero.addNode(c4);
		tablero.addNode(c5);
		tablero.addNode(c6);
		tablero.addNode(c7);
		tablero.addNode(c8);
		tablero.addNode(c9);
		tablero.addNode(c10);
		tablero.addNode(c11);
		tablero.addNode(c12);
		tablero.addNode(c13);
		tablero.addNode(c14);
		tablero.addNode(c15);
		tablero.addNode(c16);
		tablero.addNode(c17);
		tablero.addNode(c18);
		tablero.addNode(c19);
		tablero.addNode(c20);
		tablero.addNode(c21);
		tablero.addNode(c22);
		tablero.addNode(c23);
		tablero.addNode(c24);
		tablero.addNode(c25);
		tablero.addNode(c26);
		tablero.addNode(c27);
		tablero.addNode(c28);
		tablero.addNode(c29);	
		tablero.addNode(c30);
		tablero.addNode(c31);
		tablero.addNode(c32);
		tablero.addNode(c33);
		tablero.addNode(c34);
		tablero.addNode(c35);
		tablero.addNode(c36);
		tablero.addNode(c37);
		tablero.addNode(c38);
		tablero.addNode(c39);
		tablero.addNode(c40);
		tablero.addNode(c41);
		tablero.addNode(c42);

		tablero.addEdge(c1, c2, 1);
		tablero.addEdge(c2, c1, 1);
		tablero.addEdge(c2, c3, 1);
		tablero.addEdge(c3, c2, 1);
		tablero.addEdge(c3, c4, 1);
		tablero.addEdge(c4, c3, 1);
		tablero.addEdge(c4, c5, 1);
		tablero.addEdge(c5, c4, 1);
		tablero.addEdge(c5, c6, 1);
		tablero.addEdge(c6, c5, 1);
		tablero.addEdge(c6, c7, 1);
		tablero.addEdge(c7, c6, 1);
		tablero.addEdge(c7, c8, 1);
		tablero.addEdge(c8, c7, 1);
		tablero.addEdge(c8, c9, 1);
		tablero.addEdge(c9, c8, 1);
		tablero.addEdge(c9, c10, 1);
		tablero.addEdge(c10, c9, 1);
		tablero.addEdge(c10, c11, 1);
		tablero.addEdge(c11, c10, 1);
		tablero.addEdge(c11, c12, 1);
		tablero.addEdge(c12, c11, 1);
		tablero.addEdge(c12, c13, 1);
		tablero.addEdge(c13, c12, 1);
		tablero.addEdge(c13, c14, 1);
		tablero.addEdge(c14, c13, 1);
		tablero.addEdge(c14, c15, 1);
		tablero.addEdge(c15, c14, 1);
		tablero.addEdge(c15, c16, 1);
		tablero.addEdge(c16, c17, 1);
		tablero.addEdge(c17, c18, 1);
		tablero.addEdge(c18, c17, 1);
		tablero.addEdge(c18, c19, 1);
		tablero.addEdge(c19, c18, 1);
		tablero.addEdge(c19, c20, 1);
		tablero.addEdge(c20, c19, 1);
		tablero.addEdge(c21, c20, 1);
		tablero.addEdge(c20, c21, 1);
		tablero.addEdge(c21, c22, 1);
		tablero.addEdge(c22, c21, 1);
		tablero.addEdge(c22, c23, 1);
		tablero.addEdge(c23, c24, 1);
		tablero.addEdge(c24, c25, 1);
		tablero.addEdge(c25, c24, 1);
		tablero.addEdge(c25, c26, 1);
		tablero.addEdge(c26, c25, 1);
		tablero.addEdge(c26, c27, 1);
		tablero.addEdge(c27, c26, 1);
		tablero.addEdge(c27, c28, 1);
		tablero.addEdge(c28, c27, 1);
		tablero.addEdge(c28, c29, 1);
		tablero.addEdge(c29, c30, 1);
		tablero.addEdge(c30, c31, 1);
		tablero.addEdge(c31, c30, 1);
		tablero.addEdge(c31, c32, 1);
		tablero.addEdge(c32, c31, 1);
		tablero.addEdge(c32, c33, 1);
		tablero.addEdge(c33, c32, 1);
		tablero.addEdge(c33, c34, 1);
		tablero.addEdge(c34, c33, 1);
		tablero.addEdge(c34, c35, 1);
		tablero.addEdge(c35, c36, 1);
		tablero.addEdge(c36, c37, 1);
		tablero.addEdge(c37, c36, 1);
		tablero.addEdge(c37, c38, 1);
		tablero.addEdge(c38, c37, 1);
		tablero.addEdge(c38, c39, 1);
		tablero.addEdge(c39, c38, 1);
		tablero.addEdge(c39, c40, 1);
		tablero.addEdge(c40, c39, 1);
		tablero.addEdge(c40, c41, 1);
		tablero.addEdge(c41, c40, 1);
		tablero.addEdge(c41, c42, 1);
		tablero.addEdge(c42, c41, 1);
		tablero.addEdge(c1, c42, 1);
		tablero.addEdge(c42, c1, 1);	

		return tablero;

	}
}