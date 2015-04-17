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
import scala.Int;
import util.FileUtil;
import views.html.index;
import controllers.authenticators.ClientSecured;

public class Juego extends Controller {
	public static List<String> coordenadas = new ArrayList<String>();
	public static GameService game=new GameServiceImpl();
	public static Result jugar(Integer posicion){
		game.moveTo(game.getCasilla(posicion+1));
		Logger.info(String.valueOf(posicion));
		return redirect("/index");
	}
	public static Result tirar() {
		game.throwDice();
		return redirect("/index");
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
		
		if(game.getPlayers().size()==0){
		game.addPlayer(new Player("f","f"));
		}
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
