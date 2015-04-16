package controllers.authenticators;

import play.Logger;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import controllers.routes;

public class ClientSecured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("id");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
    	Logger.info("Debes estar logueado para poder acceder a esa página");
        return redirect(routes.Application.showLogin());
    }
}
