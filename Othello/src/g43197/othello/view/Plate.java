package g43197.othello.view;

import static g43197.othello.model.Board.*;
import g43197.othello.model.Color;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * This class displays the plate of the game.
 *
 * @author Philippe
 */
public class Plate extends GridPane {

    public Plate() {
        super();

        //TODO taille plate dynamique
        double size = 1.1 * 500;
        this.setMinSize(size, size);
        this.setMaxSize(size, size);

        Rectangle rectangle;
        Label test;
        Image image;
        size /= MAX_ROWS_COLS;
        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                rectangle = new Rectangle();
                rectangle.minHeight(size);
                rectangle.minWidth(size);
                test = new Label("row: " + row + ", col:" + col);
                this.add(rectangle, col, row);
            }
        }
        setHgap(5);
        setVgap(5);
        //TODO setStyle("--fx-border");
    }

    public void addPiece(int row, int col, Color color) {
        String imageName = "Images";
        imageName += color == Color.BLACK ? "BlackPiece" : "WhitePiece";
        Image image = new Image(getClass().getClassLoader().getResource(imageName).toString());
        Rectangle rect = getShapeByRowCol(row, col);
        rect.setFill(new ImagePattern(image));
    }

    public Rectangle getShapeByRowCol(int row, int col) {
        Rectangle rect = null;
        for (Node node : getChildren()) {
            if (getRowIndex(node) == row && getColumnIndex(node) == col) {
                rect = (Rectangle) node;
                break;
            }
        }
        return rect;
    }
}
