package g43197.othello.view;

import g43197.othello.model.util.Color;
import g43197.othello.model.Facade;
import g43197.othello.model.Piece;
import g43197.othello.model.Player;
import g43197.othello.model.util.Coordinates;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ProgressIndicator;

/**
 * Class with multiple Graphic helps like a progressbar.
 *
 * @author Philippe
 */
class GraphicHelps extends VBox {

    private final Facade game;
    private final ProgressBar pieceProgress;
    private final ProgressIndicator gameProgress;
    private final double nbTiles;
    private final ProgressBar wallsProgress;

    GraphicHelps(Facade game) {
        this.game = game;
        final HBox pieceProgressContainer = new HBox(10);
        pieceProgress = new ProgressBar();
        pieceProgress.setStyle("-fx-accent: black;");
        pieceProgressContainer.getChildren().addAll(new Label("Black/White"), pieceProgress);

        final HBox wallsProgressContainer = new HBox(10);
        wallsProgress = new ProgressBar();
        wallsProgress.setStyle("-fx-accent: brown;");
        wallsProgressContainer.getChildren().addAll(new Label("Walls/Pieces"), wallsProgress);
        updatePieceWallsProgress();

        final HBox gameProgressContainer = new HBox(10);
        gameProgress = new ProgressIndicator(0);
        nbTiles = Math.pow(game.getMaxRowsCols(), 2);
        gameProgressContainer.getChildren().addAll(new Label("Game progress"), gameProgress);
        updateGameProgress();

        this.getChildren().addAll(pieceProgressContainer, wallsProgressContainer, gameProgressContainer);
    }

    void update() {
        updatePieceWallsProgress();
        updateGameProgress();
    }

    private void updatePieceWallsProgress() {
        double nbBlackPieces = 0, otherPieces = 0;
        for (Player p : game.getPlayers()) {
            if (p.getColor() == Color.BLACK) {
                nbBlackPieces = p.getScore();
            } else {
                otherPieces += p.getScore();
            }
        }
        double nbPieces = nbBlackPieces + otherPieces;
        pieceProgress.setProgress(nbBlackPieces / nbPieces);
        wallsProgress.setProgress(game.getNbWalls() / nbPieces);
    }

    private void updateGameProgress() {
        int nbPiecesOnBoard = game.getPlayers().stream()
                .map(player -> player.getScore())
                .reduce(0, Integer::sum);
        gameProgress.setProgress(nbPiecesOnBoard / nbTiles);
    }
}
