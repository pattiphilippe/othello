package g43197.othello.view;

import g43197.othello.model.Coordinates;
import java.util.Scanner;

/**
 *
 * @author g43197
 */
public class Read {

    private static Scanner clavier = new Scanner(System.in);

    public static Coordinates readPos() {
        System.out.println("Enter the position where you wanna put a piece.");
        int row = read(false), col = read(true);
        return new Coordinates(row, col);
    }

    private static int read(boolean readingCol) {
        boolean validInput = false;
        String read;
        int nb = 0;
        while (!validInput) {
            read = clavier.next().charAt(0) + "";
            nb = readingCol ? convertToCol(read) : convertToRow(read);
            if (nb == -1) {
                System.out.print("Wrong input.");
                if (readingCol) {
                    System.out.println(" Enter a letter from A to H.");
                } else {
                    System.out.println("Enter a number from 1 to 8");
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
        return Integer.parseInt(row) - 1;
    }
}
