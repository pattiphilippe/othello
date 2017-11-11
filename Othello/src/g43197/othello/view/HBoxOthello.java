package g43197.othello.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 *
 * @author Philippe
 */
public class HBoxOthello extends HBox {

    private final Players players;
    private final Board board;
    private final Region region;

    public HBoxOthello(double d) {
        super();

        players = new Players(10);
        players.setMaxWidth(OthelloApp.WIDTH * 1 / 5);
        HBox.setHgrow(players, Priority.ALWAYS);
        players.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

        board = new Board();
        board.setCenterShape(true);
        board.setMaxWidth(OthelloApp.WIDTH * 3 / 5);
        HBox.setHgrow(board, Priority.ALWAYS);

        region = new Region();
        region.setMaxWidth(OthelloApp.WIDTH * 1 / 5);
        region.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setHgrow(region, Priority.ALWAYS);

        this.getChildren().addAll(players, board, region);
    }
}
