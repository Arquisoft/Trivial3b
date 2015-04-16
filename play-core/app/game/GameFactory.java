package game;



public class GameFactory {

    public static final GameService newGameService() {
        return new GameServiceImpl();
    }

    public static final GameService newGameService(int var) {
        return new GameServiceImpl(var);
    }

}
