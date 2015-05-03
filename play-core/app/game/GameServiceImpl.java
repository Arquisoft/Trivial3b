package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Player;
import models.Pregunta;
import models.Estadistica;
import controllers.MongoQuestions;

public class GameServiceImpl implements GameService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TIPO_GRAFO = 1;
	private static final int MAX_PLAYERS = 6;
	private String id;
	private Random rand = new Random();
	private transient MongoQuestions mongoQuestions;

	// Tablero y jugadores
	private List<Player> players = new ArrayList<Player>();
	private Graph<Casilla> tablero;
	private Integer tipo;

	private transient List<Casilla> moves;
	// Estado actual
	private int activePlayer;
	private boolean diceThrown;
	private int diceNumber;

	@Override
	public int getDiceNumber() {
		return diceNumber;
	}

	private Pregunta questionGiven;

	private Player winner;

	private boolean saveStats = true;

	// factory de la bd para introducir las estadisticas

	public GameServiceImpl(int var) {
		this.setTablero(var);
		this.setMongoQuestions(new MongoQuestions());
	}

	public GameServiceImpl() {
		this(TIPO_GRAFO);
		this.setMongoQuestions(new MongoQuestions());

	}

	@Override
	public boolean addPlayer(Player player) {
		if (players.size() >= MAX_PLAYERS) {
			return false;
		}

		// Si el usuario y contraseña son incorrectos
		// return false;

		players.add(player);
		player.setPosition(tablero.getNode(0));

		return true;
	}

	@Override
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	@Override
	public Player CurrentTurnPlayer() {
		if (players.size() == 0) {
			return null;
		}

		return players.get(activePlayer);
	}

	@Override
	public boolean canThrowDice() {
		return CurrentTurnPlayer() != null && !diceThrown;
	}

	@Override
	public Integer throwDice() {
		if (!canThrowDice()) {
			return null;
		}

		diceThrown = true;
		return diceNumber = rand.nextInt(6) + 1;
	}

	@Override
	public boolean canMove() {
		return CurrentTurnPlayer() != null && diceThrown
				&& questionGiven == null;
	}

	@Override
	public List<Casilla> move() {
		if (!canMove()) {
			return null;
		}

		Casilla origen = CurrentTurnPlayer().getPosition();
		moves = tablero.getNodesDestino(origen, diceNumber);
		return moves;
	}

	@Override
	public List<Casilla> getMoves() {
		return moves;
	}

	@Override
	public boolean moveTo(Casilla casilla) {

		if (!move().contains(casilla)) {
			return false;
		}
		CurrentTurnPlayer().setPosition(casilla);

		// Caer en tirar de nuevo es como acertar directamente
		if (casilla.getTipo().equals(TipoCasilla.TIRADAEXTRA)) {
			// Reseteamos los valores
			resetTurn();

		} else {
			Category cat = CurrentTurnPlayer().getPosition().getCategoria();
			this.setQuestionGiven(MongoQuestions.getRandomQuestion(cat));
		}

		return true;
	}

	@Override
	public void respuestaCorrecta() {
		if (CurrentTurnPlayer() != null && diceThrown && questionGiven != null) {

			Player player = CurrentTurnPlayer();

			// Guardar estadisticas en segundo plano
			// guardaEstadisticas(true, player.getUsername(), questionGiven);

			// Si es una pregunta de quesito, lo añadimos
			if (player.getPosition().getTipo().equals(TipoCasilla.QUESITO)) {
				player.addQuesito(player.getPosition().getCategoria());
			}

			// Reseteamos los valores
			resetTurn();

		} else {
			// Lanzar excepcion?
		}
	}

	// private void guardaEstadisticas(final boolean resultado, final String
	// user,
	// final Pregunta question) {
	// if (saveStats) {
	// try {
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// // Almacenar en base de datos estadisticas pregunta
	// // EstadisticasJugadorDao ejDao =
	// persistencia.createEstadisticasJugadorDao();
	// // JugadorDao jugadorDao = persistencia.createJugadorDao();
	// // PreguntaDao preguntaDao = persistencia.createPreguntaDao();
	// // String id = question.getId();
	//
	// Map<String, Object> pregunta = preguntaDao.findById(id);
	// if (pregunta == null) {
	// preguntaDao.insertar(question);
	// pregunta = preguntaDao.findById(id);
	// preguntaDao.guardarResultado(pregunta, resultado);
	// } else
	// preguntaDao.guardarResultado(pregunta, resultado);
	//
	// // Almacenar estadisticas jugador
	// int idJugador = jugadorDao.getIdByLogin(user);
	// pregunta = ejDao.findByJyP(idJugador, id);
	// if (pregunta == null) {
	// ejDao.insertar(idJugador, id);
	// pregunta = ejDao.findByJyP(idJugador, id);
	// ejDao.guardarResultado(pregunta, resultado);
	// } else
	// ejDao.guardarResultado(pregunta, resultado);
	// }
	// }).start();
	// } catch (Exception e) {
	// saveStats = false;
	// JOptionPane.showMessageDialog(null,
	// "Error al guardar las estadisticas, "
	// + "se han desabilitado durante esta partida");
	// e.printStackTrace();
	// }
	// }
	// }

	@Override
	public MongoQuestions getMongoQuestions() {
		return mongoQuestions;
	}

	@Override
	public void setMongoQuestions(MongoQuestions mongoQuestions) {
		this.mongoQuestions = mongoQuestions;
	}

	@Override
	public int getActivePlayer() {
		return activePlayer;
	}

	@Override
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	@Override
	public boolean isDiceThrown() {
		return diceThrown;
	}

	@Override
	public void setDiceThrown(boolean diceThrown) {
		this.diceThrown = diceThrown;
	}

	@Override
	public Pregunta getQuestionGiven() {
		return questionGiven;
	}

	@Override
	public void setQuestionGiven(Pregunta questionGiven) {
		this.questionGiven = questionGiven;
	}

	@Override
	public Graph<Casilla> getTablero() {
		return tablero;
	}

	@Override
	public Player getWinner() {
		return winner;
	}

	@Override
	public boolean isSaveStats() {
		return saveStats;
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	@Override
	public void respuestaIncorrecta() {
		if (CurrentTurnPlayer() != null && diceThrown && questionGiven != null) {

			// Guardar estadisticas en segundo plano
			// guardaEstadisticas(true, player.getUsername(), questionGiven);
			
			// Pasamos el turno
			nextTurn();

			// Reseteamos los valores
			resetTurn();

		} else {
			// Lanzar excepcion?
		}
	}

	@Override
	public boolean partidaFinalizada() {
		return winner != null;
	}

	private void resetTurn() {
		diceThrown = false;
		questionGiven = null;
	}

	private void nextTurn() {
		activePlayer++;
		activePlayer %= players.size();
	}

	@Override
	public Casilla getCasilla(int i) {
		return tablero.getNode(i - 1);
	}

	@Override
	public List<Estadistica> getAllEstadisticas() {
		return Estadistica.obtenerTodasEstadisticas();
	}

	@Override
	public Integer getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Override
	public void setTablero(int tipo) {
		setTipo(tipo);
		tablero = Tablero.getTablero(tipo);

	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public List<String> getAnswers() {
		List<String> respuestas = new ArrayList<String>();
		for (String respuesta : getQuestionGiven().getWrongAnswers()) {
			respuestas.add(respuesta);
		}
		respuestas.add(getQuestionGiven().getCorrectAnswer());
		Collections.shuffle(respuestas);
		return respuestas;
	}

}
