package g43197.othello.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G43197
 */
public class BoardTest {

    /**
     * Tests the construction of a normal board. InitBoard(), getCenterPos()
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
     * Test of put method, of class Board and getPiece. With consequencePut()
     * and getDirToSwitch()
     */
    @Test
    public void testPutGetPiece() {
        Piece piece = new Piece(Color.BLACK);
        Coordinates pos = new Coordinates(2, 3);
        Board board = new Board();
        board.put(piece, pos);
        assertEquals(piece, board.getPiece(pos));
        pos = new Coordinates(3, 3);
        assertEquals(piece, board.getPiece(pos));
    }

    /**
     * Test if gets exception when piece is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullPiece() {
        Board board = new Board();
        board.put(null, null);
    }

    /**
     * Test if gets exception when pos is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullPos() {
        Board board = new Board();
        board.put(new Piece(Color.BLACK), null);
    }

    /**
     * Test if gets exception when pos is outside.
     */
    @Test(expected = GameException.class)
    public void testPutPosOutside() {
        Board board = new Board();
        board.put(new Piece(Color.BLACK), new Coordinates(-1, 0));
    }

    /**
     * Test of multiple puts and test of switch.
     */
    @Test
    public void testPutMultiplesSwitchColors() {
        Board board = new Board();
        List<Coordinates> poss = new LinkedList<>();
        poss.add(new Coordinates(2, 3));
        poss.add(new Coordinates(2, 2));
        poss.add(new Coordinates(2, 1));
        poss.add(new Coordinates(5, 3));
        int nbPut = 0;
        Color color;
        Piece piece;
        for (Coordinates pos : poss) {
            if (nbPut % 2 == 0) {
                color = Color.BLACK;
            } else {
                color = Color.WHITE;
            }
            piece = new Piece(color);
            board.put(piece, pos);
            assertEquals(piece, board.getPiece(pos));
            nbPut++;
        }
        Coordinates pos = new Coordinates(2, 2);
        assertEquals(Color.BLACK, board.getPiece(pos).getColor());
        pos = new Coordinates(4, 3);
        assertEquals(Color.WHITE, board.getPiece(pos).getColor());
    }

    /**
     * Checks if switches multiple pieces in a row.
     */
    @Test
    public void testSwitchMultiples() {
        Board board = new Board();
        List<Coordinates> poss = new LinkedList<>();
        poss.add(new Coordinates(2, 3));
        poss.add(new Coordinates(2, 2));
        poss.add(new Coordinates(2, 1));
        poss.add(new Coordinates(5, 3));
        poss.add(new Coordinates(6, 3));
        int nbPut = 0;
        Color color;
        Piece piece;
        for (Coordinates pos : poss) {
            if (nbPut % 2 == 0) {
                color = Color.BLACK;
            } else {
                color = Color.WHITE;
            }
            piece = new Piece(color);
            board.put(piece, pos);
            assertEquals(piece, board.getPiece(pos));
            nbPut++;
        }
        Coordinates pos = new Coordinates(2, 3);
        assertEquals(Color.BLACK, board.getPiece(pos).getColor());
        pos = new Coordinates(3, 3);
        assertEquals(Color.BLACK, board.getPiece(pos).getColor());
        pos = new Coordinates(4, 3);
        assertEquals(Color.BLACK, board.getPiece(pos).getColor());
    }

    /**
     * Initial case.
     */
    @Test
    public void testUpdateAccessibles() {
        Board board = new Board();
        List<Coordinates> accessibles = new LinkedList<>();
        board.updateAccessibles(accessibles, Color.BLACK);
        List<Coordinates> expResult = new LinkedList<>();
        expResult.add(new Coordinates(2, 3));
        expResult.add(new Coordinates(3, 2));
        expResult.add(new Coordinates(4, 5));
        expResult.add(new Coordinates(5, 4));
        assertTrue(accessibles.containsAll(expResult));
    }

    /**
     * Test after a move.
     */
    @Test
    public void testUpdateAccessiblesAfterMove() {
        Board board = new Board();
        board.put(new Piece(Color.BLACK), new Coordinates(2, 3));
        List<Coordinates> accessibles = new LinkedList<>();
        board.updateAccessibles(accessibles, Color.WHITE);
        List<Coordinates> expResult = new LinkedList<>();
        expResult.add(new Coordinates(2, 2));
        expResult.add(new Coordinates(2, 4));
        expResult.add(new Coordinates(4, 2));
        assertTrue(accessibles.containsAll(expResult));
    }

    /**
     * Test after a few moves.
     */
    @Test
    public void testUpdateAccessiblesAfterMoves() {
        Board board = new Board();
        board.put(new Piece(Color.BLACK), new Coordinates(2, 3));
        board.put(new Piece(Color.WHITE), new Coordinates(2, 2));
        board.put(new Piece(Color.BLACK), new Coordinates(2, 1));
        board.put(new Piece(Color.WHITE), new Coordinates(5, 3));
        board.put(new Piece(Color.BLACK), new Coordinates(6, 3));
        List<Coordinates> accessibles = new LinkedList<>();
        board.updateAccessibles(accessibles, Color.WHITE);
        List<Coordinates> expResult = new LinkedList<>();
        expResult.add(new Coordinates(1, 1));
        expResult.add(new Coordinates(2, 4));
        expResult.add(new Coordinates(4, 2));
        expResult.add(new Coordinates(6, 2));
        assertTrue(accessibles.containsAll(expResult));
    }

    /**
     *
     */
    @Test
    public void testClone() {
        try {
            Board board = new Board();
            Board boardClone = board.clone();
            assertEquals(new Piece(Color.WHITE), boardClone.getPiece(new Coordinates(3, 3)));
            assertEquals(new Piece(Color.BLACK), boardClone.getPiece(new Coordinates(3, 4)));
            assertEquals(new Piece(Color.BLACK), boardClone.getPiece(new Coordinates(4, 3)));
            assertEquals(new Piece(Color.WHITE), boardClone.getPiece(new Coordinates(4, 4)));
            assertNotEquals(board, boardClone);
        } catch (CloneNotSupportedException e) {
            fail("Clone failed!");
        }
    }
}
