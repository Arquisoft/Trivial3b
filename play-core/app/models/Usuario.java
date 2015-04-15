package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Usuario extends Model {

	@Id
	private String id;
	@Required
	private String pass;
	private static final long serialVersionUID = 1L;

	public Usuario(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public static Usuario authenticate(String id, String password) {

		Usuario user = finder.byId(id);
		
		if (user == null)
			return null;
		else if (user.pass.equals(password))
			return user;
		else
			return null;
	}

	public static Usuario get(String id) {
		return finder.byId(id);
	}
	
	public static void addUser(Usuario user) {
		user.save();
	}

	private static Finder<String, Usuario> finder = new Finder<String, Usuario>(String.class, Usuario.class);
}
