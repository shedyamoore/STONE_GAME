package stones.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void constructorTest() {
    GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
    assertEquals("player1",gameBoard.getPlayer1().getName());
    assertEquals(0,gameBoard.getPlayer1().getMoves());
    assertEquals(0,gameBoard.getTotalTurns());
    }


    @Test
    void makeTurnTest() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.makeTurn(0,0);
        assertFalse(gameBoard.getArray()[0][0]);
    }


    @Test
    void resetTest() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.makeTurn(0,0);
        gameBoard.reset();
        assertTrue(gameBoard.getArray()[0][0]);
    }

    @Test
    void skipTurn() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.makeTurn(0,0);
        gameBoard.skipTurn();
        assertFalse(gameBoard.isPlayer1Turn());
    }

    @Test
    void getPlayer1() {
        GameBoard gameBoard = new GameBoard(new Player("p1"),new Player("p2"));
        assertEquals("p1",gameBoard.getPlayer1().getName());
        assertEquals(0,gameBoard.getPlayer1().getMoves());
    }

    @Test
    void getPlayer2() {
        GameBoard gameBoard = new GameBoard(new Player("p1"),new Player("p2"));
        assertEquals("p2",gameBoard.getPlayer2().getName());
        assertEquals(0,gameBoard.getPlayer2().getMoves());
    }

    @Test
    void getArrayStartingTest() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertTrue(gameBoard.getArray()[i][j]);
            }
        }
    }
    @Test
    void getArrayAfterResetTest() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.makeTurn(0,0);
        gameBoard.makeTurn(0,1);
        gameBoard.makeTurn(0,2);
        gameBoard.reset();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertTrue(gameBoard.getArray()[i][j]);
            }
        }
    }

    @Test
    void isPlayer1TurnTest() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.makeTurn(0,0);
        gameBoard.makeTurn(0,1);
        gameBoard.makeTurn(0,2);
        gameBoard.makeTurn(0,3);
        assertFalse(gameBoard.isPlayer1Turn());
    }

    @Test
    void getWinnerFailTest() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.makeTurn(0,0);
        gameBoard.makeTurn(0,1);
        gameBoard.makeTurn(0,2);
        gameBoard.makeTurn(0,3);
        assertFalse(gameBoard.getWinner()!=null);

    }

    @Test
    void setWinner() {
        GameBoard gameBoard = new GameBoard(new Player("player1"),new Player("player2"));
        gameBoard.setWinner("player1");
        assertEquals("player1",gameBoard.getPlayer1().getName());
    }
}