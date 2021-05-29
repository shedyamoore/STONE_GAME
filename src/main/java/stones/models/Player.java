package stones.models;

/**
 * Player model
 */
public class Player {
    private String name;
    private int moves;

    /**
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
        moves = 0;

    }

    public Player() {
    }

    public Player(String name, int moves) {
        this.name = name;
        this.moves = moves;
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
     * @return name of the player
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
     * @return moves made by the player
     */
    public int getMoves() {
        return moves;
    }

    /**
     * @param moves updates moves
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }
}
