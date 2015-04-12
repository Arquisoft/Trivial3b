package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    
  /** 
   * Ejemplo de los metodos referenciados en routes
   *  public static void createUser(User newUser) {
        newUser.save();
        user(newUser.id);
    }

    public static void updateUser(Long id, User user) {
        User dbUser = User.findById(id);
        dbUser.updateDetails(user); // some model logic you would write to do a safe merge
        dbUser.save();
        user(id);
    }
    */

}
