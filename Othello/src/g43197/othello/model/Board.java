package g43197.othello.model;

import static g43197.othello.model.Direction.increment;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the plate. It is by default a square. The game ONLY
 * works with a pair amount of rows and columns.
 *
 * @author Philippe
 */
public class Board {

    public static final int MAX_ROWS_COLS = 8;
    private final Piece[][] BOARD;

    /**
     * Creates a new board with Max_rows_cols rows and Max_rows_cols columns.
     * Puts the first pieces of the game.
     */
    public Board() {
        if (MAX_ROWS_COLS % 2 != 0) {
            throw new GameException("Amount of rows and columns has to be pair!");
        }
        if (MAX_ROWS_COLS < 4) {
            throw new GameException("Amount of rows and columns has to be greater or equal to 4!");
        }

        BOARD = new Piece[MAX_ROWS_COLS][MAX_ROWS_COLS];
        initBoardCenter();
    }

    /**
     * Returns the piece in the given position.
     *
     * @param pos
     * @return
     */
    public Piece getPiece(Coordinates pos) {
        if (!isInside(pos)) {
            throw new GameException("Pos is outside the board");
        }
        return BOARD[pos.getROW()][pos.getCOL()];
    }

    public void put(Piece piece, Coordinates pos) {
        if (canPut(piece, pos)) {
            int line = pos.getROW(), col = pos.getCOL();
            BOARD[line][col] = piece;
        }
        //TODO call consequencePut
    }

    private boolean canPut(Piece piece, Coordinates pos) {
        for (Direction dir : Direction.values()) {

        }
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
    /*Puts the first pieces in the center of the board.*/
    private void initBoardCenter() {
        //TODO dynamic method in the center
        this.put(new Piece(Color.WHITE), new Coordinates(3, 3));
        this.put(new Piece(Color.BLACK), new Coordinates(3, 4));
        this.put(new Piece(Color.BLACK), new Coordinates(4, 3));
        this.put(new Piece(Color.WHITE), new Coordinates(4, 4));
    }

    private boolean isInside(Coordinates pos) {
        boolean goodLine = isBetween(pos.getROW(), 0, MAX_ROWS_COLS - 1);
        boolean goodCol = isBetween(pos.getCOL(), 0, MAX_ROWS_COLS - 1);
        return goodLine && goodCol;
    }

    private boolean isBetween(int nb, int min, int max) {
        return min <= nb && nb <= max;
    }

    private List<Direction> getDirToSwitch(Coordinates pos) {
        List<Direction> dirs = new ArrayList<>();
        Color saveColor = getPiece(pos).getColor();
        Piece piece;
        boolean hadOtherColors;
        for (Direction dir : Direction.values()) {
            hadOtherColors = false;
            pos = increment(pos, dir);
            while (isInside(pos)) {
                piece = getPiece(pos);
                if (piece != null) {
                    if (piece.getColor() != saveColor) {
                        hadOtherColors = true;
                        pos = increment(pos, dir);
                    } else if (hadOtherColors) {
                        dirs.add(dir);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return dirs;
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
