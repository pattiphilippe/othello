package g43197.othello.model;

import static g43197.othello.model.Direction.increment;
import java.util.List;

/**
 * This class represents the plate. It is by default a square. The game ONLY
 * works with a pair amount of rows and columns.
 *
 * @author Philippe
 */
public class Board {

    public static final int MAX_ROWS = 8;
    public static final int MAX_COLS = MAX_ROWS;
    private final Piece[][] BOARD;

    /**
     * Creates a new board with Max_rows rows and Max_cols columns.
     * Puts the first pieces of the game.
     */
    public Board() {
        if (MAX_ROWS % 2 != 0 || MAX_COLS % 2 != 0) {
            throw new GameException("Amount of rows and columns has to be pair!");
        }
        if (MAX_ROWS < 4 || MAX_COLS < 4) {
            throw new GameException("Amount of rows and columns has to be greater or equal to 4!");
        }

        BOARD = new Piece[MAX_ROWS][MAX_COLS];
        initBoardCenter();
    }

    public void put(Piece piece, Coordinates pos) {
        if (piece == null || pos == null) {
            throw new IllegalArgumentException("Piece and pos can't be null!");
        }
        if (!isInside(pos)) {
            throw new GameException("Pos is outside the board");
        }
        int line = pos.getROW(), col = pos.getCOL();
        if (BOARD[line][col] != null) {
            throw new GameException("Pos already occupied!");
        }
        BOARD[line][col] = piece;
    }

    /**
     * Switches the pieces in consequence of a put piece.
     *
     * @param pos
     * @return the number of pieces switched
     */
    public int consequencePut(Coordinates pos) {
        if (getPiece(pos) == null) {
            throw new GameException("Pos can't be null!");
        }
        List<Direction> dirs = getDirToSwitch(pos);
        if (dirs.isEmpty()) {
            throw new GameException("No directions from this position to "
                    + "swap colors!");
        }
        int nbSwitched = 0;
        for (Direction dir : dirs) {
            nbSwitched += switchColors(pos, dir);
        }
        return nbSwitched;
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private Piece getPiece(Coordinates pos) {
        if (!isInside(pos)) {
            throw new GameException("Pos is outside the board");
        }
        return BOARD[pos.getROW()][pos.getCOL()];
    }

    /*Puts the first pieces in the center of the board.*/
    private void initBoardCenter() {
        //TODO dynamic method in the center
        this.put(new Piece(Color.WHITE), new Coordinates(3, 3));
        this.put(new Piece(Color.BLACK), new Coordinates(3, 4));
        this.put(new Piece(Color.WHITE), new Coordinates(4, 3));
        this.put(new Piece(Color.BLACK), new Coordinates(4, 4));
    }

    private boolean isInside(Coordinates pos) {
        boolean goodLine = isBetween(pos.getROW(), 0, MAX_ROWS - 1);
        boolean goodCol = isBetween(pos.getCOL(), 0, MAX_COLS - 1);
        return goodLine && goodCol;
    }

    private boolean isBetween(int nb, int min, int max) {
        return min <= nb && nb <= max;
    }

    private List<Direction> getDirToSwitch(Coordinates pos) {
        //TODO implement method
        return null;
    }

    /*Returns the number of pieces switched.*/
    private int switchColors(Coordinates pos, Direction dir) {
        int nbSwitched = 0;
        Color saveColor = getPiece(pos).getColor();
        pos = increment(pos, dir);
        Piece piece;
        boolean swapping = true;
        while (isInside(pos) && swapping) {
            piece = getPiece(pos);
            if (piece != null && piece.getColor() != saveColor) {
                piece.switchColor();
                nbSwitched++;
            } else {
                swapping = false;
            }
            pos = increment(pos, dir);
        }
        return nbSwitched;
    }

}
