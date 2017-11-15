package g43197.othello.model;

import java.util.List;

/**
 * Facade of the model for Othello.
 *
 * @author G43197
 */
public interface Facade {

    /**
     * Starts a new game.
     */
    public abstract void startAgain();

    /**
     * Checks if the game is over.
     *
     * @return
     */
    public boolean isFinished();

    /**
     * Returns a list with every player and their name and score. This list is
     * only made of clones.
     *
     * @return
     */
    public List<Player> getScores();

    /**
     * Gets the current player.
     *
     * @return
     */
    public Color getCurrentPlayer();

    /**
     * Returns a clone of the board.
     *
     * @return
     */
    public Board getBoard();

    /**
     * Returns a copy of the accessible positions for the current player.
     *
     * @return
     */
    public List<Coordinates> getAccessibles();

    /**
     * Puts a piece on the board.
     *
     * @param pos the given position
     */
    public void putPiece(Coordinates pos);

    /**
     * Puts a wall on the board.
     *
     * @param pos the given position
     */
    public void putWall(Coordinates pos);
}
