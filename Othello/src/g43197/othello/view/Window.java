package g43197.othello.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * This class gets the title and the rest of the output.
 *
 * @author Philippe
 */
public class Window extends BorderPane {

    private Label title;
    private Player player1;
    private Player player2;
    private Plate plate;

    public Window() {
        super();

        title = new Label("----------OTHELLO----------\n");
        title.setFont(Font.font(60));
        this.setAlignment(title, Pos.TOP_CENTER);
        this.setTop(title);

        player1 = new Player();
        this.setLeft(player1);
        this.setPadding(new Insets(20));
        this.setAlignment(player1, Pos.CENTER_LEFT);
        player2 = new Player();
        this.setRight(player2);
        this.setAlignment(player2, Pos.CENTER_RIGHT);

        plate = new Plate();
        this.setBottom(plate);
        this.setAlignment(plate, Pos.BASELINE_CENTER);
    }
}
