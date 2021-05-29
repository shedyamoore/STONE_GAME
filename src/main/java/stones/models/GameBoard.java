package stones.models;

import java.util.ArrayList;
import java.util.List;

/**
 * The main model for game
 * A gameboard holds two players and an array of positions where stones are still present
 */
public class GameBoard {
    private Player player1;
    private Player player2;
    private boolean array[][];
    private boolean turn = true;
    private List<Integer> turns;
    private int totalTurns = 0;
    private boolean hasWon = false;

    /**
     * @param player1 first player
     * @param player2 second player
     * constructs a new game board
     */
    public GameBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        turns = new ArrayList<>();
        array = new boolean[4][4];
        reset();
    }

    /**
     * @param i i position
     * @param j j position
     * makes move and if 4 moves are done then turn is automatically shifted to other player
     * This checks if the turn is valid and is adjacent to last few turns by same player
     *
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
        if (turn)
            player1.move();
        else
            player2.move();
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
     * @return total turns made so far
     */
    public int getTotalTurns() {
        return totalTurns;
    }

    /**
     * resets the gameboard
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


    }

    /**
     * Changes the turn if and only if the player has made atleast one turn this round
     */
    public void skipTurn() {
        if (turns.size() != 0) {
            turns.clear();
            turn = !turn;
        }
    }


    /**
     * @return first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @return second player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @return 2D array representation of stones
     */
    public boolean[][] getArray() {
        return array;
    }

    /**
     * @return this returns the current players turn
     */
    public boolean isPlayer1Turn() {
        return turn;
    }

    /**
     * @return if there is a winner it returns the winner
     * else returns null
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
     * @param winner set winner to help by
     *  reading the file
     */
    public void setWinner(String winner) {
        if (player1.getName().equalsIgnoreCase(winner)) {
            turn = true;
            hasWon = true;
        } else {
            turn = false;
            hasWon = true;
        }
    }
}
