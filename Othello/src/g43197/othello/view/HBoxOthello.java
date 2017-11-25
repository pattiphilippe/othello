package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * HBox of Othello, with players, board and walls counter.
 *
 * @author Philippe
 */
public class HBoxOthello extends HBox {
    
    private final VBox left;
    private final VBox right;
    private final Facade game;
    private final PlayersView players;
    private final BoardView board;
    private final WallsCptView wallsCpt;
//    private final Button pass;
//    private final Button replay;
//    private final Button abandon;
    //TODO button undo
    private final Region regionPlyWall;
    private final Region regionLeftCenter;
    private final Region regionCenterRidht;

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
//        players.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        regionPlyWall = new Region();
        VBox.setVgrow(regionPlyWall, Priority.ALWAYS);
        
        wallsCpt = new WallsCptView(game.getNbWalls());
        wallsCpt.setPrefWidth(boardSize * 2 / 8);
        wallsCpt.setPrefHeight(boardSize * 1 / 8);
        wallsCpt.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        regionLeftCenter = new Region();
        regionLeftCenter.setPrefWidth(boardSize * 1 / 20);
        HBox.setHgrow(regionLeftCenter, Priority.ALWAYS);
        
        board = new BoardView(boardSize, boardSize, game);
        board.setCenterShape(true);
        board.setPrefWidth(boardSize);
        board.setPrefHeight(boardSize);
        
        regionCenterRidht = new Region();
        regionCenterRidht.setPrefWidth(boardSize * 1 / 20);
        HBox.setHgrow(regionCenterRidht, Priority.ALWAYS);
        
        
        
        left = new VBox(10);
        left.getChildren().addAll(players, regionPlyWall, wallsCpt);
        left.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        right = new VBox(10);
        right.setPrefWidth(boardSize * 2 / 8);
        right.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        this.getChildren().addAll(left, regionLeftCenter, board, regionCenterRidht, right);
    }
    
    public void update() {
        players.update(game.getCurrentPlayer(), game.getScores());
        board.update();
        wallsCpt.update(game.getNbWalls());
    }
}
