package g43197.othello.view.console;

import g43197.othello.model.Color;

/**
 * Class for all the displays in the game.
 *
 * @author Philippe
 */
public class Display {

    /**
     *
     * @param errorMsg
     */
    public static void error(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Displays the end of the game.
     */
    public static void endGame() {
        //TODO implement scores
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
        System.out.println("Score: " + score);
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
}
