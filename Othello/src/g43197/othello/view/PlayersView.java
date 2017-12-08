package g43197.othello.view;

import g43197.othello.model.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * List of players with their score.
 *
 * @author Philippe
 */
class PlayersView extends HBox {

    private final Background selected;
    private final Background notSelected;
    private final List<PlayerView> players;
    private PlayerView currentPlayer;

    /**
     * Creates the list of players with their score.
     *
     * @param d the insets between players node.
     * @param players
     */
    PlayersView(double d, Player... players) {
        super(d);
        this.setAlignment(Pos.CENTER);

        this.players = new ArrayList<>(2);

        Color color;
        for (Player p : players) {
            color = toFxColor(p.getColor());
            this.players.add(new PlayerView(p.getName(), p.getScore(), color, p.getNbWalls()));
        }
        this.players.forEach(p -> setHgrow(p, Priority.ALWAYS));

        selected = new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(8), Insets.EMPTY));
        notSelected = new Background(new BackgroundFill(Color.ORANGERED, new CornerRadii(8), Insets.EMPTY));
        this.players.get(0).setBackground(selected);
        this.players.get(1).setBackground(notSelected);
        currentPlayer = this.players.get(0);

        this.players.forEach(p -> this.getChildren().add(p));
    }

    private void nextPlayer() {
        currentPlayer.setBackground(notSelected);
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
        currentPlayer.setBackground(selected);
    }

    /**
     * Updates the players: currentPlayer and scores.
     *
     * @param name
     * @param players
     */
    void update(g43197.othello.model.util.Color color, List<Player> players, boolean isFinished) {
        if (!isFinished) {
            updatePlayer(toFxColor(color));
        }
        updateScores(players);
    }

    void updatePlayer(Color color) {
        while (!this.currentPlayer.isPlayer(color)) {
            nextPlayer();
        }
    }

    private void updateScores(List<Player> players) {
        Player p;
        for (int i = 0; i < players.size(); i++) {
            p = players.get(i);
            this.players.get(i).update(p.getScore(), p.getNbWalls());
        }
    }

    private Color toFxColor(g43197.othello.model.util.Color color) {
        return color == g43197.othello.model.util.Color.BLACK ? Color.BLACK : Color.WHITE;
    }
}
