package g43197.othello.view;

import javafx.scene.layout.VBox;

/**
 * List of players with their score.
 *
 * @author Philippe
 */
public class Players extends VBox {

    private final Player player1;
    private final Player player2;

    /**
     * Creates the list of players with their score.
     *
     * @param d the insets between players node.
     */
    public Players(double d) {
        super(d);

        player1 = new Player("White");
        player2 = new Player("Black");
        this.getChildren().addAll(player1, player2);
    }
}
