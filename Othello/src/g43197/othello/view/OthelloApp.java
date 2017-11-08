 package g43197.othello.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMaxHeight(HEIGHT);

        Window root = new Window();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
