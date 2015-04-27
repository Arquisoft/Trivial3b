package controllers;

import game.Category;
import game.GameService;
import game.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;

import models.Estadistica;
import models.Player;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;
import controllers.authenticators.AdminSecured;
import controllers.authenticators.ClientSecured;

public class Application extends Controller {
	public static List<String> coordenadas = new ArrayList<String>();
	public static GameService game=new GameServiceImpl();
	
	public static Result showLogin() {
		cargarDatos();
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result showRegister() {
		return ok(register.render(Form.form(Register.class)));
	}

	@Security.Authenticated(ClientSecured.class)
	public static Result showChoice() {
		return ok(choice.render());
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
				return redirect(routes.Juego.showIndex());
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

	
	private static boolean primeraVez = true; //TEMPORAL
	private static void cargarDatos() {
		
		if(primeraVez) {
			Player p0 = new Player("admin", "admin");
			p0.save();
			Player p1 = new Player("usuario1", "usuario1");
			p1.save();
			Player p2 = new Player("usuario2", "usuario2");
			p2.save();
			Player p3 = new Player("usuario3", "usuario3");
			p3.save();
			Estadistica e1 = new Estadistica(p1.getId(), "P0001", Category.DEPORTES.toString(), 2, 1);
			e1.save();
			Estadistica e2 = new Estadistica(p1.getId(), "P0002", Category.GEOGRAFIA.toString(), 0, 1);
			e2.save();
			Estadistica e3 = new Estadistica(p1.getId(), "P0003", Category.HISTORIA.toString(), 1, 3);
			e3.save();
			Estadistica e4 = new Estadistica(p2.getId(), "P0001", Category.DEPORTES.toString(), 0, 0);
			e4.save();
			Estadistica e5 = new Estadistica(p2.getId(), "P0002", Category.GEOGRAFIA.toString(), 2, 1);
			e5.save();
			Estadistica e6 = new Estadistica(p2.getId(), "P0003", Category.HISTORIA.toString(), 1, 1);
			e6.save();
			Estadistica e7 = new Estadistica(p3.getId(), "P0001", Category.DEPORTES.toString(), 4, 0);
			e7.save();
			Estadistica e8 = new Estadistica(p3.getId(), "P0002", Category.GEOGRAFIA.toString(), 1, 2);
			e8.save();
			Estadistica e9 = new Estadistica(p3.getId(), "P0003", Category.HISTORIA.toString(), 0, 0);
			e9.save();
			
			primeraVez = false;
		}
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
