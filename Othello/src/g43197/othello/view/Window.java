package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Philippe
 */
public class Window extends BorderPane implements Observer {

    //TODO top: menubar
    //TODO left : board, buttons ; right : players, historic 
    //TODO add a wall cpt view
    //TODO add a class for graphicHelps with progressBar, ...
    // Top
    private final MenuOthello menu;
    // Left
    private final BoardView board;
    private final Buttons buttons;
    // Right
    private final PlayersView players;
    // Others
    private final Alert finishedGame;

    public Window(Facade game) {
        //this settings
        super();
        game.addObserver(this);

        // Top
        menu = new MenuOthello();
        this.setTop(menu);

        // Left side
        double boardSize = OthelloApp.HEIGHT * 4 / 7;
        board = new BoardView(boardSize, boardSize, game);
        buttons = new Buttons(game, board);

        VBox left = new VBox();
        left.setPadding(new Insets(25));
        left.setSpacing(25);
        left.getChildren().addAll(board, buttons);
        this.setLeft(left);

        // Right side
        List<Player> playersList = game.getScores();
        players = new PlayersView(10, playersList.toArray(new Player[playersList.size()]));

        VBox right = new VBox();
        right.setPadding(new Insets(25));
        right.getChildren().addAll(players);
        this.setRight(right);

        // Others
        finishedGame = new Alert(Alert.AlertType.INFORMATION);
        finishedGame.setTitle("Finished Game");
        finishedGame.setHeaderText("Congratulations!");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof Facade)) {
            throw new IllegalArgumentException("Unknown type of observable!");
        }
        Facade game = (Facade) o;
        if (game.isFinished()) {
            Player winner = game.getWinner();
            finishedGame.setContentText("Player " + winner.getName()
                    + " won with " + winner.getScore() + " points.");
            finishedGame.show();
        }
        if (!game.abandonned()) {
            //TODO check if other stuff to update : wallscpt
            players.update(game.getCurrentPlayer().getName(), game.getScores());
            board.update();
        }
    }
}
