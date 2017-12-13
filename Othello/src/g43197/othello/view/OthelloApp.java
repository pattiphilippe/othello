package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Game;
import g43197.othello.model.util.Strategies;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * This class is the actual javaFX window for the game.
 *
 * @author Philippe
 */
public class OthelloApp extends Application {

    /**
     * The height of the window.
     */
    public static final int HEIGHT = 750;

    /**
     * The width of the window.
     */
    public static final int WIDTH = 1000;

    /**
     * Icon of the game.
     */
    public static final Image ICON = new Image("/g43197/othello/view/icon.png");

    /**
     * Time for one ai turn in milliseconds.
     */
    public static final int TURN_TIME = 1000;

    /**
     * Main method that launches the application with the window display.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method of the application.
     *
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");
        primaryStage.getIcons().add(ICON);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMaxHeight(HEIGHT);

        Alert editNewGame = new Alert(Alert.AlertType.WARNING);
        editNewGame.setTitle("Editing new game");
        editNewGame.setHeaderText("By validating the edits, "
                + "you will launch a new game! "
                + "All the info of your games will be lost!");

        MenuOthello menu = new MenuOthello();
        menu.getMenus().get(0).setOnAction(e -> {
            editNewGame.showAndWait();
            newGame(primaryStage, menu);
        });

        newGame(primaryStage, menu);

    }

    private void newGame(Stage primaryStage, MenuOthello menu) {
        GameOptions gameOptions = new GameOptions();
        Optional<Pair<Pair<String, Strategies>, Pair<String, Strategies>>> result;
        result = gameOptions.showAndWait();

        if (result.isPresent()) {
            String name1 = result.get().getKey().getKey();
            Strategies strat1 = result.get().getKey().getValue();
            String name2 = result.get().getValue().getKey();
            Strategies strat2 = result.get().getValue().getValue();

            Facade game = new Game(name1, strat1, name2, strat2);

            Window root = new Window(game, menu);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
