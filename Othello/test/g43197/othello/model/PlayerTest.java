package g43197.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class PlayerTest {

    /**
     * Test of constructor, normal case.
     */
    @Test
    public void newPlayer() {
        Player p = new Player(Color.BLACK);
        assertEquals(p.getColor(), Color.BLACK);
        assertEquals(p.getScore(), 0);
    }

    /**
     * Checks the exception if null in arg of Player constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void newPlayerNullColor() {
        new Player(null);
    }

    /**
     * Test when first incrementation of score.
     */
    @Test
    public void testModifyScoreFirst() {
        Player player = new Player(Color.WHITE);
        int delta = 2;
        player.modifyScore(delta);
        assertEquals(delta, player.getScore());
    }

    /**
     * Test when two incrementations of score.
     */
    @Test
    public void testModifyScoreSeveral() {
        Player player = new Player(Color.WHITE);
        int delta = 15;
        player.modifyScore(delta);
        delta = 10;
        player.modifyScore(delta);
        assertEquals(25, player.getScore());
    }

    /**
     * Test incrementation of score with negatives.
     */
    @Test
    public void testModifyScoreNegative() {
        Player player = new Player(Color.WHITE);
        int delta = 15;
        player.modifyScore(delta);
        delta = -5;
        player.modifyScore(delta);
        assertEquals(10, player.getScore());
    }

    /**
     * Checks if throws an exception when the score is negative.
     */
    @Test(expected = GameException.class)
    public void testModifyScoreIsNegative() {
        Player player = new Player(Color.WHITE);
        player.modifyScore(-5);
    }

}
