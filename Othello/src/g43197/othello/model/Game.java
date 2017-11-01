package g43197.othello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Facade of Othello. The methods in this class are mainly around the current
 * player, and adapts to the current player every turn.
 *
 * @author g43197
 */
public class Game {

    private final Board board;
    private final Rack rack;
    private final List<Player> players;
    private Player currentPlayer;
    private final List<Coordinates> accessibles;
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
     */
    public Color getCurrentPlayer() {
        return currentPlayer.getColor();
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
        int points = board.put(rack.getPiece(currentPlayer.getColor()), pos);
        currentPlayer.modifyScore(points + 1);
        int score = getScore();
        nextPlayer();
        currentPlayer.modifyScore(-points);
        didPlay = true;
        return score;
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
