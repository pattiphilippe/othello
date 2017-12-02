//package OldCode;
//
//import g43197.othello.model.Facade;
//import g43197.othello.model.Player;
//import g43197.othello.view.BoardView;
//import g43197.othello.view.PlayersView;
//import g43197.othello.view.WallsCptView;
//import java.util.List;
//import javafx.geometry.Insets;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//
///**
// * HBox of Othello, with players, board and walls counter.
// *
// * @author Philippe
// */
//public class HBoxOthello extends HBox {
//
//    private final VBox left;
//    private final VBox right;
//    private final Facade game;
//    private final PlayersView players;
//    private final BoardView board;
//    private final WallsCptView wallsCpt;
//    private final Button pass;
//    private final Alert cantPass;
//    private final Button replay;
//    private final Alert checkReplay;
//    private final Button abandon;
//    private final Alert checkAbandon;
//    //TODO ? button undo
//    private final Region regionPlyWall;
//    private final Region regionLeftCenter;
//    private final Region regionCenterRidht;
//
//    /**
//     * Creates a new othello hbox.
//     *
//     * @param boardSize size of the board
//     * @param game
//     */
//    public HBoxOthello(double boardSize, Facade game) {
//        super();
//        this.game = game;
//
//        List<Player> names = game.getScores();
//        players = new PlayersView(10, names.get(0).getScore(), names.get(0).getColor().name(), names.get(1).getColor().name());
//        players.setPrefWidth(boardSize * 2 / 8);
//
//        regionPlyWall = new Region();
//        VBox.setVgrow(regionPlyWall, Priority.ALWAYS);
//
//        wallsCpt = new WallsCptView(game.getNbWalls());
//        wallsCpt.setPrefWidth(boardSize * 2 / 8);
//        wallsCpt.setPrefHeight(boardSize * 1 / 8);
//        wallsCpt.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        regionLeftCenter = new Region();
//        regionLeftCenter.setPrefWidth(boardSize * 1 / 20);
//        HBox.setHgrow(regionLeftCenter, Priority.ALWAYS);
//
//        board = new BoardView(boardSize, boardSize, game);
//        board.setCenterShape(true);
//        board.setPrefWidth(boardSize);
//        board.setPrefHeight(boardSize);
//
//        regionCenterRidht = new Region();
//        regionCenterRidht.setPrefWidth(boardSize * 1 / 20);
//        HBox.setHgrow(regionCenterRidht, Priority.ALWAYS);
//
//        cantPass = new Alert(AlertType.ERROR);
//        cantPass.setTitle("Passing alert");
//        cantPass.setHeaderText("Can't pass!");
//        cantPass.setContentText("Player can't pass, because he can put a piece. "
//                + "When a player can't put a piece, he can build a wall or pass");
//
//        pass = new Button("Pass");
//        pass.setOnMouseClicked(event -> {
//            if (!game.canPlay()) {
//                game.pass();
//            } else {
//                cantPass.show();
//                event.consume();
//            }
//        });
//
//        checkAbandon = new Alert(AlertType.CONFIRMATION);
//        checkAbandon.setTitle("Abandon game");
//        checkAbandon.setHeaderText("Check abnadon game");
//        checkAbandon.setContentText("Are you sure you wannna abandon the game?");
//        abandon = new Button("Abandon");
//        abandon.setOnMouseClicked(event -> {
//            checkAbandon.showAndWait();
//            if (checkAbandon.getResult() == ButtonType.OK) {
//                game.abandon();
//            } else {
//                event.consume();
//            }
//        });
//        checkReplay = new Alert(AlertType.CONFIRMATION);
//        checkReplay.setTitle("Replay game");
//        checkReplay.setHeaderText("Check replay game");
//        checkReplay.setContentText("Are you sure you wannna replay a new game?");
//        replay = new Button("Replay");
//        replay.setOnMouseClicked(event -> {
//            checkReplay.showAndWait();
//            if (checkReplay.getResult() == ButtonType.OK) {
//                game.startAgain();
//                board.replay();
//            } else {
//                event.consume();
//            }
//        });
//
//        left = new VBox(10);
//        left.getChildren().addAll(players, regionPlyWall, wallsCpt);
//        left.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        right = new VBox(10);
//        right.setPrefWidth(boardSize * 2 / 8);
//        right.getChildren().addAll(pass, replay, abandon);
//        right.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        this.getChildren().addAll(left, regionLeftCenter, board, regionCenterRidht, right);
//    }
//
//    /**
//     * Updates the hbox and its components.
//     */
//    public void update() {
//        players.update(game.getCurrentPlayer().getColor(), game.getScores());
//        board.update();
//        wallsCpt.update(game.getNbWalls());
//    }
//}
