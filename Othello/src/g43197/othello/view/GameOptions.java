package g43197.othello.view;

import g43197.othello.model.util.Strategies;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Philippe
 */
public class GameOptions extends Dialog<Pair<Pair<String, String>, Pair<Strategies, Strategies>>> {

    //TODO constantes privées dès qu'immédiat employé
    private final GridPane grid;
    private final TextField name1Tfd;
    private final TextField name2Tfd;
    private final ChoiceBox strat1CB;
    private final ChoiceBox strat2CB;

    public GameOptions() {
        super();

        Stage stage = (Stage) (this.getDialogPane().getScene().getWindow());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/g43197/othello/view/icon.png")));
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

        name1Tfd = new TextField();
        name1Tfd.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (name1Tfd.getText().length() >= 8) {
                e.consume();
            }
        });
        name1Tfd.setPromptText("Name of player 1");

        grid.add(name1Tfd, 1, 3);

        name2Tfd = new TextField();
        name2Tfd.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (name2Tfd.getText().length() >= 8) {
                e.consume();
            }
        });
        name2Tfd.setPromptText("Name of player 2");
        grid.add(name2Tfd, 1, 4);

        strat1CB = new ChoiceBox(FXCollections.observableArrayList(Strategies.values()));
        strat1CB.getSelectionModel().selectFirst();
        grid.add(strat1CB, 2, 3);
        strat2CB = new ChoiceBox(FXCollections.observableArrayList(Strategies.values()));
        strat2CB.getSelectionModel().selectLast();
        grid.add(strat2CB, 2, 4);

        for (Node child : grid.getChildren()) {
            GridPane.setMargin(child, new Insets(10));
        }

        this.getDialogPane().setContent(grid);

        this.setResultConverter(dialogButton -> {
            if (dialogButton == validateButtonType) {
                String name1 = name1Tfd.getText();
                String name2 = name2Tfd.getText();
                Strategies strat1 = Strategies.valueOf(strat1CB.getValue().toString());
                Strategies strat2 = Strategies.valueOf(strat2CB.getValue().toString());
                return new Pair<>(new Pair<>(name1, name2), new Pair<>(strat1, strat2));
            } else {
                return null;
            }
        });
    }
}
