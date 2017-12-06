package g43197.othello.view;

import g43197.othello.model.util.Color;
import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class with multiple Graphic helps like a progressbar.
 *
 * @author Philippe
 */
class GraphicHelps extends VBox {

    //TODO ajouter autre progresindicator et walls cpt
    private final Facade game;
    private final ProgressBar pieceProgress;

    GraphicHelps(Facade game) {
        this.game = game;
        HBox pieceProgressContainer = new HBox(10);
        pieceProgress = new ProgressBar();
        pieceProgress.setStyle("-fx-accent: black;");
        pieceProgressContainer.getChildren().addAll(new Label("Black/White"), pieceProgress);
        updatePieceProgress();
        this.getChildren().addAll(pieceProgressContainer);
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

    void update() {
        updatePieceProgress();
    }

}