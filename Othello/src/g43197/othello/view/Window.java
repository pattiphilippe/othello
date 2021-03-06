package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import g43197.othello.model.util.GameState;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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

    private final Facade game;
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

    Window(Facade game, MenuOthello menu) {
        //this settings
        super();
        this.game = game;
        this.game.addObserver(this);

        this.setTop(menu);

        // Left side
        final double boardSize = OthelloApp.HEIGHT * 4 / 7;
        board = new BoardView(boardSize, boardSize, game);
        graphicHelps = new GraphicHelps(game);
        buttons = new Buttons(game, board);

        final VBox left = new VBox();
        left.setPadding(new Insets(25));
        left.setSpacing(25);
        left.getChildren().addAll(board, graphicHelps, buttons);
        this.setLeft(left);

        // Right side
        final List<Player> playersList = game.getPlayers();
        players = new PlayersView(10, playersList.toArray(new Player[playersList.size()]));
        historic = new HistoricView(game.getHistoric());

        final VBox right = new VBox();
        right.setPadding(new Insets(25));
        right.setSpacing(25);
        right.getChildren().addAll(players, historic);
        this.setRight(right);

        // Others
        finishedGame = new Alert(Alert.AlertType.INFORMATION);
        finishedGame.setTitle("Finished Game");
        finishedGame.setHeaderText("Congratulations!");
        pause = new PauseTransition(new Duration(OthelloApp.TURN_TIME));
        pause.setOnFinished(e -> game.iaPlay());
        if (game.isAi()) {
            pause.play();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        final boolean isFinished = game.getState() == GameState.FINISHED;
        historic.update();
        graphicHelps.update();
        players.update(game.getCurrentPlayer().getColor(), game.getPlayers(), isFinished);
        board.update(game.getState() == GameState.JUST_STARTED);
        if (isFinished) {
            Player winner = game.getWinner();
            finishedGame.setContentText("Player " + winner.getName()
                    + " won with " + winner.getScore() + " points.");
            finishedGame.show();
        }
        if (game.isAi()) {
            pause.play();
        }
    }
}
