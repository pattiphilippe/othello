package g43197.othello.view;

import g43197.othello.model.Facade;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * HBox of Othello, with players, board and walls counter.
 *
 * @author Philippe
 */
public class HBoxOthello extends HBox implements Observer {

    private final Players players;
    private final Board board;
    private final WallsCpt wallsCpt;
    private final Region region1;
    private final Region region2;

    /**
     * Creates a new othello hbox.
     *
     * @param boardSize size of the board
     */
    public HBoxOthello(double boardSize) {
        super();

        players = new Players(10);
        players.setMinWidth(boardSize * 2 / 8);

        players.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        region1 = new Region();
        region1.setMinWidth(boardSize * 1 / 20);
        HBox.setHgrow(region1, Priority.ALWAYS);

        wallsCpt = new WallsCpt();

        board = new Board(boardSize, boardSize, wallsCpt);
        board.setCenterShape(true);
        board.setMaxWidth(boardSize);
        board.setMaxHeight(boardSize);

        region2 = new Region();
        region2.setMinWidth(boardSize * 1 / 20);
        HBox.setHgrow(region2, Priority.ALWAYS);

        wallsCpt.setMinWidth(boardSize * 2 / 8);

        wallsCpt.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().addAll(players, region1, board, region2, wallsCpt);
    }

    @Override
    public void update(Observable o, Object arg) {
        Facade game = (Facade) o;
        players.update(game.getCurrentPlayer(), game.getScores());
        board.update(game.getSwitchedPositions(), game.getCurrentPlayer());
        
    }
}
