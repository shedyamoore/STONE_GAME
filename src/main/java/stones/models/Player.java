package stones.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Player model
 */
public class Player {
    private String name;
    private int moves;
    private static Logger logger = LogManager.getLogger(FileManager.class);
    /**
     * Player constructor
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
        moves = 0;
        logger.info("Name:"+name);
    }
    /**
     * Player constructor
     */
    public Player() {
    }

    /**
     * Player constructor
     * @param name name of the player
     * @param moves name of the player
     */
    public Player(String name, int moves) {
        this.name = name;
        this.moves = moves;
        logger.info("Name:"+name+",Moves is "+moves);
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * increments the moves by 1
     */
    public void move() {
        moves++;
    }

    /**
     * name of the player
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * resets the moves
     */
    public void reset() {
        moves = 0;
    }

    /**
     * method to return moves made by the player
     * @return int
     */
    public int getMoves() {
        return moves;
    }

    /**
     * method to update player moves
     * @param moves updates moves
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }
}
