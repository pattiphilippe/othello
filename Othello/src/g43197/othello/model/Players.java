package g43197.othello.model;

import g43197.othello.model.util.Color;
import g43197.othello.model.util.Strategies;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is a list of players and a few methods for it.
 *
 * @author Philippe
 */
class Players implements Iterator<Player> {

    private final List<Player> players;
    private Player currentPlayer;

    Players(String nameP1, Strategies stratP1, String nameP2, Strategies stratP2) {
        players = new ArrayList<>(2);
        Color[] colors = Color.values();
        players.add(new Player(colors[0], nameP1, stratP1));
        players.add(new Player(colors[1], nameP2, stratP2));

        currentPlayer = players.get(0);
    }

    /**
     * initializes the score of every player.
     */
    void init() {
        currentPlayer = players.get(0);
        players.forEach((player) -> {
            player.init();
        });
    }

    /**
     * Returns the current player.
     *
     * @return
     */
    Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the previous player.
     *
     * @return
     */
    Player getPreviousPlayer() {
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            return players.get(i);
        } else {
            return players.get(0);
        }
    }

    /**
     * Returns the player with the greatest score.
     *
     * @return
     */
    Player getWinner() {
        return players.stream().max((Player p1, Player p2) -> p1.getScore() - p2.getScore()).get();
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
     * Modifies the score of the current player of delta and of the other
     * player, and add the number of switched pieces to the counter of the
     * current player.
     *
     * @param delta the number of switched pieces
     */
    void modifyScore(int delta) {
        currentPlayer.modifyScore(delta + 1);
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            players.get(i).modifyScore(-delta);
        } else {
            players.get(0).modifyScore(-delta);
        }
        currentPlayer.addTakes(delta);
    }

    List<Player> getScores() {
        return players;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Player next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next player!");
        }
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
        return currentPlayer;
    }

    Player previous() {
        return next();
    }

    boolean isAi() {
        return currentPlayer.isAI();
    }
}
