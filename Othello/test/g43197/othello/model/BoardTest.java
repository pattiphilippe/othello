package g43197.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G43197
 */
public class BoardTest {

    /**
     * Tests the construction of a normal board.
     */
    @Test
    public void testNewBoard() {
        Board board = new Board();
        Coordinates pos = new Coordinates(3, 3);
        assertEquals(new Piece(Color.WHITE), board.getPiece(pos));
        pos = new Coordinates(3, 4);
        assertEquals(new Piece(Color.BLACK), board.getPiece(pos));
        pos = new Coordinates(4, 3);
        assertEquals(new Piece(Color.BLACK), board.getPiece(pos));
        pos = new Coordinates(4, 4);
        assertEquals(new Piece(Color.WHITE), board.getPiece(pos));
    }

    /**
     * Test of put method, of class Board.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Piece piece = null;
        Coordinates pos = null;
        Board instance = new Board();
        instance.put(piece, pos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consequencePut method, of class Board.
     */
    @Test
    public void testConsequencePut() {
        System.out.println("consequencePut");
        Coordinates pos = null;
        Board instance = new Board();
        int expResult = 0;
        int result = instance.consequencePut(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
