package g43197.othello.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class displays one player with his name and score.
 *
 * @author Philippe
 */
public class PlayerView extends VBox {

    private final Label name;
    private final Label score;

    /**
     * Creates a new player with the given name.
     *
     * @param name player's name
     * @param score first score of the player
     */
    public PlayerView(String name, int score) {
        super();

        this.name = new Label(name);
        this.name.setFont(Font.font(20));
        this.score = new Label("Score : " + score);
        this.score.setFont(Font.font(20));

        this.getChildren().addAll(this.name, this.score);
    }

    public boolean isPlayer(String name) {
        return this.name.getText().equalsIgnoreCase(name);
    }

    public void updateScore(Integer score) {
        this.score.setText("Score : " + score);
    }
}
