package g43197.othello.view;

import g43197.othello.model.util.Color;
import g43197.othello.model.util.Coordinates;
import g43197.othello.model.Facade;
import g43197.othello.model.IA;
import g43197.othello.model.Piece;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Node;
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

        class ClickedTileHandler implements EventHandler<MouseEvent> {

            private final int row;
            private final int col;

            public ClickedTileHandler(int row, int col) {
                this.row = row;
                this.col = col;
            }

            @Override
            public void handle(MouseEvent event) {
                if (game.isFinished() || (game.getCurrentPlayer() instanceof IA)) {
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
                        }
                    } catch (Exception e) {
                        event.consume();
                    }
                    event.consume();
                }
            }
        }

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
        TileView tile = null;
        //TODO optimiser méthode avec child(x) : x étant calculé avec row et col
        for (Node node : getChildren()) {
            if (getRowIndex(node) == row && getColumnIndex(node) == col) {
                tile = (TileView) node;
                break;
            }
        }
        return tile;
    }

    void replay() {
        TileView tile;
        Coordinates pos;
        for (int row = 0; row < game.getMaxRowsCols(); row++) {
            for (int col = 0; col < game.getMaxRowsCols(); col++) {
                tile = getTileByRowCol(row, col);
                tile.initTile();
                pos = new Coordinates(row, col);
                if (game.getPiece(pos) != null) {
                    tile.addPiece(game.getPiece(pos).getColor());
                }
            }
        }
        switchedPos = game.getSwitchedPositions();
    }

    /**
     * Updates the view.
     */
    void update() {
        updateAccessibles();

        if (game.getCurrentPlayer() instanceof IA && game.getPreviousPlayer() instanceof IA) {
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
                piece = game.getPiece(new Coordinates(row, col));
                if (piece != null) {
                    tile = getTileByRowCol(row, col);
                    tile.addPiece(piece.getColor());
                }
            }
        }
    }
}
