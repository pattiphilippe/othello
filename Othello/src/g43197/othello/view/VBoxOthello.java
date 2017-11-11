package g43197.othello.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class gets the title and the rest of the output.
 *
 * @author Philippe
 */
public class VBoxOthello extends VBox {

    private final Label title;
    private final HBoxOthello hbox;

    /**
     * Creates a new Window for the Othello game.
     *
     * @param d
     */
    public VBoxOthello(double d) {
        super(d);

        title = new Label("----------OTHELLO----------\n");
        title.setFont(Font.font(60));
        this.setAlignment(Pos.CENTER);

        hbox = new HBoxOthello(d);

        this.getChildren().addAll(title, hbox);
    }
}
