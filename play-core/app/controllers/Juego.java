package controllers;

import game.GameService;
import game.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;

import models.Player;
import play.mvc.Controller;
import play.mvc.Result;
import util.FileUtil;
import views.html.index;

public class Juego extends Controller {
	public static List<String> coordenadas = new ArrayList<String>();
	public static GameService game=new GameServiceImpl();
	private static int tirada=0;
	public static Result tirar() {
		game.addPlayer(new Player("f","f"));
//		int posicion=id.intValue();
//		game.moveTo(game.getCasilla(posicion));
//		getCoordenadas();
		if(game.canThrowDice()){
			tirada=game.throwDice();
		return ok(index.render(coordenadas,new Long(tirada)));
		
		}
		else{
			return ok(index.render(coordenadas,new Long(tirada)));
		}
	}
	public static List<String> getCoordenadas(){
		String fichero = FileUtil.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		return coordenadas;
	}
}
