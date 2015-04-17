package controllers;

import game.Casilla;
import game.GameService;
import game.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;

import models.Player;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.FileUtil;
import views.html.index;
import controllers.authenticators.ClientSecured;

public class Juego extends Controller {
	public static List<String> coordenadas = new ArrayList<String>();
	public static GameService game=new GameServiceImpl();
	private static int tirada=0;
	
	public static Result jugar(Long posicion){
		game.moveTo(game.getCasilla(posicion.intValue()));
		Logger.info("debug",game.getPregunta().getQuestion());
		return ok(index.render(coordenadas, game));
	}
	public static Result tirar() {
//		int posicion=id.intValue();
//		game.moveTo(game.getCasilla(posicion));
//		getCoordenadas();
		if(game.canThrowDice()){
			tirada=game.throwDice();
		return ok(index.render(coordenadas,game));
		
		}
		else{
			return ok(index.render(coordenadas,game));
		}
	}
	public static Result respuestaCorrecta(){
		game.respuestaCorrecta();
		return ok(index.render(coordenadas, game));
	}
	public static Result respuestaIncorrecta(){
		game.respuestaIncorrecta();
		return ok(index.render(coordenadas, game));
	}
	@Security.Authenticated(ClientSecured.class)
	public static Result showIndex() {
		game.addPlayer(new Player("f","f"));
		List<Casilla> cas=game.getMoves();
		String fichero = FileUtil.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		return ok(index.render(coordenadas,game));
	}
	public static List<String> getCoordenadas(){
		String fichero = FileUtil.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		return coordenadas;
	}
}
