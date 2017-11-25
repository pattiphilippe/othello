package g43197.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class PieceTest {

    /**
     * Checks normal case of constructor.
     */
    @Test
    public void newPiece() {
        Piece p = new Piece(Color.BLACK);
        assertEquals(Color.BLACK, p.getColor());
    }

    /**
     * Test of switchColor method, of class Piece, Black case.
     */
    @Test
    public void testSwitchColorBlack() {
        Piece p = new Piece(Color.BLACK);
        p.switchColor();
        assertEquals(p.getColor(), Color.WHITE);
    }

    /**
     * Test of switchColor method, of class Piece, White case.
     */
    @Test
    public void testSwitchColorWhite() {
        Piece p = new Piece(Color.WHITE);
        p.switchColor();
        assertEquals(p.getColor(), Color.BLACK);
    }

}
