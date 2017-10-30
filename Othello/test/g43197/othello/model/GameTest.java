package g43197.othello.model;

import java.util.LinkedList;
import java.util.List;
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
     * Test when can't play. Also tests method isFinished().
     */
    @Test
    public void testCanPlayFullBoard() {
        Game game = new Game();
        List<Coordinates> poss = new LinkedList<>();
        poss.add(new Coordinates(2, 3));
        poss.add(new Coordinates(2, 2));
        poss.add(new Coordinates(2, 1));
        poss.add(new Coordinates(5, 3));
        poss.add(new Coordinates(6, 3));
        poss.add(new Coordinates(2, 4));
        poss.add(new Coordinates(3, 5));
        poss.add(new Coordinates(2, 0));
        poss.add(new Coordinates(1, 3));
        poss.add(new Coordinates(5, 5));
        poss.add(new Coordinates(5, 4));
        poss.add(new Coordinates(2, 5));
        poss.add(new Coordinates(1, 4));
        poss.add(new Coordinates(0, 4));
        poss.add(new Coordinates(1, 2));
        poss.add(new Coordinates(0, 3));
        poss.add(new Coordinates(0, 2));
        poss.add(new Coordinates(6, 4));
        poss.add(new Coordinates(5, 6));
        poss.add(new Coordinates(7, 3));
        poss.add(new Coordinates(3, 2));
        poss.add(new Coordinates(5, 7));
        poss.add(new Coordinates(7, 4));
        poss.add(new Coordinates(6, 5));
        poss.add(new Coordinates(4, 2));
        poss.add(new Coordinates(7, 5));
        poss.add(new Coordinates(0, 5));
        poss.add(new Coordinates(5, 1));
        poss.add(new Coordinates(5, 2));
        poss.add(new Coordinates(3, 1));
        poss.add(new Coordinates(1, 5));
        poss.add(new Coordinates(4, 5));
        poss.add(new Coordinates(6, 0));
        poss.add(new Coordinates(3, 6));
        poss.add(new Coordinates(4, 6));
        poss.add(new Coordinates(2, 6));
        poss.add(new Coordinates(4, 7));
        poss.add(new Coordinates(3, 7));
        poss.add(new Coordinates(2, 7));
        poss.add(new Coordinates(1, 7));
        poss.add(new Coordinates(1, 6));
        poss.add(new Coordinates(4, 1));
        poss.add(new Coordinates(6, 2));
        poss.add(new Coordinates(0, 7));
        poss.add(new Coordinates(7, 6));
        poss.add(new Coordinates(7, 7));
        poss.add(new Coordinates(6, 7));
        poss.add(new Coordinates(6, 6));
        poss.add(new Coordinates(0, 6));
        poss.add(new Coordinates(0, 1));
        poss.add(new Coordinates(4, 0));
        poss.add(new Coordinates(3, 0));
        poss.add(new Coordinates(5, 0));
        poss.add(new Coordinates(7, 0));
        poss.add(new Coordinates(1, 0));
        poss.add(new Coordinates(0, 0));
        poss.add(new Coordinates(7, 2));
        poss.add(new Coordinates(7, 1));
        poss.add(new Coordinates(1, 1));
        for (Coordinates pos : poss) {
            game.put(pos);
        }
        game.put(new Coordinates(6, 1));
        assertEquals(false, game.canPlay());
        assertEquals(true, game.isFinished());
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
}
