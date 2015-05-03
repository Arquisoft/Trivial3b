package controllers;

import game.GameService;
import game.GameServiceImpl;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Player;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.WebSocket;
import util.FileUtil;
import views.html.deleteGame;
import views.html.findGame;
import views.html.games;
import views.html.index;
import controllers.authenticators.ClientSecured;

public class Juego extends Controller {
	public static final Map<String, GameService> salas = new HashMap<String, GameService>();
	static List<String> coordenadas = new ArrayList<String>();
	static List<String> centrosx = new ArrayList<String>();
	static List<String> centrosy = new ArrayList<String>();
	static List<String> centrosximages = new ArrayList<String>();
	static List<String> centrosyimages = new ArrayList<String>();
	static GameService game;

	public static Result jugar(Integer posicion) {
		game.moveTo(game.getCasilla(posicion + 1));
		return redirect("/indexr/");
	}

	public static Result tirar() {
		game.throwDice();
		game.move();
		return redirect("/indexr/");
	}

	public static Result misPartidas() {
		return ok(games.render(salas, session("id")));
	}

	public static Result respuestaCorrecta() {
		game.respuestaCorrecta();
		if (game.partidaFinalizada()) {
			// Borrar la partida actual para dicho usuario
		}
		return redirect("/indexr/");
	}

	public static Result respuestaIncorrecta() {
		game.respuestaIncorrecta();
		return redirect("/indexr/");
	}

	public static Result findGame() {
		return ok(findGame.render(salas));
	}

	public static Result deleteGame() {
		return ok(deleteGame.render(salas, session("id")));
	}

	public static Result borrar(String id) {
		salas.remove(id);
		return ok(deleteGame.render(salas, session("id")));
	}

	public static Result wsJs() {
		return ok(views.js.ws.render());
	}

	public static WebSocket<String> wsInterface() {
		return new WebSocket<String>() {

			// called when websocket handshake is done
			public void onReady(WebSocket.In<String> in,
					WebSocket.Out<String> out) {
				SimpleChat.start(in, out);
			}
		};
	}

	public static Result redirectIndex() {
		Player player = new Player();
		player = Player.get(session("id"));
		return ok(index.render(coordenadas, game, centrosx, centrosy,
				centrosximages, centrosyimages, player.getId(), false));
	}

	@Security.Authenticated(ClientSecured.class)
	public static Result showIndex(Integer tablero) throws IOException {
		game = null;
		Player playert = new Player();
		playert = Player.get(session("id"));
		SecureRandom random = new SecureRandom();
		String id = new BigInteger(15, random).toString(32);
		for (String key : salas.keySet()) {
			if (key.equals(session("id"))) {
				game = salas.get(key);
				leerTablero(game.getTipo());
			}
		}
		if (game == null) {
			game = new GameServiceImpl();
			salas.put(session("id"), game);
			game.setTablero(tablero);
			game.setId(id);
			Player player = new Player();
			player = Player.get(session("id"));
			game.addPlayer(player);
			leerTablero(tablero);
		}
		return ok(index.render(coordenadas, game, centrosx, centrosy,
				centrosximages, centrosyimages, playert.getId(), false));
	}

	public static Result joinGame(String id) {
		boolean isPlaying = false;
		for (String key : salas.keySet()) {
			if (id.equals(salas.get(key).getId())) {
				for (Player p : salas.get(key).getPlayers()) {
					if (p.getId().equals(session("id"))) {
						isPlaying = true;
					}
				}
				if (!isPlaying) {
					Player player = new Player();
					player = Player.get(session("id"));
					salas.get(key).addPlayer(player);
					game = salas.get(key);
					leerTablero(game.getTipo());
				} else {
					badRequest();
				}
			}
		}
		return redirect("/indexr/");
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
		String fichero = null;
		String[] lineas = null;
		String[] lineas2 = null;
		String fichero2 = null;
		coordenadas = new ArrayList<String>();
		centrosx = new ArrayList<String>();
		centrosy = new ArrayList<String>();
		centrosximages = new ArrayList<String>();
		centrosyimages = new ArrayList<String>();
		switch (tablero) {
		case 1:
			Logger.info("circulo");
			fichero = FileUtil.getFile("public/resources/botonesCircular.txt");
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
			fichero = FileUtil.getFile("public/resources/botonesCuadrado.txt");
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
