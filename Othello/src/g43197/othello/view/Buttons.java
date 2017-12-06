package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.util.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

/**
 * Class with all the buttons.
 *
 * @author Philippe
 */
class Buttons extends HBox {

    private final Alert gameFinished;
    private final Button pass;
    private final Alert cantPass;
    private final Button replay;
    private final Alert checkReplay;
    private final Button abandon;
    private final Alert checkAbandon;

    Buttons(Facade game, BoardView board) {
        super(10);
        this.setAlignment(Pos.CENTER);

        gameFinished = new Alert(Alert.AlertType.ERROR);
        gameFinished.setTitle("Game finished");
        gameFinished.setHeaderText("Can't pass or abandon!");
        gameFinished.setContentText("You can't do anything, "
                + "the game is ... Finished! Stop trying to find bugs...");

        // Pass
        cantPass = new Alert(Alert.AlertType.ERROR);
        cantPass.setTitle("Passing alert");
        cantPass.setHeaderText("Can't pass!");
        cantPass.setContentText("Player can't pass, because he can put a piece. "
                + "When a player can't put a piece, he can build a wall or pass. ");

        pass = new Button("Pass");
        pass.setOnMouseClicked(event -> {
            if (game.getState() == GameState.FINISHED) {
                gameFinished.show();
            } else if (!game.canPlay()) {
                game.pass();
            } else {
                cantPass.show();
            }
            event.consume();
        });

        // Abandon
        checkAbandon = new Alert(Alert.AlertType.CONFIRMATION);
        checkAbandon.setTitle("Abandon game");
        checkAbandon.setHeaderText("Check abnadon game");
        checkAbandon.setContentText("Are you sure you wannna abandon the game?");

        abandon = new Button("Abandon");
        abandon.setOnMouseClicked(event -> {
            if (game.getState() == GameState.FINISHED) {
                gameFinished.show();
            } else {
                checkAbandon.showAndWait();
                if (checkAbandon.getResult() == ButtonType.OK) {
                    game.abandon();
                }
            }
            event.consume();
        });

        //TODO pas fin de jeu quand ia sait jouer
        //TODO pas bouton possible avant affichage tour ia
        // Replay
        checkReplay = new Alert(Alert.AlertType.CONFIRMATION);
        checkReplay.setTitle("Replay game");
        checkReplay.setHeaderText("Check replay game");
        checkReplay.setContentText("Are you sure you wannna replay a new game?");

        replay = new Button("Replay");
        replay.setOnMouseClicked(event -> {
            checkReplay.showAndWait();
            if (checkReplay.getResult() == ButtonType.OK) {
                game.startAgain();
            } else {
                event.consume();
            }
        });
        this.getChildren().addAll(pass, abandon, replay);
    }
}
