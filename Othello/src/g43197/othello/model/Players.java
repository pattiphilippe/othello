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
//TODO check iterator   

    private final List<Player> players;
    private Player currentPlayer;

    /**
     * Creates a new list of players.
     */
    Players() {
        this(false, false);
    }

    Players(boolean ia1, boolean ia2) {
        players = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            if ((i == 0 && ia1) || (i == 1 && ia2)) {
                players.add(new IA(Color.values()[i]));
            } else {
                players.add(new Player(Color.values()[i]));
            }
        }
        currentPlayer = players.get(0);
    }

    /**
     * initializes the score of every player.
     */
    void init() {
        currentPlayer = players.get(0);
        players.forEach((player) -> {
            player.initScore();
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
     * player.
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
