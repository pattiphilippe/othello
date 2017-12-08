package g43197.othello.view;

import g43197.othello.model.util.Color;
import g43197.othello.model.util.Coordinates;
import g43197.othello.model.Facade;
import g43197.othello.model.Piece;
import g43197.othello.model.util.GameException;
import g43197.othello.model.util.GameState;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * This class displays the plate of the game.
 *
 * @author Philippe
 */
class BoardView extends GridPane {

    private final Facade game;
    private List<Coordinates> accessibles;
    private List<Coordinates> switchedPos;

    /**
     * Creates a new Board.
     *
     * @param width width of the new board
     * @param height height of the new board
     * @param game
     */
    BoardView(double width, double height, Facade game) {
        super();
        this.setEffect(new DropShadow());
        this.game = game;
        accessibles = new ArrayList<>();
        switchedPos = game.getSwitchedPositions();

        TileView tile;
        int maxRowsCols = game.getMaxRowsCols();
        height /= maxRowsCols;
        width /= maxRowsCols;
        Coordinates pos;

        for (int row = 0; row < maxRowsCols; row++) {
            for (int col = 0; col < maxRowsCols; col++) {
                tile = new TileView(width, height);
                tile.setPrefSize(width, height);
                tile.setOnMouseClicked(new ClickedTileHandler(row, col));
                pos = new Coordinates(row, col);
                this.add(tile, col, row);

                if (game.getPiece(pos) != null) {
                    addPiece(row, col, game.getPiece(pos).getColor());
                }
            }
        }
        updateAccessibles();
    }

    /**
     * Returns the tile at the given row and col on the board.
     *
     * @param row
     * @param col
     * @return
     */
    TileView getTileByRowCol(int row, int col) {
        return (TileView) this.getChildren().get(col + row * 8);
    }

    /**
     * Updates the view.
     *
     * @param fullUpdate if true, full update is done. Can be used for replaying
     * a game or if 2 ais playing against each other.
     */
    void update(boolean fullUpdate) {
        updateAccessibles();

        if (fullUpdate) {
            updateFull();
        } else {
            Color prevPlayer = game.getPreviousPlayer().getColor();
            int row, col;
            if (switchedPos.size() == 1) {
                row = switchedPos.get(0).getROW();
                col = switchedPos.get(0).getCOL();
                addPiece(row, col, Color.WALL);
            } else if (switchedPos.size() > 0) {
                row = switchedPos.get(0).getROW();
                col = switchedPos.get(0).getCOL();
                addPiece(row, col, prevPlayer);
                switchedPos.stream().skip(1).forEach(pos
                        -> getTileByRowCol(pos.getROW(), pos.getCOL()).switchColor()
                );
            }
        }
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void addPiece(int row, int col, Color color) {
        getTileByRowCol(row, col).addPiece(color);
    }

    private void updateAccessibles() {
        accessibles.stream().forEach(pos
                -> getTileByRowCol(pos.getROW(), pos.getCOL()).setAccessible(false));
        accessibles = game.getAccessibles();
        accessibles.stream().forEach(pos
                -> getTileByRowCol(pos.getROW(), pos.getCOL()).setAccessible(true));
    }

    private void updateFull() {
        TileView tile;
        Piece piece;
        for (int row = 0; row < game.getMaxRowsCols(); row++) {
            for (int col = 0; col < game.getMaxRowsCols(); col++) {
                tile = getTileByRowCol(row, col);
                tile.init();
                piece = game.getPiece(new Coordinates(row, col));
                if (piece != null) {
                    tile.addPiece(piece.getColor());
                }
            }
        }
        switchedPos = game.getSwitchedPositions();
    }

///////////////////////////Intern//Classes//////////////////////////////
    private class ClickedTileHandler implements EventHandler<MouseEvent> {

        private final int row;
        private final int col;

        public ClickedTileHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void handle(MouseEvent event) {
            if ((game.getState() == GameState.FINISHED) || game.isAi()) {
                event.consume();
            } else {
                try {
                    switch (event.getButton()) {
                        case PRIMARY:
                            game.putPiece(new Coordinates(row, col));
                            break;
                        case SECONDARY:
                            game.putWall(new Coordinates(row, col));
                            break;
                        default:
                            event.consume();
                    }
                } catch (GameException e) {
                    event.consume();
                }
                event.consume();
            }
        }
    }
}
