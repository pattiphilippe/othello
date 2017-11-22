package g43197.othello.view;

import g43197.othello.model.Board;
import static g43197.othello.model.Board.*;
import g43197.othello.model.Color;
import g43197.othello.model.Coordinates;
import g43197.othello.model.Facade;
import g43197.othello.model.Piece;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * This class displays the plate of the game.
 *
 * @author Philippe
 */
public class BoardView extends GridPane {

    private final WallsCptView wallsCpt;
    private final Facade game;

    /**
     * Creates a new Board.
     *
     * @param width width of the new board
     * @param height height of the new board
     * @param wallsCpt the walls counter of the board
     * @param game
     */
    public BoardView(double width, double height, WallsCptView wallsCpt, Facade game) {
        super();
        this.game = game;

        TileView tile;
        height /= MAX_ROWS_COLS;
        width /= MAX_ROWS_COLS;

        class ClickedTileHandler implements EventHandler<MouseEvent> {

            private final int row;
            private final int col;

            public ClickedTileHandler(int row, int col) {
                this.row = row;
                this.col = col;
            }

            @Override
            public void handle(MouseEvent event) {
                System.out.println("handle click tile");
                if (event.isPrimaryButtonDown()) {
                    game.putPiece(new Coordinates(row, col));
                } else if (event.isSecondaryButtonDown()) {
                    game.putWall(new Coordinates(row, col));
                }
            }

        }

        Board board = game.getBoard();
        Coordinates pos;

        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                tile = new TileView(width, height);
                tile.setMinSize(width, height);
                tile.setOnMouseClicked(new ClickedTileHandler(row, col));
                pos = new Coordinates(row, col);
                this.add(tile, col, row);

                if (board.getPiece(pos) != null) {
                    addPiece(row, col, board.getPiece(pos).getColor());
                }
            }
        }
        this.wallsCpt = wallsCpt;
    }

    /**
     * Returns the tile at the given row and col on the board.
     *
     * @param row
     * @param col
     * @return
     */
    public TileView getTileByRowCol(int row, int col) {
        TileView tile = null;
        for (Node node : getChildren()) {
            if (getRowIndex(node) == row && getColumnIndex(node) == col) {
                tile = (TileView) node;
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

    public void update(List<Coordinates> switchedPos, Color currentPlayer) {
        //TODO mettre à jour en fonction de board, pas switchedPos?
        int row, col;
        if (switchedPos.size() > 0) {
            row = switchedPos.get(0).getROW();
            col = switchedPos.get(0).getCOL();
            addPiece(row, col, currentPlayer);
            switchedPos.stream().skip(1).forEach(pos
                    -> getTileByRowCol(pos.getROW(), pos.getCOL()).switchColor()
            );
        }
    }
}
