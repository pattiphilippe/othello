package g43197.othello.view;

import g43197.othello.model.Color;
import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Philippe
 */
public class GraphicHelps extends VBox {

    private final Facade game;
    private final ProgressBar pieceProgress;

    public GraphicHelps(Facade game) {
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
        for (Player p : game.getScores()) {
            if (p.getColor() == Color.BLACK) {
                nbBlackPieces = p.getScore();
            } else {
                otherPieces += p.getScore();
            }
        }
        pieceProgress.setProgress(nbBlackPieces / (nbBlackPieces + otherPieces));
    }

    //TODO check visibilities of methods and classes
    public void update() {
        updatePieceProgress();
    }

}
