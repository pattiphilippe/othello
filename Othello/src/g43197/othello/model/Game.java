package g43197.othello.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Facade of Othello. The methods in this class are mainly around the current
 * player, and adapts to the current player every turn.
 *
 * @author g43197
 */
public class Game {

    private Board board;
    private Rack rack;
    private List<Player> players;
    private Player currentPlayer;
    private List<Coordinates> accessibles;
    private boolean didPlay;

    public Game() {
        board = new Board();
        rack = new Rack();
        players = new ArrayList<>();
        for (Color color : Color.values()) {
            players.add(new Player(color));
        }
        currentPlayer = players.get(0);
        accessibles = new LinkedList<>();
        updateAccessibles();
        didPlay = true;
    }

    /**
     * Checks if the game is over.
     *
     * @return
     */
    public boolean isFinished() {
        return !didPlay && !hasMovesLeft();
    }

    /**
     * Checks if the current player can play. If he can't, it goes directly to
     * the next player.
     *
     * @return
     */
    public boolean canPlay() {
        boolean canPlay = hasMovesLeft();
        if (!canPlay) {
            nextPlayer();
        }
        return canPlay;
    }

    /**
     * Gets the score of the current player.
     *
     * @return
     */
    public int getScore() {
        return currentPlayer.getScore();
    }

    /**
     * Gets the current player.
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    public Player getCurrentPlayer() throws CloneNotSupportedException {
        return currentPlayer.clone();
    }

    /**
     * Puts a piece on the board.
     *
     * @param pos the given position
     */
    public void put(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Piece and pos can't be null!");
        }
        int score = board.put(rack.getPiece(currentPlayer.getColor()), pos);
        currentPlayer.modifyScore(score + 1);
        nextPlayer();
        currentPlayer.modifyScore(-score);
        didPlay = true;
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void nextPlayer() {
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
        // has to go through put() so that didPlay is true;
        didPlay = false;
        updateAccessibles();
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, currentPlayer.getColor());
    }

    private boolean hasMovesLeft() {
        return !accessibles.isEmpty();
    }
}
