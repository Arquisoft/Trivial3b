package es.uniovi.asw.controller.game;

import es.uniovi.asw.controller.game.impl.GameServiceImpl;

public class GameFactory {

    public static final GameService newGameService() {
        return new GameServiceImpl();
    }

    public static final GameService newGameService(int var) {
        return new GameServiceImpl(var);
    }

}
