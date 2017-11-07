package g43197.othello.view;


import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.PINK;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Philippe
 */
public class Tile extends Pane {

    private final Rectangle rectangle;
    private final Shape shape;

    public Tile(double width, double height) {
        double shapeSizeFact = 2 / 5.;
        shape = new Ellipse(width * shapeSizeFact, height * shapeSizeFact);
        shape.setFill(PINK);
        
        shape.setTranslateX(width / 2);
        shape.setTranslateY(height / 2);

        rectangle = new Rectangle(width, height);
        
        rectangle.setStroke(GREEN);
        rectangle.setFill(PINK);

        this.getChildren().addAll(rectangle, shape);
    }

    public void addPiece(g43197.othello.model.Color color) {
        shape.setFill(getFxColor(color));
    }

    public void switchColor() throws Exception {
        if(shape.getFill() == PINK){
            throw new Exception("No piece added there yet!");
        }
        if(shape.getFill() == BLACK){
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
