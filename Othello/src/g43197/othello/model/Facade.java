package g43197.othello.model;

import java.util.List;
import java.util.Observable;

/**
 * Facade of the model for Othello.
 *
 * @author G43197
 */
public abstract class Facade extends Observable {

    /**
     * Starts a new game.
     */
    public abstract void startAgain();

    /**
     * Checks if the game is over.
     *
     * @return
     */
    public abstract boolean isFinished();

    /**
     * Returns a list with every player and their name and score. This list is
     * only made of clones.
     *
     * @return
     */
    public abstract List<Player> getScores();

    /**
     * Gets the current player.
     *
     * @return
     */
    public abstract Color getCurrentPlayer();

    /**
     * Returns the number of walls on the board.
     *
     * @return the number of walls
     */
    public abstract int getNbWalls();

    /**
     * Returns a clone of the board.
     *
     * @return
     */
    public abstract Board getBoard();

    /**
     * Returns a list of the positions that switched colors. The first position
     * is the position were the piece was put. If the list has only one element,
     * it is the position of a wall just put.
     *
     * @return
     */
    public abstract List<Coordinates> getSwitchedPositions();

    /**
     * Returns a copy of the accessible positions for the current player.
     *
     * @return
     */
    public abstract List<Coordinates> getAccessibles();

    /**
     * Puts a piece on the board.
     *
     * @param pos the given position
     */
    public abstract void putPiece(Coordinates pos);

    /**
     * Puts a wall on the board.
     *
     * @param pos the given position
     */
    public abstract void putWall(Coordinates pos);

    /**
     * Pass the turn of the player, only if he can't play a turn.
     */
    public abstract void pass();
}
