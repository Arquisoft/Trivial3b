package controllers;

import game.GameService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import play.mvc.Controller;

public class Conexion extends Controller {
	final int PUERTO = 9000;

	// ServerSocket sc;
	// Socket so;

	// DataOutputStream salida;

	// String mensajeRecibido;

	public void initServer()

	{

		BufferedReader entrada;

		try

		{

			ServerSocket ss = new ServerSocket(PUERTO);
			Socket s = ss.accept();
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			GameService to = (GameService) ois.readObject();
			if (to != null) {
				System.out.println(to.getDiceNumber());
			}
			System.out.println((String) ois.readObject());
			is.close();
			s.close();
			ss.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}