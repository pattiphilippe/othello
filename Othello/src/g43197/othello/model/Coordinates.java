package g43197.othello.model;

/**
 * Simple coordinate representation with a row and a column. Can take every
 * integer value.
 *
 * @author G43197
 */
public class Coordinates {

    private final int ROW;
    private final int COL;

    /**
     *
     * @param row
     * @param col
     */
    public Coordinates(int row, int col) {
        this.ROW = row;
        this.COL = col;
    }

    /**
     * Creates a copy of the given position.
     *
     * @param pos
     */
    public Coordinates(Coordinates pos) {
        this.ROW = pos.ROW;
        this.COL = pos.COL;
    }

    /**
     *
     * @return
     */
    public int getROW() {
        return ROW;
    }

    /**
     *
     * @return
     */
    public int getCOL() {
        return COL;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinates other = (Coordinates) obj;
        if (this.ROW != other.ROW) {
            return false;
        }
        if (this.COL != other.COL) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.ROW;
        hash = 19 * hash + this.COL;
        return hash;
    }

    /**
     * Returns a new position that has moved from this position of 1 tile in
     * given direction.
     *
     * @param dir
     * @return the new Coordinate
     */
    public Coordinates increment(Direction dir) {
        int row = this.ROW + dir.getDeltaRow();
        int col = this.COL + dir.getDeltaCol();
        return new Coordinates(row, col);
    }

}
