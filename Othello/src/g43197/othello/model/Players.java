package g43197.othello.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is a list of players and a few methods for it.
 *
 * @author Philippe
 */
public class Players implements Iterator<Player> {

    private final List<Player> players;
    private Player currentPlayer;

    /**
     * Creates a new list of players.
     */
    Players() {
        players = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            players.add(new Player(Color.values()[i]));
        }
        currentPlayer = players.get(0);
    }

    /**
     * initializes the score of every player.
     */
    void initScores() {
        players.forEach((player) -> {
            player.initScore();
        });
    }

    /**
     * Returns the color of the current player.
     *
     * @return
     */
    Color getCurrentPlayer() {
        return currentPlayer.getColor();
    }

    /**
     * Returns the score of the current player.
     *
     * @return
     */
    int getScore() {
        return currentPlayer.getScore();
    }

    /**
     * Modifies the score of the current player of delta.
     *
     * @param delta
     */
    void modifyScore(int delta) {
        currentPlayer.modifyScore(delta);
    }

    List<Player> getScores() {
        return players;
    }

    @Override
    public boolean hasNext() {
        return players.size() != (players.indexOf(currentPlayer) + 1);
    }

    @Override
    public Player next() {
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
        return currentPlayer;
    }
}
