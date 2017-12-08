package g43197.othello.view;

import g43197.othello.model.util.Color;
import g43197.othello.model.Facade;
import g43197.othello.model.Player;
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

    GraphicHelps(Facade game) {
        this.game = game;
        final HBox pieceProgressContainer = new HBox(10);
        pieceProgress = new ProgressBar();
        pieceProgress.setStyle("-fx-accent: black;");
        pieceProgressContainer.getChildren().addAll(new Label("Black/White"), pieceProgress);
        updatePieceProgress();

        final HBox gameProgressContainer = new HBox(10);
        gameProgress = new ProgressIndicator(0);
        nbTiles = Math.pow(game.getMaxRowsCols(), 2);
        gameProgressContainer.getChildren().addAll(new Label("Game progress"), gameProgress);
        updateGameProgress();

        this.getChildren().addAll(pieceProgressContainer, gameProgressContainer);
    }

    void update() {
        updatePieceProgress();
        updateGameProgress();
    }

    private void updatePieceProgress() {
        double nbBlackPieces = 0, otherPieces = 0;
        for (Player p : game.getPlayers()) {
            if (p.getColor() == Color.BLACK) {
                nbBlackPieces = p.getScore();
            } else {
                otherPieces += p.getScore();
            }
        }
        pieceProgress.setProgress(nbBlackPieces / (nbBlackPieces + otherPieces));
    }

    private void updateGameProgress() {
        int nbPiecesOnBoard = game.getPlayers().stream()
                .map(player -> player.getScore())
                .reduce(0, Integer::sum);
        gameProgress.setProgress(nbPiecesOnBoard / nbTiles);
    }

}
