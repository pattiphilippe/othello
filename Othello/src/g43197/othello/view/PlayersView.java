package g43197.othello.view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * List of players with their score.
 *
 * @author Philippe
 */
public class PlayersView extends VBox {

    private final Border border;
    private final List<PlayerView> players;
    private PlayerView currentPlayer;

    /**
     * Creates the list of players with their score.
     *
     * @param d the insets between players node.
     * @param score first score of the players
     * @param names array of players name, at least 2 names
     */
    public PlayersView(double d, int score, String... names) {
        super(d);

        double borderSize = 5;
        border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED,
                new CornerRadii(borderSize), new BorderWidths(borderSize)));

        players = new ArrayList<>(2);

        players.add(new PlayerView(names[0], score));
        players.add(new PlayerView(names[1], score));

        players.get(0).setBorder(border);
        players.get(1).setBorder(Border.EMPTY);

        currentPlayer = players.get(0);

        players.forEach(p -> this.getChildren().add(p));
    }

    private void nextPlayer() {
        currentPlayer.setBorder(Border.EMPTY);
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
        currentPlayer.setBorder(border);
    }

    /**
     * Updates the players: currentPlayer and scores.
     *
     * @param currentPlayer
     * @param scores
     */
    public void update(g43197.othello.model.Color currentPlayer,
            List<g43197.othello.model.Player> scores) {
        updatePlayer(currentPlayer);
        updateScores(scores);
    }

    private void updatePlayer(g43197.othello.model.Color currentPlayer) {
        if (!this.currentPlayer.isPlayer(currentPlayer.toString())) {
            nextPlayer();
        }
    }

    private void updateScores(List<g43197.othello.model.Player> scores) {
        for (int i = 0; i < scores.size(); i++) {
            players.get(i).updateScore(scores.get(i).getScore());
        }
    }
}
