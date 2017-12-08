package g43197.othello.view.console;

import g43197.othello.console.Command;
import g43197.othello.model.util.Coordinates;
import java.util.Arrays;
import java.util.Locale;
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
            msg = CLAVIER.next().toUpperCase(Locale.ENGLISH);
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
     * @param maxRowsCols
     * @return
     */
    public static Coordinates readPos(int maxRowsCols) {
        int row = readRowCol(maxRowsCols, false), col = readRowCol(maxRowsCols, true);
        return new Coordinates(row, col);
    }

    ////////////////////////////PRIVATE//METHODS/////////////////////////////
    private static int readRowCol(int maxRowsCols, boolean readingCol) {
        boolean validInput = false;
        String read;
        int nb = 0;
        while (!validInput) {
            read = CLAVIER.next().charAt(0) + "";
            nb = readingCol ? convertToCol(read, maxRowsCols) : convertToRow(read, maxRowsCols);
            if (nb == -1) {
                System.out.print("Wrong input.");
                if (readingCol) {
                    System.out.println("Enter a letter for the column.");
                } else {
                    System.out.println("Enter a number from 1 to " + maxRowsCols + " for the row.");
                }
            } else {
                validInput = true;
            }
        }
        return nb;
    }

    private static int convertToCol(String colChar, int maxRowsCols) {
        int col = colChar.toUpperCase(Locale.ENGLISH).charAt(0) - 65;
        if (col < 0 || maxRowsCols < col) {
            return -1;
        } else {
            return col;
        }
    }

    private static int convertToRow(String row, int maxRowsCols) {
        int rowNb;
        try {
            rowNb = Integer.parseInt(row);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (rowNb < 1 || maxRowsCols < rowNb) {
            return -1;
        }
        return maxRowsCols - rowNb;
    }
}
