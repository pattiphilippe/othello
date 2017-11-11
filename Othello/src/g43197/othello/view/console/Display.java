package g43197.othello.view.console;

import g43197.othello.model.Color;

/**
 * Class for all the displays in the game.
 *
 * @author Philippe
 */
public class Display {

    /**
     * Displays the message of the error.
     *
     * @param e
     */
    public static void error(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Display that the current player can't play.
     */
    public static void cantPlay() {
        System.out.println("Current player can't play.");
    }

    /**
     * Displays the start of a turn for the current player and his score.
     *
     * @param currentPlayer
     * @param score
     */
    public static void turn(Color currentPlayer, int score) {
        System.out.println("Player: " + currentPlayer);
//        System.out.println("Score: " + score);
    }

    /**
     * Displays the end of a turn for the current player and his new score
     *
     * @param currentPlayer
     * @param newScore
     */
    public static void endTurn(Color currentPlayer, int newScore) {
        turn(currentPlayer, newScore);
        System.out.println("\n\n");
    }

    /**
     * Basic display at the start of a new game.
     */
    public static void startGame() {
        System.out.println("");
        System.out.println("");
        System.out.println("----------New Game of Othello----------");
        System.out.println("");
    }

    public static void scores() {
        System.out.println("SCORRESESESEE FS\n\n\n");
    }
}
