package controllers;

import game.GameService;
import game.GameServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import models.Player;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.FileUtil;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.authenticators.ClientSecured;

public class Juego extends Controller {
	static Socket so;
	DataInputStream entrada;
	static DataOutputStream mensaje;
	public static Integer tipoTablero;
	public  static List<String> coordenadas = new ArrayList<String>();
	public  static List<String> centrosx = new ArrayList<String>();
	public  static List<String> centrosy = new ArrayList<String>();
	public  static List<String> centrosximages = new ArrayList<String>();
	public  static List<String> centrosyimages = new ArrayList<String>();
	public  static GameService game = new GameServiceImpl();

	public static Result jugar(Integer posicion) {
		game.moveTo(game.getCasilla(posicion + 1));
		return redirect("/indexr/");
	}

	public static Result tirar() {
		game.throwDice();
		game.move();
		return redirect("/indexr/");
	}

	public static Result respuestaCorrecta() {
		game.respuestaCorrecta();
		return redirect("/indexr/");
	}

	public static Result respuestaIncorrecta() {
		game.respuestaIncorrecta();
		return redirect("/indexr/");
	}
	public static Result redirectIndex(){
		return ok(index.render(coordenadas, game, centrosx, centrosy,
				centrosximages, centrosyimages));
	}
	@Security.Authenticated(ClientSecured.class)
	public static Result showIndex(Integer tablero) throws IOException {
		game=new GameServiceImpl();
		game.setTablero(tablero);
		Player player=new Player();
		player=Player.get(session("id"));
		game.addPlayer(player);
		leerTablero(tablero);
		return ok(index.render(coordenadas, game, centrosx, centrosy,
				centrosximages, centrosyimages));
	}

	public static List<String> getCoordenadas() {
		String fichero = FileUtil
				.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		return coordenadas;
	}

	public static void leerTablero(Integer tablero) {
		String fichero=null;
		String[] lineas=null;
		String[] lineas2=null;
		String fichero2=null;
		coordenadas=new ArrayList<String>();
		centrosx=new ArrayList<String>();
		centrosy=new ArrayList<String>();
		centrosximages=new ArrayList<String>();
		centrosyimages=new ArrayList<String>();
		switch (tablero) {
		case 1:
			Logger.info("circulo");
			fichero = FileUtil
					.getFile("public/resources/botonesCircular.txt");
			lineas = fichero.split("[\n]");
			for (int i = 0; i < lineas.length; i++)
				coordenadas.add(lineas[i]);
			fichero2 = FileUtil.getFile("public/resources/centros.txt");
			lineas2 = fichero2.split("[\n]");
			for (int i = 0; i < lineas2.length; i++) {
				String[] datos = lineas2[i].trim().split(",");
				centrosx.add(datos[0]);
				int centro = Integer.parseInt(datos[0]);
				int centroy = Integer.parseInt(datos[1]);
				centrosximages.add(String.valueOf(centro - 19));
				centrosyimages.add(String.valueOf(centroy - 34));
				centrosy.add(datos[1]);
			}
			break;
		case 2:
			Logger.info("cuadrado");
			fichero = FileUtil
			.getFile("public/resources/botonesCuadrado.txt");
			lineas = fichero.split("[\n]");
			for (int i = 0; i < lineas.length; i++)
				coordenadas.add(lineas[i]);
			fichero2 = FileUtil.getFile("public/resources/centrosCuadrado.txt");
			lineas2 = fichero2.split("[\n]");
			for (int i = 0; i < lineas2.length; i++) {
				String[] datos = lineas2[i].trim().split(",");
				centrosx.add(datos[0]);
				int centro = Integer.parseInt(datos[0]);
				int centroy = Integer.parseInt(datos[1]);
				centrosximages.add(String.valueOf(centro - 19));
				centrosyimages.add(String.valueOf(centroy - 34));
				centrosy.add(datos[1]);
			}
			break;
		
		}
	}
}
