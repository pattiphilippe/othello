package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the actual javaFX window for the game.
 *
 * @author Philippe
 */
public class OthelloApp extends Application {

    //TODO ? comment gérer les tailles: définie min max, ou pref? (peut faire fenêtre plus petite)
    /**
     * The height of the window.
     */
    public static final int HEIGHT = 750;

    /**
     * The width of the window.
     */
    public static final int WIDTH = 1000;

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
        //TODO refaire toutes les tailles, et tirer les min size dans OthelloApp
//        primaryStage.setMinWidth(WIDTH * 4 / 5);
//        primaryStage.setMinHeight(HEIGHT * 4 / 5);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMaxHeight(HEIGHT);
        //TODO coder menubar avec exit et les commandes
        //TODO tableView<...> , class ligne avec properties, observableList<Ligne>

        Facade game = new Game();
        Window root = new Window(game);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
