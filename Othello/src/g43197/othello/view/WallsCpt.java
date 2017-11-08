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
    private final Label walls;

    public WallsCpt(int nbWalls) {

        this.nbWalls = nbWalls;

        this.walls = new Label("They are " +this.nbWalls + " walls");
        walls.setFont(new Font(25));
        this.getChildren().add(this.walls);
    }

    public void addWall() {
        nbWalls++;
        walls.setText(this.nbWalls + " walls");
    }

}
