package g43197.othello.view;

import javafx.scene.layout.VBox;

/**
 *
 * @author Philippe
 */
public class Players extends VBox {

    private final Player player1;
    private final Player player2;

    public Players(double d) {
        super(d);

        player1 = new Player("White");
        player2 = new Player("Black");
        this.getChildren().addAll(player1, player2);
    }
}
