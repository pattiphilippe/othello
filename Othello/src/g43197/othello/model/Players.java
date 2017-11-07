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
        for (Color color : Color.values()) {
            players.add(new Player(color));
        }
        currentPlayer = players.get(0);
    }

    /**
     * Initialises the score of every player.
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

    public void getScores() {
        //TODO implement method to have all the score at the end.
    }

    /**
     * Modifies the score of the current player of delta.
     *
     * @param delta
     */
    public void modifyScore(int delta) {
        currentPlayer.modifyScore(delta);
    }

}
