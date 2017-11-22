package g43197.othello.view;

import static g43197.othello.model.Board.*;
import g43197.othello.model.Color;
import g43197.othello.model.Coordinates;
import g43197.othello.model.Facade;
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
public class Board extends GridPane {

    private final WallsCpt wallsCpt;
    private final Facade game;

    /**
     * Creates a new Board.
     *
     * @param width width of the new board
     * @param height height of the new board
     * @param wallsCpt the walls counter of the board
     * @param game
     */
    public Board(double width, double height, WallsCpt wallsCpt, Facade game) {
        super();
        this.game = game;

        Tile tile;
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

        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                tile = new Tile(width, height);
                tile.setMinSize(width, height);
                tile.setOnMouseClicked(new ClickedTileHandler(row, col));
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

    public void update(List<Coordinates> switchedPos, Color currentPlayer) {
        //TODO mettre à jour en fonction de board, pas switchedPos
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
