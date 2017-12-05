package g43197.othello.model.util;

/**
 * Possible colors of the pieces.
 *
 * @author Philippe
 */
public enum Color {

    /**
     *
     */
    BLACK("X"),
    /**
     *
     */
    WHITE("O"),
    /**
     * Specific color for a wall.
     */
    WALL("W");

    private final String descr;

    private Color(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return descr;
    }
}
