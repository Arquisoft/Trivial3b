package es.uniovi.asw.game.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import es.uniovi.asw.game.GameService;
import es.uniovi.asw.graph.Graph;
import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Player;
import es.uniovi.asw.model.Pregunta;
import es.uniovi.asw.model.Tablero;
import es.uniovi.asw.model.TipoCasilla;
import es.uniovi.asw.persistence.PersistenceFactory;
import es.uniovi.asw.persistence.impl.SimplePersistenceFactory;
import es.uniovi.asw.questions.MongoQuestions;

public class GameServiceImpl implements GameService {
	private static final int TIPO_GRAFO = 1;
	private static final int MAX_PLAYERS = 6;

	private Random rand = new Random();
	private MongoQuestions mongoQuestions = new MongoQuestions();

	// Tablero y jugadores
	private List<Player> players = new ArrayList<Player>();
	private Graph<Casilla> tablero;

	// Estado actual
	private int activePlayer;
	private boolean diceThrown;
	private int diceNumber;
	private Pregunta questionGiven;

	private Player winner;

	// factory de la bd para introducir las estadisticas
	private PersistenceFactory persistencia = new SimplePersistenceFactory();

	public GameServiceImpl(int var) {
		tablero = Tablero.getTablero(var);
	}

	public GameServiceImpl() {
		this(TIPO_GRAFO);
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
	public Player getCurrentTurnPlayer() {
		if (players.size() == 0) {
			return null;
		}

		return players.get(activePlayer);
	}

	@Override
	public boolean canThrowDice() {
		return getCurrentTurnPlayer() != null && !diceThrown;
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
		return getCurrentTurnPlayer() != null && diceThrown
				&& questionGiven == null;
	}

	@Override
	public List<Casilla> getMoves() {
		if (!canMove()) {
			return null;
		}

		Casilla origen = getCurrentTurnPlayer().getPosition();
		return tablero.getNodesDestino(origen, diceNumber);
	}

	@Override
	public boolean moveTo(Casilla casilla) {
		if (!getMoves().contains(casilla)) {
			return false;
		}

		getCurrentTurnPlayer().setPosition(casilla);

		// Caer en tirar de nuevo es como acertar directamente
		if (casilla.getTipoCasilla().equals(TipoCasilla.TIRADAEXTRA)) {
			// Reseteamos los valores
			resetTurn();

		} else {
			Category cat = getCurrentTurnPlayer().getPosition().getCategoria();
			questionGiven = mongoQuestions.getQuestion(cat);
		}

		return true;
	}

	@Override
	public Pregunta getPregunta() {
		return questionGiven;
	}

	@Override
	public void respuestaCorrecta() {
		if (getCurrentTurnPlayer() != null && diceThrown
				&& questionGiven != null) {
			Player player = getCurrentTurnPlayer();

			// TODO: Almacenar en base de datos
			Map<String, Object> pregunta = persistencia.createPreguntaDao()
					.findByEnunciado(questionGiven.getQuestion());
			if (pregunta == null) {
				persistencia.createPreguntaDao().insertar(questionGiven);
				pregunta = persistencia.createPreguntaDao().findByEnunciado(
						questionGiven.getQuestion());
				persistencia.createPreguntaDao().guardarResultado(pregunta,
						true);
			}
			persistencia.createPreguntaDao().guardarResultado(pregunta, true);


			// Si es una pregunta de quesito, lo añadimos

			if (player.getPosition().getTipoCasilla()
					.equals(TipoCasilla.QUESITO)) {
				player.addQuesito(player.getPosition().getCategoria());
			}

			// Reseteamos los valores
			resetTurn();

		} else {
			// Lanzar excepcion?
		}
	}

	@Override
	public void respuestaIncorrecta() {
		if (getCurrentTurnPlayer() != null && diceThrown
				&& questionGiven != null) {

			// TODO: Almacenar en base de datos
			Map<String, Object> pregunta = persistencia.createPreguntaDao()
					.findByEnunciado(questionGiven.getQuestion());
			if (pregunta == null) {
				persistencia.createPreguntaDao().insertar(questionGiven);
				pregunta = persistencia.createPreguntaDao().findByEnunciado(
						questionGiven.getQuestion());
				persistencia.createPreguntaDao().guardarResultado(pregunta,
						true);
			}
			persistencia.createPreguntaDao().guardarResultado(pregunta, false);
			
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

	@Override
	public Player getGanador() {
		return winner;
	}

	private void resetTurn() {
		diceThrown = false;
		questionGiven = null;
	}

	private void nextTurn() {
		activePlayer++;
		activePlayer %= players.size();
	}
}
