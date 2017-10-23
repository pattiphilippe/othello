package g43197.othello.model;

/**
 * This class represents the plate. It is by default a square. The game ONLY works with a pair amount of rows and
 * columns.
 *
 * @author Philippe
 */
public class Board {

    public static final int MAX_ROWS = 8;
    public static final int MAX_COLS = MAX_ROWS;
    private final Piece[][] BOARD;

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

    private void initBoardCenter() {
        //TODO dynamic method in the center
        BOARD[3][3] = new Piece(Color.WHITE);
        BOARD[3][4] = new Piece(Color.BLACK);
        BOARD[4][3] = new Piece(Color.WHITE);
        BOARD[4][4] = new Piece(Color.BLACK);
    }

}
