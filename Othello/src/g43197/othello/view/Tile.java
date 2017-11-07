package g43197.othello.view;

import g43197.othello.model.Color;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Philippe
 */
public class Tile extends Pane {

    private Rectangle rectangle;
    private Shape shape;

    public Tile(double width, double height) {
        double shapeSizeFact = 2 / 5.;
        shape = new Ellipse(width * shapeSizeFact, height * shapeSizeFact);
        shape.setFill(javafx.scene.paint.Color.PINK);
        
        shape.setTranslateX(width / 2);
        shape.setTranslateY(height / 2);

        rectangle = new Rectangle(width, height);
        
        rectangle.setStroke(javafx.scene.paint.Color.GREEN);
        rectangle.setFill(javafx.scene.paint.Color.PINK);

        this.getChildren().addAll(rectangle, shape);
    }

    public void addPiece(Color color) {
        shape.setFill(getFxColor(color));
    }

    public void switchColor() {
        //TODO voir si peut switch quand pas encore couleur
        if(shape.getFill() == javafx.scene.paint.Color.BLACK){
            shape.setFill(javafx.scene.paint.Color.WHITE);
        } else {
            shape.setFill(javafx.scene.paint.Color.BLACK);
        }
    }

    private Paint getFxColor(Color color) {
        switch (color) {
            case BLACK:
                return javafx.scene.paint.Color.BLACK;
            case WHITE:
                return javafx.scene.paint.Color.WHITE;
            default:
                throw new RuntimeException("wrong color!");
        }
    }

}
