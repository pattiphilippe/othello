package g43197.othello.view;

import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.SLATEBLUE;
import static javafx.scene.paint.Color.LIGHTBLUE;
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
public class Tile extends StackPane {

    private final Rectangle rectangle;
    private final Shape piece;
    private final Shape wall;
    private static final double SHAPE_SIZE_FACT = 2 / 5.;
    private static final double WALL_SIZE_FACT = 13 / 20.;

    /**
     * Creates a new Tile with the given width and height.
     *
     * @param width
     * @param height
     */
    public Tile(double width, double height) {
        this.setMinWidth(width);
        this.setMinHeight(height);

        piece = new Ellipse(width * SHAPE_SIZE_FACT, height * SHAPE_SIZE_FACT);
        piece.setFill(BLACK);
        piece.setVisible(false);

        wall = new Rectangle(width * WALL_SIZE_FACT, height * WALL_SIZE_FACT);
        wall.setFill(BROWN);
        wall.setVisible(false);

        // si attribut reçoit nouvelle adresse, pas changement dans children()
        rectangle = new Rectangle(width, height);

        rectangle.setStroke(SLATEBLUE);
        rectangle.setFill(LIGHTBLUE);

        this.getChildren().addAll(rectangle, piece, wall);
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
            piece.setVisible(true);
            piece.setFill(getFxColor(color));
        }
    }

    private void addWall() {
        piece.setVisible(false);
        wall.setVisible(true);
    }

    /**
     * Switches the color of the piece on this tile.
     *
     * @throws Exception if no piece added yet.
     */
    public void switchColor() throws Exception {
        if (wall.isVisible() == true) {
            throw new Exception("Can't switch walls color!");
        }
        if (piece.isVisible() == false) {
            throw new Exception("No piece added there yet!");
        }
        if (piece.getFill() == BLACK) {
            piece.setFill(WHITE);
        } else {
            piece.setFill(BLACK);
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