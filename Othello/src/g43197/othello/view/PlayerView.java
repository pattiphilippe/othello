package g43197.othello.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * This class displays one player with his name and score.
 *
 * @author Philippe
 */
class PlayerView extends GridPane {

    private final Label name;
    private final StackPane piece;
    private final Color color;
    private final Label score;
//    private final Label nbWalls;
    private final Label nbTakes;

    /**
     * Creates a new player with the given name.
     *
     * @param name player's name
     * @param score first score of the player
     * @param color color of the piece
     * @param nbTakes number of takes
     */
    PlayerView(String name, int score, Color color, int nbTakes) {
        super();
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setEffect(new DropShadow());
        ColumnConstraints colGrow = new ColumnConstraints();
        colGrow.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(colGrow, colGrow, colGrow);

        Label nameLbl = new Label("Name");
        nameLbl.setFont(Font.font(15));
        nameLbl.setTextFill(Color.BLUE);
        this.add(nameLbl, 0, 0);

        Label pieceLbl = new Label("Piece");
        pieceLbl.setFont(Font.font(15));
        pieceLbl.setTextFill(Color.BLUE);
        this.add(pieceLbl, 1, 0);

        Label scoreLbl = new Label("Score");
        scoreLbl.setFont(Font.font(15));
        scoreLbl.setTextFill(Color.BLUE);
        this.add(scoreLbl, 2, 0);

//        Label nbWallsLbl = new Label("Walls");
//        nbWallsLbl.setFont(Font.font(15));
//        nbWallsLbl.setTextFill(Color.BLUE);
//        this.add(nbWallsLbl, 3, 0);
        Label nbTakesLbl = new Label("Takes");
        nbTakesLbl.setFont(Font.font(15));
        nbTakesLbl.setTextFill(Color.BLUE);
        this.add(nbTakesLbl, 3, 0);

        this.name = new Label(name);
        this.add(this.name, 0, 1);

        piece = new StackPane();
        this.color = color;
        Circle c = new Circle(8, this.color);
        piece.getChildren().add(c);
        StackPane.setAlignment(c, Pos.CENTER);
        this.add(piece, 1, 1);

        this.score = new Label(score + "");
        this.add(this.score, 2, 1);
        //        this.nbWalls = new Label(nbWalls + "");
        //        this.add(this.nbWalls, 3, 1);

        this.nbTakes = new Label(nbTakes + "");
        this.add(this.nbTakes, 3, 1);

        getChildren().forEach((child) -> GridPane.setHalignment(child, HPos.CENTER));
    }

    /**
     * Returns true if the given name is for this player.
     *
     * @param name
     * @return
     */
    boolean isPlayer(Color color) {
        return this.color == color;
    }

    /**
     * Updates the view for the score.
     *
     * @param score
     * @param nbTakes
     */
    void update(int score, int nbTakes) {
        this.score.setText(score + "");
//        this.nbWalls.setText(nbWalls + "");
        this.nbTakes.setText(nbTakes + "");
    }
}
