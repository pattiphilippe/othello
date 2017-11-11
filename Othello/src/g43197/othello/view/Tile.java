package g43197.othello.view;

import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.PINK;
import static javafx.scene.paint.Color.BROWN;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
    private final Shape piece;
    private final Shape wall;
    private static final double shapeSizeFact = 2 / 5.;

    /**
     * Creates a new Tile with the given width and height.
     *
     * @param width
     * @param height
     */
    public Tile(double width, double height) {
        piece = new Ellipse(width * shapeSizeFact, height * shapeSizeFact);
        piece.setFill(BLACK);
        piece.setVisible(false);

        piece.setTranslateX(width / 2);
        piece.setTranslateY(height / 2);

        wall = new Rectangle(width * shapeSizeFact, height * shapeSizeFact);
        wall.setFill(BROWN);
        wall.setVisible(false);

        //TODO make rectangle wall, but unvisible and change visibilities
        // si attribut reçoit nouvelle adresse, pas changement dans children()
        rectangle = new Rectangle(width, height);

        rectangle.setStroke(GREEN);
        rectangle.setFill(PINK);

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
