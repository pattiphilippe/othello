package g43197.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the rack of pieces. They are initially as much pieces
 * as tiles on the board. The pieces are all initialised with color black. The
 * rack also counts the number of walls.
 *
 * @author Philippe
 */
class Rack {

    private final List<Piece> pieces;
    private int nbWalls;

    /**
     * Creates a new rack with as much pieces as tiles on the board, but removes
     * the 4 center pieces from the board.
     */
    Rack(int maxRowsCols) {
        pieces = new ArrayList<>();
        for (int i = 0; i < maxRowsCols * maxRowsCols - 4; i++) {
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
    Piece getPiece(Color color) {
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
    int getNbWalls() {
        return nbWalls;
    }

    /**
     * Adds a wall to the counter of walls on the board.
     */
    void addWall() {
        nbWalls++;
    }
}
