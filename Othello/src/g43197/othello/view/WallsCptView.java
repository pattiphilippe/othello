package g43197.othello.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author G43197
 */
public class WallsCptView extends Pane {

    private int nbWalls;
    private final Label lblWallsCpt;

    /**
     * Creates a view for the walls counter.
     *
     * @param nbWalls
     */
    public WallsCptView(int nbWalls) {

        this.nbWalls = nbWalls;

        this.lblWallsCpt = new Label("Walls : " + this.nbWalls);
        lblWallsCpt.setFont(new Font(20));
        this.getChildren().add(this.lblWallsCpt);
    }

    /**
     * Updates the view of the walls counter.
     *
     * @param nbWalls
     */
    public void update(int nbWalls) {
        this.nbWalls = nbWalls;
        lblWallsCpt.setText("Walls: " + this.nbWalls);
    }

}
