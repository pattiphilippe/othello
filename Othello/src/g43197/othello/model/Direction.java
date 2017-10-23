package g43197.othello.model;

/**
 * This enum are directions to move on the board. Each one of them goes 1 tile
 * further in the given direction.
 *
 * @author G43197
 */
public enum Direction {
    UP(1, 0), UP_LEFT(1, 1), LEFT(0, 1), DOWN_LEFT(-1, 1), DOWN(-1, 0),
    DOWN_RIGHT(-1, -1), RIGHT(0, -1), UP_RIGHT(1, -1);

    private final int DELTA_LINE;
    private final int DELTA_COL;

    private Direction(int deltaLine, int deltaCol) {
        this.DELTA_LINE = deltaLine;
        this.DELTA_COL = deltaCol;
    }

    /**
     *
     * @return
     */
    public int getDeltaLine() {
        return DELTA_LINE;
    }

    /**
     *
     * @return
     */
    public int getDeltaCol() {
        return DELTA_COL;
    }

    /**
     * Returns a new position that has moved from origin of 1 tile in given
     * direction.
     *
     * @param origin
     * @param dir
     * @return
     */
    public static Coordinates increment(Coordinates origin, Direction dir) {
        int line = origin.getROW() + dir.getDeltaLine();
        int col = origin.getCOL() + dir.getDeltaCol();
        return new Coordinates(line, col);
    }
}
