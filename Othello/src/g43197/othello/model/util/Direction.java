package g43197.othello.model.util;

/**
 * This enum are directions to move on the board. Each one of them goes 1 tile
 * further in the given direction.
 *
 * @author G43197
 */
public enum Direction {

    /**
     *
     */
    UP(-1, 0),
    /**
     *
     */
    UP_RIGHT(-1, 1),
    /**
     *
     */
    RIGHT(0, 1),
    /**
     *
     */
    DOWN_RIGHT(1, 1),
    /**
     *
     */
    DOWN(1, 0),
    /**
     *
     */
    DOWN_LEFT(1, -1),
    /**
     *
     */
    LEFT(0, -1),
    /**
     *
     */
    UP_LEFT(-1, -1);

    private final int DELTA_ROW;
    private final int DELTA_COL;

    private Direction(int deltaRow, int deltaCol) {
        this.DELTA_ROW = deltaRow;
        this.DELTA_COL = deltaCol;
    }

    /**
     * Returns the row delta to apply to move of 1 tile in the selected
     * direction.
     *
     * @return
     */
    public int getDeltaRow() {
        return DELTA_ROW;
    }

    /**
     * Returns the column delta to apply to move of 1 tile in the selected
     * direction.
     *
     * @return
     */
    public int getDeltaCol() {
        return DELTA_COL;
    }
}
