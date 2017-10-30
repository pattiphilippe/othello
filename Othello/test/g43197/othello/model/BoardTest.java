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
     * Test of put method, of class Board and getPiece.
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
    
    public void testUpdateToCheck(){
        Board board = new Board();
    }
}
