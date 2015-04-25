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
	public final static List<String> coordenadas = new ArrayList<String>();
	public final static List<String> centrosx = new ArrayList<String>();
	public final static List<String> centrosy = new ArrayList<String>();
	public final static List<String> centrosximages = new ArrayList<String>();
	public final static List<String> centrosyimages = new ArrayList<String>();
	public final static GameService game = new GameServiceImpl();
	public static GameService gameWeb = new GameServiceImpl(1);

	public static Result jugar(Integer posicion) {
		game.moveTo(game.getCasilla(posicion + 1));
		Logger.info(String.valueOf(posicion));
		return redirect("/index");
	}

	public static Result tirar() {
		game.throwDice();
		game.move();
		return redirect("/index");
	}

	public static Result respuestaCorrecta() {
		game.respuestaCorrecta();
		return redirect("/index");
	}

	public static Result respuestaIncorrecta() {
		game.respuestaIncorrecta();
		return redirect("/index");
	}

	@Security.Authenticated(ClientSecured.class)
	public static Result showIndex() throws IOException {

		if (game.getPlayers().size() == 0) {
			game.addPlayer(new Player("f", "f"));
			game.addPlayer(new Player("cristian", "cris"));
		}
		String fichero = FileUtil
				.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		String fichero2 = FileUtil.getFile("public/resources/centros.txt");
		String[] lineas2 = fichero2.split("[\n]");
		for (int i = 0; i < lineas2.length; i ++) {
			String[] datos=lineas2[i].trim().split(",");
			centrosx.add(datos[0]);
			int centro=Integer.parseInt(datos[0]);
			int centroy=Integer.parseInt(datos[1]);
			centrosximages.add(String.valueOf(centro-19));
			centrosyimages.add(String.valueOf(centroy-34));
			centrosy.add(datos[1]);
		}
		JsonNode node=Json.toJson(game);
		GameService gameWeb=Json.fromJson(node, GameServiceImpl.class);
		if(gameWeb.getMoves()!=null){
		System.out.println("parar");
		}
		return ok(index.render(coordenadas, gameWeb,centrosx,centrosy,centrosximages,centrosyimages));
	}

	public static List<String> getCoordenadas() {
		String fichero = FileUtil
				.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		return coordenadas;
	}

	public static void conexion(GameService game){
		try{
			Socket s = new Socket("localhost",9000);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(game);
			oos.writeObject(new String("another object from the client"));
			oos.close();
			os.close();
			s.close();
		}catch(Exception e){System.out.println(e);}
	}
}
