package g43197.othello.view;

import static g43197.othello.model.Board.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This class displays the plate of the game.
 *
 * @author Philippe
 */
public class Plate extends GridPane {

    public Plate() {
        super();

        double size = 1.1 * 500;
        this.setMinSize(size, size);
        this.setMaxSize(size, size);

        Border borderOut = new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
        Border borderIn = new Border(new BorderStroke(Color.GREENYELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)));

        Pane pane;
        Label test;
        size /= MAX_ROWS_COLS;
        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                pane = new Pane();
                pane.setBorder(borderIn);
                pane.setMinSize(size, size);
                pane.setMaxSize(size, size);
                test = new Label("row: " + row + ", col:" + col);
                pane.getChildren().add(test);
                this.add(pane, col, row);
            }
        }
        this.setBorder(borderOut);
    }
}
