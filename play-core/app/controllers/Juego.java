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
	public final static List<String> coordenadas = new ArrayList<String>();
	public final static List<String> centrosx = new ArrayList<String>();
	public final static List<String> centrosy = new ArrayList<String>();
	public final static List<String> centrosximages = new ArrayList<String>();
	public final static List<String> centrosyimages = new ArrayList<String>();
	public final static GameService game = new GameServiceImpl();

	public static Result jugar(Integer posicion) {
		game.moveTo(game.getCasilla(posicion + 1));
		return redirect("/index/"+tipoTablero);
	}

	public static Result tirar() {
		game.throwDice();
		game.move();
		return redirect("/index/"+tipoTablero);
	}

	public static Result respuestaCorrecta() {
		game.respuestaCorrecta();
		return redirect("/index/"+tipoTablero);
	}

	public static Result respuestaIncorrecta() {
		game.respuestaIncorrecta();
		return redirect("/index/"+tipoTablero);
	}

	@Security.Authenticated(ClientSecured.class)
	public static Result showIndex(Integer tablero) throws IOException {
		game.setTablero(tablero);
		if (game.getPlayers().size() == 0) {
			game.addPlayer(new Player("f", "f"));
			game.addPlayer(new Player("cristian", "cris"));
		}
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
		tipoTablero=tablero;
		switch (tablero) {
		case 1:
			String fichero = FileUtil
					.getFile("public/resources/botonesCircular.txt");
			String[] lineas = fichero.split("[\n]");
			for (int i = 0; i < lineas.length; i++)
				coordenadas.add(lineas[i]);
			String fichero2 = FileUtil.getFile("public/resources/centros.txt");
			String[] lineas2 = fichero2.split("[\n]");
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
