package controllers.authenticators;

import play.Logger;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import controllers.routes;

public class AdminSecured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        String id = ctx.session().get("id");
        if(id == null)
        	return null;
        else if(!id.equals("admin"))
        	return null;
        else
        	return id;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
    	Logger.info("Sólo puede acceder a dicha página el Administrador");
        return redirect(routes.Application.showLogin());
    }
}
