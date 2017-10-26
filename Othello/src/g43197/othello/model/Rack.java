package g43197.othello.model;

import static g43197.othello.model.Board.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the rack of pieces. They are initially as much pieces
 * as tiles on the board. The pieces are all initialised with color black.
 *
 * @author Philippe
 */
public class Rack {

    private List<Piece> pieces;

    /**
     *
     */
    public Rack() {
        pieces = new ArrayList<>();
        for (int i = 0; i < MAX_ROWS_COLS * MAX_ROWS_COLS; i++) {
            pieces.add(new Piece(Color.BLACK));
        }
    }

    /**
     * Returns a piece of the rack and takes it off the rack at the same time.
     *
     * @param color
     * @return
     */
    public Piece getPiece(Color color) {
        if (pieces.isEmpty()) {
            throw new GameException("No pieces left!");
        }
        Piece piece = pieces.get(0);
        pieces.remove(0);
        return piece;
    }
}
