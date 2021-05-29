package stones.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void getNameTest() {
        Player player = new Player("Shedy");
        assertEquals("Shedy",player.getName());
    }

    @Test
    void makeMoveTest() {

        Player player = new Player("Shedy");
        player.move();
        assertEquals(1,player.getMoves());
    }

    @Test
    void setMovesTest() {
        Player player = new Player("Shedy");
        player.setMoves(25);
        assertEquals(25,player.getMoves());
    }


    @Test
    void getMovesTest() {
        Player player = new Player("Shedy");
        player.move();
        player.move();
        assertEquals(2,player.getMoves());
    }

    @Test
    void resetTest() {
        Player player = new Player("Shedy");
        player.move();
        player.move();
        player.reset();
        assertEquals(0,player.getMoves());
    }
}