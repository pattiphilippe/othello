package g43197.othello.view.console;

import g43197.othello.Command;
import g43197.othello.model.Coordinates;
import static g43197.othello.model.Game.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author g43197
 */
public class Read {

    private static final Scanner CLAVIER = new Scanner(System.in);

    /**
     * Reads a valid command.
     *
     * @return the read command
     */
    public static Command readCommand() {
        String msg;
        while (true) {
            System.out.print("Command : ");
            msg = CLAVIER.next().toUpperCase();
            try {
                return Command.valueOf(msg);
            } catch (IllegalArgumentException e) {
                System.out.print("Wrond command! Enter one of following:");
                System.out.println(Arrays.toString(Command.values()));
            }
        }
    }

    /**
     * Reads a position that is on the board.
     *
     * @return
     */
    public static Coordinates readPos() {
        int row = readRowCol(false), col = readRowCol(true);
        return new Coordinates(row, col);
    }

    ////////////////////////////PRIVATE//METHODS/////////////////////////////
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

    private static int convertToCol(String colChar) {
        int col = colChar.toUpperCase().charAt(0) - 65;
        if (col < 0 || col >= MAX_ROWS_COLS) {
            return -1;
        } else {
            return col;
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
}
