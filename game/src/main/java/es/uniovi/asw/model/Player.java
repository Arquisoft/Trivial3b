package es.uniovi.asw.model;

public class Player {

	private String username;
	/**
	 * Cantidad de quesitos de cada jugador, segun las posiciones: 0-Deportes
	 * 1-Historia 2-Espectaculos y entretenimiento 3-Ciencia y tecnologia 4-Arte
	 * y literatura 5-Geografia
	 */
	private Boolean[] quesitos = new Boolean[6];
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

	public Boolean[] getQuesitos() {
		return quesitos;
	}

	public void setQuesitos(Boolean[] quesitos) {
		this.quesitos = quesitos;
	}

	public Casilla getPosition() {
		return position;
	}

	public void setPosition(Casilla position) {
		this.position = position;
	}

	/**
	 * Indica si el jugador tiene todos los quesitos o no
	 * 
	 * @return true si tiene todos los quesitos, false en caso contrario.
	 */
	public boolean hasAllQuesitos() {
		for (Boolean q : quesitos) {
			if (!q)
				return false;
		}
		return true;
	}

	/**
	 * Metodo que a partir de la categoria añade un quesito al jugador
	 * @param cat, categoria de la que se le va a asignar el quesito al jugador 
	 */
	public void addQuesito(Category cat) {

		switch (cat) {
		case DEPORTES:
			quesitos[0] = true;
			return;
		case HISTORIA:
			quesitos[1] = true;
			return;
		case ESPECTACULOSYENTRETENIMIENTO:
			quesitos[2] = true;
			return;
		case CIENCIAYTECNOLOGIA:
			quesitos[3] = true;
			return;
		case ARTEYLITERATURA:
			quesitos[4] = true;
			return;
		case GEOGRAFIA:
			quesitos[5] = true;
			return;
		}
	}
}
