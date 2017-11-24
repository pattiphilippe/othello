package g43197.othello.model;

import static g43197.othello.model.Direction.increment;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the plate. It is by default a square. The game ONLY
 * works with a pair amount of rows and columns.
 *
 * @author Philippe
 */
public class Board implements Cloneable {

    /**
     * The default number of rows and cols.
     */
    public static final int MAX_ROWS_COLS = 8;
    private final Piece[][] BOARD;
    private final List<Coordinates> switchedPos;

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
        switchedPos = new LinkedList<>();

        BOARD = new Piece[MAX_ROWS_COLS][MAX_ROWS_COLS];
        initBoardCenter();
    }

    public List<Coordinates> getSwitchedPositions() {
        //TODO check if needs to be copied or not
        return switchedPos;
    }

    /**
     * Returns the piece in the given position.
     *
     * @param pos
     * @return
     */
    public Piece getPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        if (!isInside(pos)) {
            throw new GameException("Pos is outside the board");
        }
        return BOARD[pos.getROW()][pos.getCOL()];
    }

    /**
     * Puts a piece and switches the pieces in consequence of that put. Returns
     * the number of pieces switched.
     *
     * @param piece
     * @param pos
     * @return number of pieces switched
     */
    public int put(Piece piece, Coordinates pos) {
        if (getPiece(pos) != null) {
            throw new GameException("Position occupied!");
        }
        if (piece.getColor() == Color.WALL) {
            throw new IllegalArgumentException("This method is not for walls.");
        }
        List<Direction> dirs = getDirToSwitch(pos, piece.getColor());
        if (dirs.isEmpty()) {
            throw new GameException("Wrong position, doesn't switch anything!");
        }
        BOARD[pos.getROW()][pos.getCOL()] = piece;
        return consequencePut(pos, dirs);
    }

    /**
     * Puts a wall on the board.
     *
     * @param pos
     */
    public void putWall(Coordinates pos) {
        if (getPiece(pos) != null) {
            throw new GameException("Position occupied!");
        }
        // check isInside ds getPiece()
        switchedPos.clear();
        switchedPos.add(pos);
        BOARD[pos.getROW()][pos.getCOL()] = new Piece(Color.WALL);
    }

    /**
     * Clears the list and adds every position that's playable for the given
     * color.
     *
     * @param accessibles
     * @param color
     */
    public void updateAccessibles(List<Coordinates> accessibles, Color color) {
        accessibles.clear();
        Coordinates pos;
        for (int row = 0; row < MAX_ROWS_COLS; row++) {
            for (int col = 0; col < MAX_ROWS_COLS; col++) {
                pos = new Coordinates(row, col);
                if (getPiece(pos) == null && !getDirToSwitch(pos, color).isEmpty()) {
                    accessibles.add(pos);
                }
            }
        }
    }

    @Override
    public Board clone() throws CloneNotSupportedException {
        return (Board) super.clone();
    }

    ///////////////////////////Private//Methods//////////////////////////////
    /*Puts the first pieces in the center of the board. Initializes switchedPos list too.*/
    private void initBoardCenter() {
        getCenterPos(switchedPos);
        Color color;
        for (Coordinates pos : switchedPos) {
            color = (pos.getROW() + pos.getCOL()) % 2 == 0 ? Color.WHITE : Color.BLACK;
            BOARD[pos.getROW()][pos.getCOL()] = new Piece(color);
        }
    }

    /*Gives in a dynamic way 4 central positions of the board.*/
    private void getCenterPos(List<Coordinates> list) {
        int rowcol = MAX_ROWS_COLS / 2 - 1;
        list.add(new Coordinates(rowcol, rowcol));
        list.add(new Coordinates(rowcol + 1, rowcol));
        list.add(new Coordinates(rowcol, rowcol + 1));
        list.add(new Coordinates(rowcol + 1, rowcol + 1));
    }

    private boolean isInside(Coordinates pos) {
        boolean goodLine = isBetween(pos.getROW(), 0, MAX_ROWS_COLS - 1);
        boolean goodCol = isBetween(pos.getCOL(), 0, MAX_ROWS_COLS - 1);
        return goodLine && goodCol;
    }

    private boolean isBetween(int nb, int min, int max) {
        return min <= nb && nb <= max;
    }

    /*Gives all the directions where placing a piece of saveColor would switch colors.*/
    private List<Direction> getDirToSwitch(Coordinates pos, Color saveColor) {
        List<Direction> dirs = new ArrayList<>();
        Coordinates savePos = pos;
        Piece piece;
        boolean hadOtherColors;
        for (Direction dir : Direction.values()) {
            hadOtherColors = false;
            pos = increment(savePos, dir);
            while (isInside(pos)) {
                piece = getPiece(pos);
                if (piece != null && piece.getColor() != Color.WALL) {
                    if (piece.getColor() != saveColor) {
                        hadOtherColors = true;
                        pos = increment(pos, dir);
                    } else if (hadOtherColors) {
                        dirs.add(dir);
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return dirs;
    }

    /*Switch colors in given directions and updates toCheck list.*/
    private int consequencePut(Coordinates pos, List<Direction> dirs) {
        switchedPos.clear();
        switchedPos.add(pos);
        int nbSwitched = 0;
        for (Direction dir : dirs) {
            nbSwitched += switchColors(pos, dir);
        }
        return nbSwitched;
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
                switchedPos.add(pos);
            } else {
                swapping = false;
            }
            pos = increment(pos, dir);
        }
        return nbSwitched;
    }
}
