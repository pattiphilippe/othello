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
    private final Label score;

    /**
     * Creates a new player with the given name.
     *
     * @param name player's name
     * @param score first score of the player
     * @param color color of the piece
     */
    PlayerView(String name, int score, Color color) {
        super();
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setEffect(new DropShadow());
        ColumnConstraints colGrow = new ColumnConstraints();
        colGrow.setHgrow(Priority.ALWAYS);
        //TODO demander comment faire: 1 constraint pour toutes les colonnes
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

        this.name = new Label(name);
        this.add(this.name, 0, 1);

        piece = new StackPane();
        Circle c = new Circle(8, color);
        piece.getChildren().add(c);
        StackPane.setAlignment(c, Pos.CENTER);
        this.add(piece, 1, 1);

        this.score = new Label(score + "");
        this.add(this.score, 2, 1);

        getChildren().forEach((child) -> GridPane.setHalignment(child, HPos.CENTER));
    }

    /**
     * Returns true if the given name is for this player.
     *
     * @param name
     * @return
     */
    boolean isPlayer(String name) {
        return this.name.getText().equalsIgnoreCase(name);
    }

    /**
     * Updates the view for the score.
     *
     * @param score
     */
    void updateScore(Integer score) {
        this.score.setText(score + "");
    }
}
