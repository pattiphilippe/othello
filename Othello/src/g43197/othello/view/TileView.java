package g43197.othello.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.SLATEBLUE;
import static javafx.scene.paint.Color.LIGHTBLUE;
import static javafx.scene.paint.Color.BROWN;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * TileView is a class created specially for Board.
 *
 * @author Philippe
 */
class TileView extends StackPane {

    private final Rectangle rectangle;
    private final Circle piece;
    private final Shape wall;
    private static final double SHAPE_SIZE_FACT = 2 / 5.;
    private static final double WALL_SIZE_FACT = 13 / 20.;
    private final Timeline switchColor;

    /**
     * Creates a new Tile with the given width and height.
     *
     * @param width
     * @param height
     */
    TileView(double width, double height) {
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setOnMouseEntered(e -> setEffect(new InnerShadow(5, BLACK)));
        this.setOnMouseExited(e -> setEffect(null));

        double radiusCircle = height * SHAPE_SIZE_FACT;
        piece = new Circle(radiusCircle);
        piece.setFill(BLACK);

        wall = new Rectangle(width * WALL_SIZE_FACT, height * WALL_SIZE_FACT);
        wall.setFill(BROWN);

        // si attribut reçoit nouvelle adresse, pas changement dans children()
        rectangle = new Rectangle(width, height);
        rectangle.setStroke(SLATEBLUE);
        rectangle.setFill(LIGHTBLUE);
        rectangle.setEffect(null);
        initTile();

        this.getChildren().addAll(rectangle, piece, wall);

        //Timeline
        final KeyFrame switchColorPart1 = new KeyFrame(new Duration(OthelloApp.TURN_TIME / 8), e -> {
            if (BLACK == piece.getFill()) {
                piece.setFill(WHITE);
            } else {
                piece.setFill(BLACK);
            }
        }, new KeyValue(piece.radiusProperty(), 0));
        final KeyFrame switchColorPart2 = new KeyFrame(new Duration((OthelloApp.TURN_TIME / 4)),
                new KeyValue(piece.radiusProperty(), radiusCircle));
        switchColor = new Timeline(switchColorPart1, switchColorPart2);
    }

    /**
     * Puts the tile to its init state, with nothing visible in it. Doesn't
     * change its visibility.
     */
    void init() {
        initTile();
    }

    /**
     * Adds a piece on the tile.
     *
     * @param color
     */
    void addPiece(g43197.othello.model.util.Color color) {
        switch (color) {
            case WALL:
                addWall();
                break;
            default:
                piece.setVisible(true);
                piece.setFill(getFxColor(color));
                break;
        }
    }

    /**
     * Switches the color of the piece on this tile.
     *
     */
    void switchColor() {
        if (wall.isVisible() == true) {
            throw new RuntimeException("Can't switch walls color!");
        }
        if (piece.isVisible() == false) {
            throw new RuntimeException("No piece added there yet!");
        }
        switchColor.play();
    }

    /**
     * Displays that this tile is accessible if accessible boolean is true. If
     * it's false, displays the tile normally.
     *
     * @param accessible
     */
    void setAccessible(boolean accessible) {
        if (accessible) {
            rectangle.setFill(Color.CHARTREUSE);
            rectangle.setEffect(new Glow(0.6));
        } else {
            rectangle.setFill(LIGHTBLUE);
            rectangle.setEffect(null);
        }
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void initTile() {
        piece.setVisible(false);
        wall.setVisible(false);
    }

    private Paint getFxColor(g43197.othello.model.util.Color color) {
        switch (color) {
            case BLACK:
                return BLACK;
            case WHITE:
                return WHITE;
            default:
                throw new RuntimeException("wrong color!");
        }
    }

    private void addWall() {
        piece.setVisible(false);
        wall.setVisible(true);
    }
}
