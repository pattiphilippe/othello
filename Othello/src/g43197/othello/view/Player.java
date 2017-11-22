package g43197.othello.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class displays one player with his name and score.
 *
 * @author Philippe
 */
public class Player extends VBox {

    private final Label name;
    private final Label score;

    /**
     * Creates a new player with the given name.
     *
     * @param name
     */
    public Player(String name) {
        super();

        this.name = new Label(name);
        this.name.setFont(Font.font(20));
        score = new Label("Score : " + 0);
        score.setFont(Font.font(20));

        this.getChildren().addAll(this.name, score);
    }
    
    public boolean isPlayer(String name){
        return this.name.getText().equalsIgnoreCase(name);
    }

    void updateScore(Integer score) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
