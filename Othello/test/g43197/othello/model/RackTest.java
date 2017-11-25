package g43197.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class RackTest {

    /**
     * Check if good amount of pieces.
     */
    @Test
    public void testRack() {
        Rack rack = new Rack();
        for (int i = 0; i < 60; i++) {
            rack.getPiece(Color.BLACK);
        }
    }

    /**
     * Get piece when no more pieces.
     */
    @Test(expected = GameException.class)
    public void testRackError() {
        Rack rack = new Rack();
        for (int i = 0; i < 61; i++) {
            rack.getPiece(Color.BLACK);
        }
    }

    /**
     * Tries to get a White piece.
     */
    @Test
    public void testGetPieceBlack() {
        Color color = Color.BLACK;
        Rack rack = new Rack();
        Piece piece = rack.getPiece(color);
        assertEquals(color, piece.getColor());
    }

    /**
     * Tries to get a White piece.
     */
    @Test
    public void testGetPieceWhite() {
        Color color = Color.WHITE;
        Rack rack = new Rack();
        Piece piece = rack.getPiece(color);
        assertEquals(color, piece.getColor());
    }

    /**
     * Tries to get multiple Black pieces.
     */
    @Test
    public void testGetPieceMultipleB() {
        Color color = Color.BLACK;
        Rack rack = new Rack();
        Piece piece;
        for (int i = 0; i < 10; i++) {
            piece = rack.getPiece(color);
            assertEquals(color, piece.getColor());
        }
    }

    /**
     * Tries to get multiple White pieces.
     */
    @Test
    public void testGetPieceMultipleW() {
        Color color = Color.WHITE;
        Rack rack = new Rack();
        Piece piece;
        for (int i = 0; i < 10; i++) {
            piece = rack.getPiece(color);
            assertEquals(color, piece.getColor());
        }
    }

    /**
     * Checks if gets the error when no pieces left.
     */
    @Test(expected = GameException.class)
    public void testGetPieceErrorNoPieces() {
        Color color = Color.BLACK;
        Rack rack = new Rack();
        Piece piece;
        int maxPieces = (int) Math.pow(Game.MAX_ROWS_COLS, 2);
        for (int i = 0; i < maxPieces + 2; i++) {
            piece = rack.getPiece(color);
            assertEquals(color, piece.getColor());
        }
    }

}
