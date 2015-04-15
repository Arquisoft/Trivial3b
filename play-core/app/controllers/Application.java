package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result showLogin() {
        return ok(login.render(Form.form(Login.class)));
    }
    
    public static Result showRegister() {
        return ok(register.render(Form.form(Register.class)));
    }
    
    public static Result showIndex() {
        return ok(index.render());
    }
    
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("id", loginForm.get().id);
            return redirect(routes.Application.showIndex());
        }
    }
    
    public static Result register() {
    	
    	Form<Register> registerForm = Form.form(Register.class).bindFromRequest();
    	if(registerForm.hasErrors()) {
    		return badRequest(register.render(registerForm));
    	} else {
    		Usuario.addUser(new Usuario(registerForm.get().id, registerForm.get().password));
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
            if (Usuario.authenticate(id, password) == null) {
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
            } else if (Usuario.get(id) != null) {
            	return "El ID de usuario introducido ya existe";
            }
            return null;
        }
    }
}
