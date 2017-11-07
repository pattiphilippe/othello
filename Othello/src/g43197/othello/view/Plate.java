package g43197.othello.view;

import static g43197.othello.model.Board.*;
import g43197.othello.model.Color;
import javafx.scene.Node;
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

        //TODO use pref size
        //TODO taille plate dynamique
        double size = 1 * 500;
        this.setMinSize(size, size);
        this.setMaxSize(size, size);

        Rectangle rectangle;
        size /= MAX_ROWS_COLS;
        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                rectangle = new Rectangle();
                rectangle.minHeight(size);
                rectangle.minWidth(size);
                rectangle.setHeight(size);
                rectangle.setWidth(size);

//                initImage(row, col);
                rectangle.setStroke(javafx.scene.paint.Color.GREEN);
                rectangle.setFill(javafx.scene.paint.Color.PINK);
                this.add(rectangle, col, row);
            }
        }
//        firstPieces();
        //setHgap(5);
        //setVgap(5);
        //TODO setStyle("--fx-border");
    }

    private void firstPieces() {
        addPiece2(3, 3, Color.WHITE);
        addPiece2(3, 4, Color.BLACK);
        addPiece2(4, 3, Color.BLACK);
        addPiece2(4, 4, Color.WHITE);
    }

    public void addPiece(int row, int col, Color color) {
        String imageUrl = "file:";
        switch (color) {
            case BLACK:
                imageUrl += "BlackPiece";
                break;
            case WHITE:
                imageUrl += "WhitePiece";
                break;
        }
        imageUrl += ".png";
        setImage(getShapeByRowCol(row, col), imageUrl);
    }

    private void setImage(Rectangle rect, String url) {
        Image image = new Image(url);
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

    private void initImage(int row, int col) {
        String url = "file:NoPiece.png";
        setImage(getShapeByRowCol(row, col), url);
    }

    private void addPiece2(int i, int i0, Color color) {
        Rectangle rect = getShapeByRowCol(i, i0);
    }
}

class Test {

    public static void main(String[] args) {
        Image image = new Image("file:BlackPiece.png");
        ImagePattern iP = new ImagePattern(image);
        System.out.println(iP.toString());
    }
}
