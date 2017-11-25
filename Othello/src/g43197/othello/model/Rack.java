package g43197.othello.model;

import static g43197.othello.model.Game.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the rack of pieces. They are initially as much pieces
 * as tiles on the board. The pieces are all initialised with color black. The
 * rack also counts the number of walls.
 *
 * @author Philippe
 */
public class Rack {

    private final List<Piece> pieces;
    private int nbWalls;

    /**
     * Creates a new rack with as much pieces as tiles on the board, but removes
     * the 4 center pieces from the board.
     */
    public Rack() {
        pieces = new ArrayList<>();
        for (int i = 0; i < MAX_ROWS_COLS * MAX_ROWS_COLS - 4; i++) {
            pieces.add(new Piece(Color.BLACK));
        }
        nbWalls = 0;
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
        if (color == Color.WHITE) {
            piece.switchColor();
        }
        return piece;
    }

    /**
     * Returns the number of walls on the board.
     *
     * @return
     */
    public int getNbWalls() {
        return nbWalls;
    }

    /**
     * Adds a wall to the counter of walls on the board.
     */
    public void addWall() {
        nbWalls++;
    }
}
