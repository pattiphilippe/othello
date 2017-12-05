package g43197.othello.view.console;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.Facade;
import g43197.othello.model.Piece;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class BoardView {

    private static int rowsDisplay;
    private static int colsDisplay;

    private static String[][] clearScreen;
    private static String[][] screen;

    private static boolean init = false;

    /**
     * Draws the board with the pieces on it and the accessible positions given
     * in param.
     *
     * @param game
     * @param accessibles
     */
    public static void draw(Facade game, List<Coordinates> accessibles) {
        if (!init) {
            init(game);
            init = true;
        }

        screen = clearScreen.clone();

        fillPieces(game);
        fillAccessibles(accessibles);
        showBoard();
    }

    /*Fills the screen with the pieces on the board.*/
    private static void fillPieces(Facade game) {
        Piece piece;
        String str;

        for (int i = 0; i < game.getMaxRowsCols(); i++) {
            for (int j = 0; j < game.getMaxRowsCols(); j++) {
                Coordinates coord = new Coordinates(i, j);
                piece = game.getPiece(coord);

                str = " ";
                screen[1 + i * 2][1 + j * 4] = str;
                screen[1 + i * 2][3 + j * 4] = str;
                if (piece != null) {
                    str = piece.getColor().toString();
                }
                screen[1 + i * 2][2 + j * 4] = str;
            }
        }
    }

    private static void fillAccessibles(List<Coordinates> accessibles) {
        accessibles.forEach(pos -> screen[1 + pos.getROW() * 2][2 + pos.getCOL() * 4] = "?");
    }

    /*Prints the board on standard output.*/
    private static void showBoard() {
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
    private static void init(Facade game) {
        rowsDisplay = 1 + 2 * game.getMaxRowsCols();
        colsDisplay = 1 + 4 * game.getMaxRowsCols();
        clearScreen = new String[rowsDisplay][colsDisplay];
        screen = new String[rowsDisplay][colsDisplay];

        for (int i = 0; i < colsDisplay; i++) {
            fillLine(0, i, "╔", "═", "╦", "╗");
        }
        for (int i = 1; i < rowsDisplay - 1; i++) {
            for (int j = 0; j < colsDisplay; j++) {
                if (i % 2 == 0) {
                    fillLine(i, j, "╠", "═", "╬", "╣");
                } else {
                    fillLine(i, j, "║", " ", "║", "║");
                }
            }
        }
        for (int i = 0; i < colsDisplay; i++) {
            fillLine(rowsDisplay - 1, i, "╚", "═", "╩", "╝");
        }
    }

    private static void fillLine(int i, int j, String a, String b, String c, String d) { //String dans l'ordre d'apparition
        String str;
        if (j == 0) {
            str = a;
        } else if (j == colsDisplay - 1) {
            str = d;
        } else {
            if (j % 4 == 0) {
                str = c;
            } else {
                str = b;
            }
        }
        clearScreen[i][j] = str;
    }

    /**
     * prints the number of the line or just spaces
     *
     * @param i
     */
    private static void numberLine(int i) {
        if (i >= 1 && i % 2 == 1) {
            System.out.print(" " + (rowsDisplay / 2 - i / 2) + " ");
        } else {
            System.out.print("   ");
        }
    }
}
