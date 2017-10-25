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
        Piece piece = new Piece(Color.BLACK);
        Coordinates pos = new Coordinates(2, 3);
        Board board = new Board();
        board.put(piece, pos);
        assertEquals(piece, board.getPiece(pos));
        pos = new Coordinates(3, 3);
        assertEquals(piece, board.getPiece(pos));
    }

}
