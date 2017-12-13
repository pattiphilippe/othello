package g43197.othello.view;

import g43197.othello.model.util.Strategies;
import static g43197.othello.view.OthelloApp.ICON;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
 * Dialog Pane with the options of the game.
 *
 * @author Philippe
 */
class GameOptions extends Dialog<Boolean> {

    private final GridPane grid;
    private final TextField name1Tfd;
    private final TextField name2Tfd;
    private final ChoiceBox strat1CB;
    private final ChoiceBox strat2CB;
    private final RadioButton scoreView;

    /**
     * Creates a new Dialog Pane to get the options for the game.
     */
    GameOptions() {
        super();

        final Stage stage = (Stage) (this.getDialogPane().getScene().getWindow());
        stage.getIcons().add(ICON);
        this.setTitle("Othello - Options");

        final ButtonType playButton = new ButtonType("Validate", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(playButton, ButtonType.CLOSE);

        grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.BISQUE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getDialogPane().setContent(grid);

        addGridTitles();
        name1Tfd = new TextField();
        name2Tfd = new TextField();
        strat1CB = new ChoiceBox(FXCollections.observableArrayList(Strategies.values()));
        strat2CB = new ChoiceBox(FXCollections.observableArrayList(Strategies.values()));
        scoreView = new RadioButton();
        addGridContent();

        this.setResultConverter(dialogButton -> {
            if (dialogButton == playButton) {
                return true;
            } else {
                return null;
            }
        });
    }

    private void addGridTitles() {
        final Label title = new Label("Othello - Options");
        title.setFont(new Font(30));
        title.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        new CornerRadii(10), BorderWidths.DEFAULT)));
        grid.add(title, 0, 0, 3, 2);
        GridPane.setHalignment(title, HPos.CENTER);

        final Label namesLbl = new Label("Names");
        namesLbl.setFont(new Font(15));
        grid.add(namesLbl, 1, 2);

        final Label playersLbl = new Label("Strategy");
        playersLbl.setFont(new Font(15));
        grid.add(playersLbl, 2, 2);

        final Label player1 = new Label("Player 1");
        player1.setFont(new Font(15));
        grid.add(player1, 0, 3);

        final Label player2 = new Label("Player 2");
        player2.setFont(new Font(15));
        grid.add(player2, 0, 4);

        final Label scoreViewLbl = new Label("Score View : ");
        scoreViewLbl.setFont(new Font(15));
        grid.add(scoreViewLbl, 0, 5);
    }

    private void addGridContent() {
        name1Tfd.setPromptText("Name of player 1");
        name1Tfd.setTooltip(new Tooltip("Name of the player who plays with black pieces."));
        name1Tfd.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (name1Tfd.getText().length() >= 8) {
                e.consume();
            }
        });
        grid.add(name1Tfd, 1, 3);

        name2Tfd.setPromptText("Name of player 2");
        name2Tfd.setTooltip(new Tooltip("Name of the player who plays with white pieces."));
        name2Tfd.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (name2Tfd.getText().length() >= 8) {
                e.consume();
            }
        });
        grid.add(name2Tfd, 1, 4);

        strat1CB.getSelectionModel().selectFirst();
        grid.add(strat1CB, 2, 3);

        strat2CB.getSelectionModel().select(1);
        grid.add(strat2CB, 2, 4);

        grid.add(scoreView, 1, 5);

        grid.getChildren().forEach((child) -> GridPane.setMargin(child, new Insets(10)));
    }

    String getName1() {
        return name1Tfd.getText();
    }

    String getName2() {
        return name2Tfd.getText();
    }

    Strategies getStrat1() {
        return Strategies.valueOf(strat1CB.getValue().toString());
    }

    Strategies getStrat2() {
        return Strategies.valueOf(strat2CB.getValue().toString());
    }

    boolean scoreView() {
        return scoreView.isSelected();
    }
}
