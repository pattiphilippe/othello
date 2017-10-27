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
        initToCheck();
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
    /*Puts the first pieces in the center of the board.*/
    private void initBoardCenter() {
        //TODO dynamic method in the center
        BOARD[3][3] = new Piece(Color.WHITE);
        BOARD[3][4] = new Piece(Color.BLACK);
        BOARD[4][3] = new Piece(Color.BLACK);
        BOARD[4][4] = new Piece(Color.WHITE);
    }

    private boolean isInside(Coordinates pos) {
        boolean goodLine = isBetween(pos.getROW(), 0, MAX_ROWS_COLS - 1);
        boolean goodCol = isBetween(pos.getCOL(), 0, MAX_ROWS_COLS - 1);
        return goodLine && goodCol;
    }

    private boolean isBetween(int nb, int min, int max) {
        return min <= nb && nb <= max;
    }

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
    
    private void updateToCheck(Coordinates pos){
        toCheck.remove(pos);
        Coordinates adj;
        for(Direction dir : Direction.values()){
            adj = increment(pos, dir);
            if(adj == null){
                toCheck.add(adj);
            }
        }
    }
}
