package g43197.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a list of players and a few methods for it.
 *
 * @author Philippe
 */
public class Players {

    private final List<Player> players;
    private Player currentPlayer;

    /**
     * Creates a new list of players.
     */
    public Players() {
        players = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            players.add(new Player(Color.values()[i]));
        }
        currentPlayer = players.get(0);
    }

    /**
     * initializes the score of every player.
     */
    public void initScores() {
        players.forEach((player) -> {
            player.initScore();
        });
    }

    /**
     * Changes the current player to the next one.
     */
    public void nextPlayer() {
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
    }

    /**
     * Returns the color of the current player.
     *
     * @return
     */
    public Color getCurrentPlayer() {
        return currentPlayer.getColor();
    }

    /**
     * Returns the score of the current player.
     *
     * @return
     */
    public int getScore() {
        return currentPlayer.getScore();
    }

    /**
     * Modifies the score of the current player of delta.
     *
     * @param delta
     */
    public void modifyScore(int delta) {
        currentPlayer.modifyScore(delta);
    }

    public List<Player> getScores() {
        List<Player> result = new ArrayList<>(2);
        for (Player p : players) {
            try {
                result.add(p.clone());
            } catch (CloneNotSupportedException e) {

            }
        }
        return result;
    }

}
