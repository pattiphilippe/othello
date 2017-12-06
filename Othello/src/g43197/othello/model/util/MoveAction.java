package g43197.othello.model.util;

/**
 * All the possible actions that a player can do.
 *
 * @author Philippe
 */
public enum MoveAction {

    /**
     * Action of creating a new Game.
     */
    NEW_GAME("New game"),
    /**
     * Action of putting a piece on the board.
     */
    PIECE("Puts a piece"),
    /**
     * Action of putting a wall on the board.
     */
    WALL("Puts a wall"),
    /**
     * Action of passing his turn.
     */
    PASS("Passes"),
    /**
     * Action of forfeiting the game.
     */
    ABANDON("Abandons");

    private final String descr;

    private MoveAction(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return descr;
    }
}
