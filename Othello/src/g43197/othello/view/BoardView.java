package g43197.othello.view;

import g43197.othello.model.*;
import static g43197.othello.model.Board.*;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class BoardView {

    private static final int ROWS_DISPLAY = 1 + 2 * MAX_ROWS_COLS;
    private static final int COLS_DISPLAY = 1 + 4 * MAX_ROWS_COLS;

    private static final String[][] CLEAR_SCREEN = new String[ROWS_DISPLAY][COLS_DISPLAY];
    private static String[][] screen;

    public static void draw(Board board) {
        Piece piece;
        String str = "";

        screen = CLEAR_SCREEN.clone();

        for (int i = 0; i < MAX_ROWS_COLS; i++) {
            for (int j = 0; j < MAX_ROWS_COLS; j++) {
                Coordinates coord = new Coordinates(i, j);
                piece = board.getPiece(coord);

                str = " ";
                screen[1 + i * 2][1 + j * 4] = str;
                screen[1 + i * 2][3 + j * 4] = str;
                if (piece == null) {
                    screen[1 + i * 2][2 + j * 4] = str;
                } else {
                    str = piece.getColor().toString();
                    screen[1 + i * 2][2 + j * 4] = str;
                }
            }
        }

        //showBoard
        System.out.println("     A   B   C   D   E   F   G   H");
        for (int i = 0; i < screen.length; i++) { // print array
            numberLine(i);
            for (String item : screen[i]) {
                System.out.print(item);
            }
            numberLine(i);
            System.out.println();
        }
        System.out.println("     A   B   C   D   E   F   G   H");
    }

    /**
     * fills the CLEAR_SCREEN array according to the size of the board
     */
    public static void init() {
        screen = new String[ROWS_DISPLAY][COLS_DISPLAY];
        for (int i = 0; i < COLS_DISPLAY; i++) {
            fillLine(0, i, "╔", "═", "╦", "╗");
        }
        for (int i = 1; i < ROWS_DISPLAY - 1; i++) {
            for (int j = 0; j < COLS_DISPLAY; j++) {
                if (i % 2 == 0) {
                    fillLine(i, j, "╠", "═", "╬", "╣");
                } else {
                    fillLine(i, j, "║", " ", "║", "║");
                }
            }
        }
        for (int i = 0; i < COLS_DISPLAY; i++) {
            fillLine(ROWS_DISPLAY - 1, i, "╚", "═", "╩", "╝");
        }
    }

    private static void fillLine(int i, int j, String a, String b, String c, String d) { //String dans l'ordre d'apparition
        String str = "";
        if (j == 0) {
            str = a;
        } else if (j == COLS_DISPLAY - 1) {
            str = d;
        } else {
            if (j % 4 == 0) {
                str = c;
            } else {
                str = b;
            }
        }
        CLEAR_SCREEN[i][j] = str;
    }

    /**
     * prints the number of the line or just spaces
     *
     * @param i
     */
    private static void numberLine(int i) {
        if (i % 2 == 1 && i >= 1) {
            System.out.print(" " + (ROWS_DISPLAY / 2 - i / 2) + " ");
        } else {
            System.out.print("   ");
        }
    }
}
