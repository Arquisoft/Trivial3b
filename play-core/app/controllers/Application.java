package controllers;

import game.GameService;
import game.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;

import controllers.authenticators.*;
import models.Player;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.FileUtil;
import views.html.*;

public class Application extends Controller {
	public static List<String> coordenadas = new ArrayList<String>();
	public static GameService game=new GameServiceImpl();
	public static Result jugar(Long id) {
		game.addPlayer(new Player("f","f"));
		game.throwDice();
		int posicion=id.intValue();
		game.moveTo(game.getCasilla(posicion));
		String fichero = FileUtil.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		return ok(index.render(coordenadas,new Long(0)));
	}

	public static Result showLogin() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result showRegister() {
		return ok(register.render(Form.form(Register.class)));
	}

	@Security.Authenticated(ClientSecured.class)
	public static Result showIndex() {
		String fichero = FileUtil.getFile("public/resources/botonesCircular.txt");
		String[] lineas = fichero.split("[\n]");
		for (int i = 0; i < lineas.length; i++)
			coordenadas.add(lineas[i]);
		
		return ok(index.render(coordenadas,new Long(0)));
	}
	
	@Security.Authenticated(AdminSecured.class)
	public static Result showAdminStatistics() {
	
		return ok(adminStatistics.render());
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("id", loginForm.get().id);
			
			if(loginForm.get().id.equals("admin"))
				return redirect(routes.Application.showAdminStatistics());
			else
				return redirect(routes.Application.showIndex());
		}
	}

	public static Result register() {

		Form<Register> registerForm = Form.form(Register.class)
				.bindFromRequest();
		if (registerForm.hasErrors()) {
			return badRequest(register.render(registerForm));
		} else {
			Player.addUser(new Player(registerForm.get().id,
					registerForm.get().password));
			return redirect(routes.Application.showLogin());
		}
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Application.showLogin());
	}

	public static class Login {
		public String id;
		public String password;

		public String validate() {
			if (Player.authenticate(id, password) == null) {
				return "Usuario o contraseña inválida";
			}
			return null;
		}
	}

	public static class Register {
		public String id;
		public String password;
		public String password2;

		public String validate() {
			if (!password.equals(password2)) {
				return "Las contraseñas deben coincidir";
			} else if (Player.get(id) != null) {
				return "El ID de usuario introducido ya existe";
			}
			return null;
		}
	}
}
