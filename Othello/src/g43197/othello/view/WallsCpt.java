package g43197.othello.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author G43197
 */
public class WallsCpt extends Pane {

    private int nbWalls;
    private final Label lblWallsCpt;

    public WallsCpt() {

        this.nbWalls = 0;

        this.lblWallsCpt = new Label("Walls: " + this.nbWalls);
        lblWallsCpt.setFont(new Font(20));
        this.getChildren().add(this.lblWallsCpt);
    }

    public void addWall() {
        nbWalls++;
        lblWallsCpt.setText("Walls: " + this.nbWalls);
    }

}