package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.Color;
import g43197.othello.model.util.GameException;
import java.util.ArrayList;
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
        Board board = new Board(8);
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
        Board board = new Board(8);
        board.put(piece, pos);
        assertEquals(piece, board.getPiece(pos));
        pos = new Coordinates(3, 3);
        assertEquals(piece, board.getPiece(pos));
    }

    /**
     * Test of normal case.
     */
    @Test
    public void testPutWall() {
        Board board = new Board(8);

        Coordinates pos = new Coordinates(2, 3);
        board.putWall(pos);
        assertEquals(Color.WALL, board.getPiece(pos).getColor());

        pos = new Coordinates(0, 0);
        board.putWall(pos);
        assertEquals(Color.WALL, board.getPiece(pos).getColor());

        pos = new Coordinates(7, 7);
        board.putWall(pos);
        assertEquals(Color.WALL, board.getPiece(pos).getColor());
    }

    /**
     * Test putting wall on piece.
     */
    @Test(expected = GameException.class)
    public void testPutWallNotNull() {
        Board board = new Board(8);
        Coordinates pos = new Coordinates(3, 3);
        board.putWall(pos);
    }

    /**
     * Test if gets exception when piece is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullPiece() {
        Board board = new Board(8);
        board.put(null, null);
    }

    /**
     * Test if gets exception when pos is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullPos() {
        Board board = new Board(8);
        board.put(new Piece(Color.BLACK), null);
    }

    /**
     * Test if gets exception when pos is outside.
     */
    @Test(expected = GameException.class)
    public void testPutPosOutside() {
        Board board = new Board(8);
        board.put(new Piece(Color.BLACK), new Coordinates(-1, 0));
    }

    /**
     * Test if gets exception when pos is outside.
     */
    @Test(expected = GameException.class)
    public void testPutWallPosOutside() {
        Board board = new Board(8);
        board.putWall(new Coordinates(-1, 0));
    }

    /**
     * Test of multiple puts and test of switch.
     */
    @Test
    public void testPutMultiplesSwitchColors() {
        Board board = new Board(8);
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
        Board board = new Board(8);
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
        Board board = new Board(8);
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
     * Initial case.
     */
    @Test
    public void testUpdateAccessiblesWall() {
        Board board = new Board(8);
        board.putWall(new Coordinates(2, 3));
        List<Coordinates> accessibles = new LinkedList<>();
        board.updateAccessibles(accessibles, Color.BLACK);
        List<Coordinates> expResult = new LinkedList<>();
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
        Board board = new Board(8);
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
        Board board = new Board(8);
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
     * Test if the list of switched positions if well updated and initialized.
     */
    @Test
    public void testSwitchedPos() {
        Board board = new Board(8);
        List<Coordinates> list = board.getSwitchedPositions(), expResult = new ArrayList<>(10);
        expResult.add(new Coordinates(3, 3));
        expResult.add(new Coordinates(3, 4));
        expResult.add(new Coordinates(4, 3));
        expResult.add(new Coordinates(4, 4));
        assertTrue(list.containsAll(expResult));

        board.put(new Piece(Color.BLACK), new Coordinates(2, 3));

        list = board.getSwitchedPositions();
        expResult.clear();
        expResult.add(new Coordinates(2, 3));
        expResult.add(new Coordinates(3, 3));
        assertTrue(list.containsAll(expResult));
    }
}
