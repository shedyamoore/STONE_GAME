package stones.models;

/**
 * singleton class to hold current gamesession
 */
public class GameSession {
    private static GameSession instance;
    private GameBoard gameBoard;

    /**
     * private constructor for singleton design pattern
     */
    private GameSession() {

    }

    /**
     * @return returns the gameboard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * @param gameBoard updates the gameboard
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * @return instance of this class
     */
    public static GameSession getInstance() {
        if (instance == null)
            instance = new GameSession();
        return instance;
    }
}
