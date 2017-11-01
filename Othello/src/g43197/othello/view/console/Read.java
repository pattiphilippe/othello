package g43197.othello.view.console;

import g43197.othello.model.Coordinates;
import static g43197.othello.model.Board.*;
import java.util.Scanner;

/**
 *
 * @author g43197
 */
public class Read {

    private static final Scanner CLAVIER = new Scanner(System.in);

    public static boolean startAgain() {
        System.out.println("Do you want to start a new game?");
        return readBoolean();
    }

    /**
     * Reads a position that is on the board.
     *
     * @return
     */
    public static Coordinates readPos() {
        System.out.println("Enter the position where you wanna put a piece.");
        int row = readRowCol(false), col = readRowCol(true);
        return new Coordinates(row, col);
    }

    private static int readRowCol(boolean readingCol) {
        boolean validInput = false;
        String read;
        int nb = 0;
        while (!validInput) {
            read = CLAVIER.next().charAt(0) + "";
            nb = readingCol ? convertToCol(read) : convertToRow(read);
            if (nb == -1) {
                System.out.print("Wrong input.");
                if (readingCol) {
                    System.out.println("Enter a letter from A to H for the column.");
                } else {
                    System.out.println("Enter a number from 1 to 8 for the row.");
                }
            } else {
                validInput = true;
            }
        }
        return nb;
    }

    private static int convertToCol(String col) {
        char colChar = col.toUpperCase().charAt(0);
        switch (colChar) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            default:
                return -1;
        }
    }

    private static int convertToRow(String row) {
        int rowNb = 0;
        try {
            rowNb = Integer.parseInt(row);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (rowNb < 1 || 8 < rowNb) {
            return -1;
        }
        return MAX_ROWS_COLS - rowNb;
    }

    private static boolean readBoolean() {
        boolean didAnswer = false, answer = false;
        String msg;
        while (!didAnswer) {
            msg = CLAVIER.next().toUpperCase();
            switch (msg) {
                case "YES":
                    answer = true;
                    didAnswer = true;
                    break;
                case "NO":
                    answer = false;
                    didAnswer = true;
                    break;
                default:
                    System.out.println("Enter yes or no.");
            }
        }
        return answer;
    }
}
