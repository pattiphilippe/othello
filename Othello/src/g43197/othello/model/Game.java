package g43197.othello.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Facade of Othello. The methods in this class are mainly around the current
 * player, and adapts to the current player every turn.
 *
 * @author g43197
 */
public class Game {

    private final Players players;
    private final List<Coordinates> accessibles;
    private Board board;
    private Rack rack;
    private boolean didPlay;

    /**
     * Creates a new game.
     */
    public Game() {
        players = new Players();
        accessibles = new LinkedList<>();
        startAgain();
    }

    /**
     * Starts a new game.
     */
    public final void startAgain() {
        board = new Board();
        rack = new Rack();
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
        return players.getScore();
    }

    /**
     * Gets the current player.
     *
     * @return
     */
    public Color getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    /**
     * Returns a clone of the board.
     *
     * @return
     */
    public Board getBoard() {
        try {
            return board.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns a copy of the accessible positions for the current player.
     *
     * @return
     */
    public List<Coordinates> getAccessibles() {
        List<Coordinates> accClone = new LinkedList<>();
        for (Coordinates pos : accessibles) {
            try {
                accClone.add(pos.clone());
            } catch (CloneNotSupportedException e) {

            }
        }
        return accClone;
    }

    /**
     * Puts a piece on the board.
     *
     * @param pos the given position
     * @return the score of the player that puts a piece
     */
    public int put(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Piece and pos can't be null!");
        }
        int points = board.put(rack.getPiece(players.getCurrentPlayer()), pos);
        players.modifyScore(points + 1);
        int score = getScore();
        nextPlayer();
        players.modifyScore(-points);
        didPlay = true;
        return score;
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void nextPlayer() {
        players.nextPlayer();
        // has to go through put() so that didPlay is true;
        didPlay = false;
        updateAccessibles();
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, players.getCurrentPlayer());
    }

    private boolean hasMovesLeft() {
        return !accessibles.isEmpty();
    }
}
