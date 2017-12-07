package g43197.othello.view;

import g43197.othello.model.Player;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Philippe
 */
public class GameInfoPanel extends Dialog<List<Player>> {

    private final GridPane grid;
    private final TextField name1;
    private final TextField name2;
    private final RadioButton isIa1;
    private final RadioButton isIa2;

    public GameInfoPanel() {
        super();

        this.setTitle("Othello - Options");

        ButtonType validateButtonType = new ButtonType("Validate", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(validateButtonType, ButtonType.CLOSE);

        grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Othello - Options");
        title.setFont(new Font(30));
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        grid.add(title, 0, 0, 3, 2);
        GridPane.setHalignment(title, HPos.CENTER);

        Label namesLbl = new Label("Names");
        namesLbl.setFont(new Font(15));
        grid.add(namesLbl, 1, 2);

        Label playersLbl = new Label("AI");
        playersLbl.setFont(new Font(15));
        grid.add(playersLbl, 2, 2);

        Label player1 = new Label("Player 1");
        player1.setFont(new Font(15));
        grid.add(player1, 0, 3);
        Label player2 = new Label("Player 2");
        player2.setFont(new Font(15));
        grid.add(player2, 0, 4);

        name1 = new TextField();
        name1.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (name1.getText().length() >= 8) {
                e.consume();
            }
        });
        name1.setPromptText("Name of player 1");

        grid.add(name1, 1, 3);

        name2 = new TextField();
        name2.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (name2.getText().length() >= 8) {
                e.consume();
            }
        });
        name2.setPromptText("Name of player 2");
        grid.add(name2, 1, 4);

        isIa1 = new RadioButton("Is AI");
        grid.add(isIa1, 2, 3);
        isIa2 = new RadioButton("Is AI");
        grid.add(isIa2, 2, 4);

        for (Node child : grid.getChildren()) {
            GridPane.setMargin(child, new Insets(10));
        }

        this.getDialogPane().setContent(grid);
    }

//        vbox = new VBox();
//
//        grid = new GridPane();
//        grid.setGridLinesVisible(true);
//        this.setGraphic(vbox);
//
//        this.setHeaderText("Othello - options");
//
//        Label namesLbl = new Label("Names");
//        namesLbl.setFont(new Font(15));
//        grid.add(namesLbl, 1, 0);
//
//        Label playersLbl = new Label("AI");
//        playersLbl.setFont(new Font(15));
//        grid.add(playersLbl, 2, 0);
//
//        Label player1 = new Label("Player 1");
//        player1.setFont(new Font(15));
//        grid.add(player1, 0, 1);
//        Label player2 = new Label("Player 2");
//        player2.setFont(new Font(15));
//        grid.add(player2, 0, 2);
//
//        name1 = new TextField();
//        name1.setPromptText("Name of player 1");
//        grid.add(name1, 1, 1);
//
//        name2 = new TextField();
//        name2.setPromptText("Name of player 2");
//        grid.add(name2, 1, 2);
//
//        isIa1 = new RadioButton("Is AI");
//        grid.add(isIa1, 2, 1);
//        isIa2 = new RadioButton("Is AI");
//        grid.add(isIa2, 2, 2);
//
//        for (Node child : grid.getChildren()) {
//            GridPane.setMargin(child, new Insets(10));
//        }
//        vbox.getChildren().add(grid);
}
