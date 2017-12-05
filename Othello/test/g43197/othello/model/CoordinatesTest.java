package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.Direction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class CoordinatesTest {

    /**
     * Test in up direction.
     */
    @Test
    public void testIncrementUp() {
        Coordinates origin = new Coordinates(4, 4);
        Coordinates expResult = new Coordinates(3, 4);
        Coordinates result = origin.increment(Direction.UP);
        assertEquals(expResult, result);
    }

    /**
     * Test in up right direction.
     */
    @Test
    public void testIncrementUpRight() {
        Coordinates origin = new Coordinates(4, 4);
        Coordinates expResult = new Coordinates(3, 5);
        Coordinates result = origin.increment(Direction.UP_RIGHT);
        assertEquals(expResult, result);
    }

    /**
     * Test in right direction.
     */
    @Test
    public void testIncrementRight() {
        Coordinates origin = new Coordinates(4, 4);
        Coordinates expResult = new Coordinates(4, 5);
        Coordinates result = origin.increment(Direction.RIGHT);
        assertEquals(expResult, result);
    }

    /**
     * Test in down right direction.
     */
    @Test
    public void testIncrementDownRight() {
        Coordinates origin = new Coordinates(4, 4);
        Coordinates expResult = new Coordinates(5, 5);
        Coordinates result = origin.increment(Direction.DOWN_RIGHT);
        assertEquals(expResult, result);
    }

    /**
     * Test in down direction.
     */
    @Test
    public void testIncrementDown() {
        Coordinates origin = new Coordinates(7, 0);
        Coordinates expResult = new Coordinates(8, 0);
        Coordinates result = origin.increment(Direction.DOWN);
        assertEquals(expResult, result);
    }

    /**
     * Test in down left direction.
     */
    @Test
    public void testIncrementDownLeft() {
        Coordinates origin = new Coordinates(7, 0);
        Coordinates expResult = new Coordinates(8, -1);
        Coordinates result = origin.increment(Direction.DOWN_LEFT);
        assertEquals(expResult, result);
    }

    /**
     * Test in left direction.
     */
    @Test
    public void testIncrementLeft() {
        Coordinates origin = new Coordinates(7, 0);
        Coordinates expResult = new Coordinates(7, -1);
        Coordinates result = origin.increment(Direction.LEFT);
        assertEquals(expResult, result);
    }

    /**
     * Test in up left direction.
     */
    @Test
    public void testIncrementUpLeft() {
        Coordinates origin = new Coordinates(7, 0);
        Coordinates expResult = new Coordinates(6, -1);
        Coordinates result = origin.increment(Direction.UP_LEFT);
        assertEquals(expResult, result);
    }
}
