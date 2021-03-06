package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.GameState;
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
     * Returns the state of the game.
     *
     * @return
     */
    public abstract GameState getState();

    /**
     * If the player is an ai, the methods triggers his turn.
     */
    public abstract void iaPlay();

    /**
     * Returns true if the current player is an ai.
     *
     * @return
     */
    public abstract boolean isAi();

    /**
     * Returns true if the current player can put a piece.
     *
     * @return
     */
    public abstract boolean canPlay();

    /**
     * Returns a list with every player. This lets anyone see the name and
     * score.
     *
     * @return an unmodifiableList, which means that every change is directly
     * seen by anyone who once called this method
     */
    public abstract List<Player> getPlayers();

    /**
     * Gets the current player.
     *
     * @return
     */
    public abstract Player getCurrentPlayer();

    /**
     * Gets the previous player.
     *
     * @return
     */
    public abstract Player getPreviousPlayer();

    /**
     * Returns the winner. If the game isn't finished, returns null.
     *
     * @return
     */
    public abstract Player getWinner();

    /**
     * Returns the historic of the games played.
     *
     * @return an unmodifiableList to make changes automatically
     */
    public abstract List<Move> getHistoric();

    /**
     * Returns the piece on the board in the given position
     *
     * @param pos
     * @return
     */
    public abstract Piece getPiece(Coordinates pos);

    /**
     * Returns the number of rows and columns.
     *
     * @return
     */
    public abstract int getMaxRowsCols();

    /**
     * Returns the number of walls on the board.
     *
     * @return
     */
    public abstract int getNbWalls();

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
     * Puts a wall on the board, or destroys the wall if there was one in the
     * given position.
     *
     * @param pos the given position
     */
    public abstract void wall(Coordinates pos);

    /**
     * Pass the turn of the player, only if he can't play a turn.
     */
    public abstract void pass();

    /**
     * Current player of the game abandons. The victory goes to his opponent.
     */
    public abstract void abandon();
}
