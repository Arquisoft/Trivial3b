package models;

import game.Casilla;
import game.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Player extends Model implements Serializable{

	@Id
	private String id;
	@Required
	private String pass;
	@Transient
	private Map<Category, Boolean> quesitos = new EnumMap<Category, Boolean>(Category.class);
	@Transient
    private Casilla position;
	@Transient
	List<Category> categorias=new ArrayList<Category>();
	public List<Category> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Category> categorias) {
		this.categorias = categorias;
	}

	public Casilla getPosition() {
		return position;
	}

	public void setPosition(Casilla position) {
		this.position = position;
	}
	private static final long serialVersionUID = 1L;

	public Player(String id, String pass) {
		this.id = id;
		this.pass = pass;
		categorias.add(Category.CIENCIAYTECNOLOGIA);
		categorias.add(Category.DEPORTES);
		categorias.add(Category.ESPECTACULOSYENTRETENIMIENTO);
		categorias.add(Category.GEOGRAFIA);
		categorias.add(Category.HISTORIA);
		categorias.add(Category.ARTEYLITERATURA);
	}

	public static Player authenticate(String id, String password) {

		Player user = get(id);
		if (user == null)
			return null;
		if (user.pass.equals(password))
			return user;
		else
			return null;
	}

	public static Player get(String id) {
		return finder.byId(id);
	}
	
	public static void addUser(Player user) {
		user.save();
	}
	public Map<Category, Boolean> getQuesitos() {
        return Collections.unmodifiableMap(quesitos);
    }

    /**
     * Indica si el jugador tiene todos los quesitos o no
     * 
     * @return true si tiene todos los quesitos, false en caso contrario.
     */
    public boolean hasAllQuesitos() {
        for (Category cat : Category.values()) {
            if (!quesitos.containsKey(cat) && !cat.equals(Category.FINAL)) {
                return false;
            }
        }
        return true;
    }

    /**
	 * Metodo que devuelve el número de quesitos que lleva un determinado jugador
     * @return int el numero de quesitos que lleva conseguidos
     */
    public int numeroQuesitos() {
        int quesos = 0;
        for (Category cat : Category.values()) {
            if (quesitos.containsKey(cat)) {
                quesos++;
            }
        }
        return quesos;

    }
    
    /**
     * Metodo que a partir de la categoria añade un quesito al jugador
     * 
     * @param cat, categoria de la que se le va a asignar el quesito al jugador
     */
    public void addQuesito(Category cat) {
        quesitos.put(cat, true);
    }
    
	private static Finder<String, Player> finder = new Finder<String, Player>(String.class, Player.class);
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
