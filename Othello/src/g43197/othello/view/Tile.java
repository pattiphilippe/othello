package g43197.othello.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.PINK;
import static javafx.scene.paint.Color.BROWN;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Tile is a class created specially for Board.
 *
 * @author Philippe
 */
public class Tile extends Pane {

    private final Rectangle rectangle;
    private final Shape shape;
    private static final double shapeSizeFact = 2 / 5.;

    /**
     * Creates a new Tile with the given width and height.
     *
     * @param width
     * @param height
     */
    public Tile(double width, double height) {
        shape = new Ellipse(width * shapeSizeFact, height * shapeSizeFact);
        shape.setFill(PINK);

        shape.setTranslateX(width / 2);
        shape.setTranslateY(height / 2);

        rectangle = new Rectangle(width, height);

        rectangle.setStroke(GREEN);
        rectangle.setFill(PINK);

        this.getChildren().addAll(rectangle, shape);
    }

    /**
     * Adds a piece on the tile.
     *
     * @param color
     */
    public void addPiece(g43197.othello.model.Color color) {
        if (color == g43197.othello.model.Color.WALL) {
            addWall();
        } else {
            shape.setFill(getFxColor(color));
        }
    }


    private void addWall() {
        shape.setFill(BROWN);
        shape.setStroke(BROWN);
    }

    /**
     * Switches the color of the piece on this tile.
     *
     * @throws Exception if no piece added yet.
     */
    public void switchColor() throws Exception {
        if (shape.getFill() == PINK) {
            throw new Exception("No piece added there yet!");
        }
        if(shape.getFill() == BROWN){
            throw new Exception("Can't switch walls color!");
        }
        if (shape.getFill() == BLACK) {
            shape.setFill(WHITE);
        } else {
            shape.setFill(BLACK);
        }
    }

    private Paint getFxColor(g43197.othello.model.Color color) {
        switch (color) {
            case BLACK:
                return BLACK;
            case WHITE:
                return WHITE;
            default:
                throw new RuntimeException("wrong color!");
        }
    }
}
