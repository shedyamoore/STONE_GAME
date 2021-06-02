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
     * returns the game board instance this is a game method to return the instance
     * @return GameBoard method
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * this method is called to update the board
     * @param gameBoard method to updates the game board
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * this method is called to return the instance of the class
     * @return GameSession method to return instance of this class
     */
    //It means instance variables belong to an object and
    // we know that an object is an instance of a class
    public static GameSession getInstance() {
        if (instance == null)
            instance = new GameSession();
        logger.info("Game Session is " +instance);
        return instance;
    }
}
