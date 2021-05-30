package stones.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * singleton class to hold current game session
 */
public class GameSession {
    private static GameSession instance;
    private GameBoard gameBoard;
    private static Logger logger = LogManager.getLogger(GameSession.class);
    /**
     * private constructor for singleton design pattern
     */
    private GameSession() {

    }

    /**
     * method returns the game board instance
     * @return GameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * method to updates the game board
     * @param gameBoard
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * method to return instance of this class
     * @return GameSession
     */
    public static GameSession getInstance() {
        if (instance == null)
            instance = new GameSession();
        logger.info("Game Session is " +instance);
        return instance;
    }
}
