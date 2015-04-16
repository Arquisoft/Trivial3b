package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import controllers.MongoQuestions;
import models.Player;
import models.Pregunta;


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
    private Pregunta questionGiven=new Pregunta();

    private Player winner;
    
    private boolean saveStats = true;

    // factory de la bd para introducir las estadisticas
    

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
          questionGiven = MongoQuestions.getRandomQuestion(cat);
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

            // Guardar estadisticas en segundo plano
          //  guardaEstadisticas(true, player.getUsername(), questionGiven);

            // Si es una pregunta de quesito, lo añadimos
            if (player.getPosition().getTipoCasilla().equals(TipoCasilla.QUESITO)) {
                player.addQuesito(player.getPosition().getCategoria());
            }

            // Reseteamos los valores
            resetTurn();

        } else {
            // Lanzar excepcion?
        }
    }

//    private void guardaEstadisticas(final boolean resultado, final String user,
//            final Pregunta question) {
//        if (saveStats) {
//            try {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Almacenar en base de datos estadisticas pregunta
////                        EstadisticasJugadorDao ejDao = persistencia.createEstadisticasJugadorDao();
////                        JugadorDao jugadorDao = persistencia.createJugadorDao();
////                        PreguntaDao preguntaDao = persistencia.createPreguntaDao();
////                        String id = question.getId();
//
//                        Map<String, Object> pregunta = preguntaDao.findById(id);
//                        if (pregunta == null) {
//                            preguntaDao.insertar(question);
//                            pregunta = preguntaDao.findById(id);
//                            preguntaDao.guardarResultado(pregunta, resultado);
//                        } else
//                            preguntaDao.guardarResultado(pregunta, resultado);
//
//                        // Almacenar estadisticas jugador
//                        int idJugador = jugadorDao.getIdByLogin(user);
//                        pregunta = ejDao.findByJyP(idJugador, id);
//                        if (pregunta == null) {
//                            ejDao.insertar(idJugador, id);
//                            pregunta = ejDao.findByJyP(idJugador, id);
//                            ejDao.guardarResultado(pregunta, resultado);
//                        } else
//                            ejDao.guardarResultado(pregunta, resultado);
//                    }
//                }).start();
//            } catch (Exception e) {
//                saveStats = false;
//                JOptionPane.showMessageDialog(null,
//                        "Error al guardar las estadisticas, "
//                                + "se han desabilitado durante esta partida");
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void respuestaIncorrecta() {
        if (getCurrentTurnPlayer() != null && diceThrown
                && questionGiven != null) {

            Player player = getCurrentTurnPlayer();

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

    @Override
    public Casilla getCasilla(int i) {
        return tablero.getNode(i - 1);
    }

	@Override
	public boolean userExists(String user, String pass) {
		// TODO Auto-generated method stub
		//return persistencia.createJugadorDao().existeUsuario(user, pass, "usuario");
		return false;
		
	}

	@Override
	public boolean registerUser(String user, String pass) {
		// TODO Auto-generated method stub
		if(!userExists(user,pass)){
		//persistencia.createJugadorDao().insertarJugadores(user,pass);
		return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin(String user, String pass) {
		// TODO Auto-generated method stub
		//return persistencia.createJugadorDao().existeUsuario(user, pass, "administrador");
		return false;

	}
}
