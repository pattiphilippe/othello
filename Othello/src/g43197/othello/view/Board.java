package g43197.othello.view;

import static g43197.othello.model.Board.*;
import g43197.othello.model.Color;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * This class displays the plate of the game.
 *
 * @author Philippe
 */
public class Board extends GridPane {

    private final WallsCpt wallsCpt;

    /**
     * Creates a new Board.
     *
     * @param width width of the new board
     * @param height height of the new board
     * @param wallsCpt the walls counter of the board
     */
    public Board(double width, double height, WallsCpt wallsCpt) {
        super();

        Tile tile;
        height /= MAX_ROWS_COLS;
        width /= MAX_ROWS_COLS;

        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                tile = new Tile(width, height);
                tile.setMinSize(width, height);
                this.add(tile, col, row);
            }
        }
        this.wallsCpt = wallsCpt;
        firstPieces();
    }

    private void firstPieces() {
        addPiece(3, 3, Color.WHITE);
        addPiece(3, 4, Color.BLACK);
        addPiece(4, 3, Color.BLACK);
        addPiece(4, 4, Color.WHITE);
        addPiece(0, 0, Color.WALL);
    }

    /**
     * Returns the tile at the given row and col on the board.
     *
     * @param row
     * @param col
     * @return
     */
    public Tile getTileByRowCol(int row, int col) {
        Tile tile = null;
        for (Node node : getChildren()) {
            if (getRowIndex(node) == row && getColumnIndex(node) == col) {
                tile = (Tile) node;
                break;
            }
        }
        return tile;
    }

    private void addPiece(int row, int col, Color color) {
        if (color == Color.WALL) {
            wallsCpt.addWall();
        }
        getTileByRowCol(row, col).addPiece(color);
    }
}