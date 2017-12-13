package g43197.othello.model;

import g43197.othello.model.util.Color;
import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.Direction;
import g43197.othello.model.util.GameException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the plate. It is by default a square. The game ONLY
 * works with a pair amount of rows and columns.
 *
 * @author Philippe
 */
class Board {

    /**
     * The default number of rows and cols.
     */
    private final int maxRowsCols;
    private final Piece[][] BOARD;
    private final List<Coordinates> switchedPos;
    private int nbWalls;

    /**
     * Creates a new board with Max_rows_cols rows and Max_rows_cols columns.
     * Puts the first pieces of the game.
     */
    Board(int maxRowsCols) {
        this.maxRowsCols = maxRowsCols;
        if (maxRowsCols % 2 != 0) {
            throw new GameException("Amount of rows and columns has to be pair!");
        }
        if (maxRowsCols < 4) {
            throw new GameException("Amount of rows and columns has to be greater or equal to 4!");
        }
        switchedPos = new ArrayList<>();
        nbWalls = 0;

        BOARD = new Piece[maxRowsCols][maxRowsCols];
        initBoardCenter();
    }

    List<Coordinates> getSwitchedPositions() {
        return switchedPos;
    }

    /**
     * Returns the piece in the given position.
     *
     * @param pos
     * @return
     */
    Piece getPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        if (!isInside(pos)) {
            throw new GameException("Pos is outside the board");
        }
        return BOARD[pos.getROW()][pos.getCOL()];
    }
    
    int getNbWalls(){
        return this.nbWalls;
    }

    /**
     * Puts a piece and switches the pieces in consequence of that put. Returns
     * the number of pieces switched.
     *
     * @param piece
     * @param pos
     * @return number of pieces switched
     */
    int put(Piece piece, Coordinates pos) {
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
     * Puts a wall on the board, or destroys the current one in the given
     * position. Also update the walls counter.
     *
     * @param pos
     */
    void wall(Coordinates pos) {
        Piece previous = getPiece(pos);
        if (previous != null && previous.getColor() != Color.WALL) {
            throw new GameException("Position occupied by a piece!");
        } else if (previous == null) {
            BOARD[pos.getROW()][pos.getCOL()] = new Piece(Color.WALL);
            nbWalls++;
        } else {
            BOARD[pos.getROW()][pos.getCOL()] = null;
            //TODO check update pour destroy un mur
            nbWalls--;
        }
        // check isInside ds getPiece()
        switchedPos.clear();
        switchedPos.add(pos);
    }

    /**
     * Clears the list and adds every position that's playable for the given
     * color.
     *
     * @param accessibles
     * @param color
     */
    void updateAccessibles(List<Coordinates> accessibles, Color color) {
        accessibles.clear();
        Coordinates pos;
        for (int row = 0; row < maxRowsCols; row++) {
            for (int col = 0; col < maxRowsCols; col++) {
                pos = new Coordinates(row, col);
                if (getPiece(pos) == null && !getDirToSwitch(pos, color).isEmpty()) {
                    accessibles.add(pos);
                }
            }
        }
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
        int rowcol = maxRowsCols / 2 - 1;
        list.add(new Coordinates(rowcol, rowcol));
        list.add(new Coordinates(rowcol + 1, rowcol));
        list.add(new Coordinates(rowcol, rowcol + 1));
        list.add(new Coordinates(rowcol + 1, rowcol + 1));
    }

    private boolean isInside(Coordinates pos) {
        boolean goodLine = isBetween(pos.getROW(), 0, maxRowsCols - 1);
        boolean goodCol = isBetween(pos.getCOL(), 0, maxRowsCols - 1);
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
            pos = savePos.increment(dir);
            while (isInside(pos)) {
                piece = getPiece(pos);
                if (piece != null && piece.getColor() != Color.WALL) {
                    if (piece.getColor() != saveColor) {
                        hadOtherColors = true;
                        pos = pos.increment(dir);
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
        return dirs.stream().map((dir) -> switchColors(pos, dir)).reduce(0, Integer::sum);
    }

    /*Returns the number of pieces switched.*/
    private int switchColors(Coordinates pos, Direction dir) {
        int nbSwitched = 0;
        Color saveColor = getPiece(pos).getColor();
        pos = pos.increment(dir);
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
            pos = pos.increment(dir);
        }
        return nbSwitched;
    }

    /**
     * Clears the switched positions. Should be called only when a game is
     * abandonned.
     */
    void clearSwitchedPos() {
        switchedPos.clear();
    }
}
