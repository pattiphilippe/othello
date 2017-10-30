package g43197.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class GameTest {

    /**
     * Test of normal case
     */
    @Test
    public void testCanPlay() {
        Game game = new Game();
        assertEquals(true, game.canPlay());
    }

    /**
     * Test when can't play.
     */
    @Test
    public void testCanPlayFullBoard() {
        Game game = new Game();
        fail("not implemented");
    }

    /**
     * Test of initial score.
     */
    @Test
    public void testGetScore() {
        Game game = new Game();
        assertEquals(game.getScore(), 2);
        game.put(new Coordinates(2, 3));
        assertEquals(game.getScore(), 1);
    }

    /**
     * Test of score after a few moves.
     */
    @Test
    public void testGetScoreAfterMoves() {
        Game game = new Game();
        assertEquals(game.getScore(), 2);
        game.put(new Coordinates(2, 3));
        assertEquals(game.getScore(), 1);
        game.put(new Coordinates(2, 2));
        assertEquals(game.getScore(), 3);
        game.put(new Coordinates(2, 1));
        assertEquals(game.getScore(), 2);
        game.put(new Coordinates(5, 3));
        assertEquals(game.getScore(), 4);
        game.put(new Coordinates(6, 3));
        assertEquals(game.getScore(), 1);
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        Game game = new Game();
        try {
            Player player = game.getCurrentPlayer();
            assertEquals(player.getScore(), game.getScore());
        } catch (CloneNotSupportedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of put method, of class Game.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Coordinates pos = null;
        Game instance = new Game();
        instance.put(pos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFinished method, of class Game.
     */
    @Test
    public void testIsFinished() {
        System.out.println("isFinished");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
