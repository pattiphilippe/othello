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
        players.initScores();
        updateAccessibles();
    }

    /**
     * Checks if the game is over.
     *
     * @return
     */
    public boolean isFinished() {
        if (hasMovesLeft()) {
            return false;
        } else {
            nextPlayer();
            return !hasMovesLeft();
        }
    }

    /**
     * Gets the score of the current player.
     *
     * @return
     */
    public int getScore() {
        return players.getScore();
    }

    public List<Player> getScores() {
        return players.getScores();
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
                //TODO ? que faire avec catch vide
            }
        }
        return accClone;
    }

    /**
     * Puts a piece on the board.
     *
     * @param pos the given position
     */
    public void putPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        int points = board.put(rack.getPiece(players.getCurrentPlayer()), pos);
        players.modifyScore(points + 1);
        nextPlayer();
        players.modifyScore(-points);
    }

    /**
     * Puts a wall on the board.
     *
     * @param pos the given position
     */
    public void putWall(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        board.putWall(pos);
        nextPlayer();
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void nextPlayer() {
        players.nextPlayer();
        updateAccessibles();
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, players.getCurrentPlayer());
    }

    private boolean hasMovesLeft() {
        return !accessibles.isEmpty();
    }
}
