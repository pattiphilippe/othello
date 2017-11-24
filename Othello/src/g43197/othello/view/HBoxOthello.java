package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import java.util.List;
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
public class HBoxOthello extends HBox {

    private final Facade game;
    private final PlayersView players;
    private final BoardView board;
    private final WallsCptView wallsCpt;
    private final Region region1;
    private final Region region2;

    /**
     * Creates a new othello hbox.
     *
     * @param boardSize size of the board
     * @param game
     */
    public HBoxOthello(double boardSize, Facade game) {
        super();
        this.game = game;

        List<Player> names = game.getScores();
        players = new PlayersView(10, names.get(0).getScore(), names.get(0).getColor().name(), names.get(1).getColor().name());
        players.setPrefWidth(boardSize * 2 / 8);

        players.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        region1 = new Region();
        region1.setPrefWidth(boardSize * 1 / 20);
        HBox.setHgrow(region1, Priority.ALWAYS);

        wallsCpt = new WallsCptView(game.getNbWalls());

        board = new BoardView(boardSize, boardSize, wallsCpt, game);
        board.setCenterShape(true);
        board.setPrefWidth(boardSize);
        board.setPrefHeight(boardSize);

        region2 = new Region();
        region2.setPrefWidth(boardSize * 1 / 20);
        HBox.setHgrow(region2, Priority.ALWAYS);

        wallsCpt.setPrefWidth(boardSize * 2 / 8);

        wallsCpt.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().addAll(players, region1, board, region2, wallsCpt);
    }

    public void update() {
        players.update(game.getCurrentPlayer(), game.getScores());
        board.update();
        wallsCpt.update(game.getNbWalls());
    }
}
