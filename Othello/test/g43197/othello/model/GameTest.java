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
        assertEquals(true , game.canPlay());
    }

    /**
     * Test when can't play.
     */
    @Test
    public void testCanPlayFullBoard() {
        Game game = new Game();
    }

    /**
     * Test of initial score
     */
    @Test
    public void testGetScore() {
        Game game = new Game();
        assertEquals(game.getScore(), 2);
        game.put(new Coordinates(2, 3));
        assertEquals(game.getScore(), 1);

    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
