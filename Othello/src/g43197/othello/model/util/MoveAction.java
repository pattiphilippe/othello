package g43197.othello.model.util;

/**
 * All the possible actions that a player can do.
 *
 * @author Philippe
 */
public enum MoveAction {
    NEW_GAME("New game"), PIECE("Puts a piece"), WALL("Puts a wall"), PASS("Passes"), ABANDON("Abandons");

    private final String descr;

    private MoveAction(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return descr;
    }
}
