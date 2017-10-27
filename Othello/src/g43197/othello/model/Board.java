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
public class Board {

    public static final int MAX_ROWS_COLS = 8;
    private final Piece[][] BOARD;
    private List<Coordinates> toCheck;

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
        toCheck = new LinkedList<>();
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

    /**
     * Puts a piece and switches the pieces in consequence of that put. Returns
     * the number of pieces switched.
     *
     * @param piece
     * @param pos
     * @return number of pieces switched
     */
    public int put(Piece piece, Coordinates pos) {
        int nbSwitched = 0;
        if (getPiece(pos) == null) {
            List<Direction> dirs = getDirToSwitch(pos, piece.getColor());
            if (dirs.isEmpty()) {
                throw new GameException("Wrong position, doesn't switch anything!");
            } else {
                BOARD[pos.getROW()][pos.getCOL()] = piece;
                nbSwitched = consequencePut(pos, dirs);
            }
        }
        return nbSwitched;
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
        for (Coordinates pos : toCheck) {
            if (!getDirToSwitch(pos, color).isEmpty()) {
                //TODO optimiser condition : arrêter dès que c'est bon, pas tout vérifier
                //TODO check if useful: doing this simplifies code, but more checks
                accessibles.add(pos);
            }
        }
    }

    ///////////////////////////Private//Methods//////////////////////////////
    /*Puts the first pieces in the center of the board. Initializes toCheck list too.*/
    private void initBoardCenter() {
        //TODO dynamic method in the center
        List<Coordinates> list = new ArrayList<>(4);
        getCenterPos(list);
        int nbPos = 1;
        Color color;
        for (Coordinates pos : list) {
            toCheck.add(pos);
            color = nbPos % 2 == 1 ? Color.BLACK : Color.WHITE;
            BOARD[pos.getROW()][pos.getCOL()] = new Piece(color);
            updateToCheck(pos);
            nbPos++;
        }
    }

    /*Gives in a dynamic way 4 central positions of the board.*/
    private void getCenterPos(List<Coordinates> list) {
        //TODO check colors in center
        int rowcol = MAX_ROWS_COLS / 2 - 1;
        list.add(new Coordinates(rowcol, rowcol));
        list.add(new Coordinates(rowcol, rowcol + 1));
        list.add(new Coordinates(rowcol + 1, rowcol));
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

    /*Gives all the directions where placing a piece of saveColor will switch colors.*/
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
                if (piece != null) {
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
        int nbSwitched = 0;
        for (Direction dir : dirs) {
            nbSwitched += switchColors(pos, dir);
        }
        updateToCheck(pos);
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
            } else {
                swapping = false;
            }
            pos = increment(pos, dir);
        }
        return nbSwitched;
    }

    /*Updates toCheck list*/
    private void updateToCheck(Coordinates pos) {
        toCheck.remove(pos);
        Coordinates adj;
        for (Direction dir : Direction.values()) {
            adj = increment(pos, dir);
            if (adj == null) {
                toCheck.add(adj);
            }
        }
    }
}
