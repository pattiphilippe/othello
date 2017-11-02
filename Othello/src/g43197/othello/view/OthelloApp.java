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

    public static final int MIN_WIDTH_HEIGHT = 500;

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
        primaryStage.setMinWidth(MIN_WIDTH_HEIGHT);
        primaryStage.setMinHeight(MIN_WIDTH_HEIGHT * 1.1);
//        primaryStage.setMaxHeight(750);
//        primaryStage.setMaxWidth(1000);

        Window root = new Window();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
