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

        double height = 2 / 3. * OthelloApp.HEIGHT;
        double width = 1 / 2. * OthelloApp.WIDTH;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        
        Tile tile;
        height /= MAX_ROWS_COLS;
        width /= MAX_ROWS_COLS;

        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                tile = new Tile(width, height);
                tile.setMinSize(width, height);
                tile.setMaxSize(width, height);
                this.add(tile, col, row);
            }
        }
        firstPieces();
    }

    private void firstPieces() {
        addPiece(3, 3, Color.WHITE);
        addPiece(3, 4, Color.BLACK);
        addPiece(4, 3, Color.BLACK);
        addPiece(4, 4, Color.WHITE);
    }

    public Tile getTileByRowCol(int row, int col) {
        //TODO check cette méthode, tte les pièces ont une couleur
        Tile tile = null;
        for (Node node : getChildren()) {
            if (getRowIndex(node) == row && getColumnIndex(node) == col) {
                tile = (Tile) node;
                break;
            }
        }
        return tile;
    }

    private void addPiece(int row, int col, Color color) {
        getTileByRowCol(row, col).addPiece(color);
    }

//    public void addPiece2(int row, int col, Color color) {
//        String imageUrl = "file:";
//        switch (color) {
//            case BLACK:
//                imageUrl += "BlackPiece";
//                break;
//            case WHITE:
//                imageUrl += "WhitePiece";
//                break;
//        }
//        imageUrl += ".png";
//        setImage(getShapeByRowCol(row, col), imageUrl);
//    }
//
//    private void setImage(Rectangle rect, String url) {
//        Image image = new Image(url);
//        rect.setFill(new ImagePattern(image));
//    }
//    private void initImage(int row, int col) {
//        String url = "file:NoPiece.png";
//        setImage(getShapeByRowCol(row, col), url);
//    }
}

///////////////////////////////////////////Test class for images//////////////
class Test {

    public static void main(String[] args) {
        Image image = new Image("file:BlackPiece.png");
        ImagePattern iP = new ImagePattern(image);
        System.out.println(iP.toString());
    }
}
