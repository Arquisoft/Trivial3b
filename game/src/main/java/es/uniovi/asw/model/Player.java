package es.uniovi.asw.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Player {

    private String username;
    /**
     * Cantidad de quesitos de cada jugador
     */
	private Map<Category, Boolean> quesitos = new EnumMap<Category, Boolean>(Category.class);
    private Casilla position;

    public Player(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Casilla getPosition() {
        return position;
    }

    public void setPosition(Casilla position) {
        this.position = position;
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
            if (!quesitos.containsKey(cat)) {
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
}
