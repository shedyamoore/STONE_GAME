package stones.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The main model for game
 * A game board holds two players and an array of positions where stones are still present
 */
public class GameBoard {
    private Player player1;
    private Player player2;
    private boolean array[][];
    private boolean turn = true;
    private List<Integer> turns;
    private int totalTurns = 0;
    private boolean hasWon = false;
    private static Logger logger = LogManager.getLogger(GameBoard.class);
    /**
     * constructs a new game board
     * @param player1 first player
     * @param player2 second player
     *
     */
    public GameBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        turns = new ArrayList<>();
        array = new boolean[4][4];
        reset();
    }

    /**
     * makes move and if 4 moves are done then turn is automatically shifted to other player
     * This also checks if the turn is valid and is adjacent to last few turns by same player
     * @param i i position
     * @param j j position
     *
     */
    public void makeTurn(int i, int j) {
        boolean valid = false;

        if (!turns.isEmpty()) {
            for (int old : turns) {
              int oi = old / 4;
              int oj = old % 4;

              if (oi!=0){
                  if (oi - 1 ==i && oj == j)
                      valid = true;
              }
              if (oi!=3){
                  if (oi + 1 ==i && oj == j)
                      valid = true;
              }

                if (oj!=0){
                    if (oi == i && oj-1 == j)
                        valid = true;
                }
                if (oj!=3){
                    if (oi ==i && oj+1 == j)
                        valid = true;
                }
            }

            if (!valid)
                return;
        }
        if (turn) {
            logger.info("Player1 turn");
            player1.move();
        }
        else{
            logger.info("Player2 turn");

            player2.move();
        }
        array[i][j] = false;
        checkWinner();

        totalTurns++;
        turns.add((i * 4) + j);
        if (turns.size() % 4 == 0 && !hasWon)
            skipTurn();
    }

    /**
     * checking if win state has been achieved this is a set of codes to check if the winner has been achieved
     */
    private void checkWinner() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] != false)
                    return;
            }
        }

        hasWon = true;
    }

    /**
     * return total turns made so far
     * @return int
     */
    public int getTotalTurns() {
        return totalTurns;
    }

    /**
     * resets the game board
     */
    public void reset() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = true;
            }
        }
        totalTurns = 0;
        turns.clear();
        player1.reset();
        player2.reset();
        hasWon = false;
        logger.info("Game reset");

    }

    /**
     * Changes the turn if and only if the player has made at least one turn this round
     */
    public void skipTurn() {
        if (turns.size() != 0) {
            turns.clear();
            turn = !turn;
        }
    }


    /**
     * method to return first player
     * @return Player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Method to return the second player
     * @return Player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * 2D array representation of stones
     * @return Boolean
     */
    public boolean[][] getArray() {
        return array;
    }

    /**
     * method to check if is player1 or player2 turn
     * @return this returns the current players turn
     */
    public boolean isPlayer1Turn() {
        return turn;
    }

    /**
     * if there is a winner it returns the winner
     * else returns null
     * @return
     *
     */
    public Player getWinner() {
        if (hasWon)
            if (turn)
                return player1;
            else return player2;
        else
            return null;
    }

    /**
     * method to set turn and has won property
     * @param winner
     *
     */
    public void setWinner(String winner) {
        if (player1.getName().equalsIgnoreCase(winner)) {
            turn = true;
            hasWon = true;
        } else {
            turn = false;
            hasWon = true;
        }
        logger.info("Has won "+hasWon);
    }
}
