package g43197.othello;

import g43197.othello.model.Game;
import g43197.othello.model.GameException;
import static g43197.othello.view.BoardView.*;
import static g43197.othello.view.Read.*;
import g43197.othello.view.Display;

/**
 * Main class of project Othello.
 *
 * @author G43197
 */
public class Othello {

    /**
     * Main method of Othello.
     *
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
        while (!game.isFinished()) {
            play(game);
        }
        //TODO afficher fin du jeu
        Display.endGame();
    }

    /**
     * Plays a turn for the player.
     *
     * @param game
     */
    public static void play(Game game) {
        draw(game.getBoard(), game.getAccessibles());
        //TODO afficher qui joue et son score, sait jouer ou pas,
        if (game.canPlay()) {
            boolean played = false;
            while (!played) {
                try {
                    game.put(readPos());
                    played = true;
                } catch (GameException e) {
                    Display.error(e.getMessage());
                }
            }
        } else {
            Display.cantPlay();
        }
    }
}
