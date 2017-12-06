package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.IA;
import g43197.othello.model.util.Observable;
import g43197.othello.model.util.Observer;
import g43197.othello.model.Player;
import g43197.othello.model.util.GameState;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Philippe
 */
class Window extends BorderPane implements Observer {

    //TODO add a wall cpt view
    private final Facade game;
    // Top
    private final MenuOthello menu;
    // Left
    private final BoardView board;
    private final GraphicHelps graphicHelps;
    private final Buttons buttons;
    // Right
    private final PlayersView players;
    private final HistoricView historic;
    // Others
    private final Alert finishedGame;
    private final PauseTransition pause;

    Window(Facade game) {
        //this settings
        super();
        this.game = game;
        this.game.addObserver(this);

        // Top
        menu = new MenuOthello();
        this.setTop(menu);

        // Left side
        double boardSize = OthelloApp.HEIGHT * 4 / 7;
        board = new BoardView(boardSize, boardSize, game);
        graphicHelps = new GraphicHelps(game);
        buttons = new Buttons(game, board);

        VBox left = new VBox();
        left.setPadding(new Insets(25));
        left.setSpacing(25);
        left.getChildren().addAll(board, graphicHelps, buttons);
        this.setLeft(left);

        // Right side
        List<Player> playersList = game.getPlayers();
        players = new PlayersView(10, playersList.toArray(new Player[playersList.size()]));
        historic = new HistoricView(game.getHistoric());

        VBox right = new VBox();
        right.setPadding(new Insets(25));
        right.setSpacing(25);
        right.getChildren().addAll(players, historic);
        this.setRight(right);

        // Others
        finishedGame = new Alert(Alert.AlertType.INFORMATION);
        finishedGame.setTitle("Finished Game");
        finishedGame.setHeaderText("Congratulations!");
        pause = new PauseTransition(new Duration(1000));
        pause.setOnFinished(e -> update());

        game.iaStart();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (game.getPreviousPlayer() instanceof IA) {
            pause.play();
        } else {
            update();
        }
    }

    private void update() {
        graphicHelps.update();
        players.update(game.getCurrentPlayer().getName(), game.getPlayers());
        System.out.println("window update");
        board.update(game.getState() == GameState.JUST_STARTED);
        if (game.getState() == GameState.FINISHED) {
            Player winner = game.getWinner();
            finishedGame.setContentText("Player " + winner.getName()
                    + " won with " + winner.getScore() + " points.");
            finishedGame.show();
        }
    }
}
