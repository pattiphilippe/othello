package g43197.othello.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class displays one player with his name and score.
 *
 * @author Philippe
 */
public class Player extends VBox {
    
    private Label name;
    private Label score;
    
    public Player() {
        super();
        
        this.setAlignment(Pos.TOP_LEFT);
        
        name = new Label("Player");
        name.setFont(Font.font(25));
        score = new Label("Score : " + 0);
        score.setFont(Font.font(25));
        
        this.getChildren().addAll(name, score);
    }
}
