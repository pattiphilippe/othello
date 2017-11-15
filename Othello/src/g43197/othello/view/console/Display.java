package g43197.othello.view.console;

import g43197.othello.model.Player;
import java.util.List;

/**
 * Class for all the displays in the game.
 *
 * @author Philippe
 */
public class Display {

    /**
     * Displays the message of the error.
     *
     * @param e the exception
     */
    public static void error(Exception e) {
        System.out.println(e.getMessage());
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

    /**
     * Displays the score of every player.
     *
     * @param players the list of player with their name and score
     */
    public static void scores(List<Player> players) {
        System.out.println("Scores:");
        players.forEach((p) -> {
            System.out.println(p.getColor() + ": " + p.getScore());
        });
    }

    /**
     * Displays that can't play because game is finished.
     */
    public static void gameIsFinished() {
        System.out.println("Can't play a turn, game is finished");
    }
}
