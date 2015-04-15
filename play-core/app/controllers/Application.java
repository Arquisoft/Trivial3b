package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class Application extends Controller {

    public static Result showLogin() {
        return ok(login.render(Form.form(Login.class)));
    }
    
    public static Result showRegister() {
        return ok(register.render());
    }
    
    public static Result showIndex() {
        return ok(index.render());
    }
    
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("id", loginForm.get().id);
            return redirect(routes.Application.showIndex());
        }
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
}
