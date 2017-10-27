package g43197.othello;

import g43197.othello.model.*;
import g43197.othello.view.BoardView;
import static g43197.othello.view.BoardView.*;
import static g43197.othello.view.Read.*;
import static g43197.othello.view.Display.*;

/**
 *
 * @author G43197
 */
public class Othello {

    public static void main(String[] args) {
        BoardView.init();
        Board board = new Board();
        draw(board);

        Game game = new Game();
        while (!game.isFinished()) {
            play(game);
        }
    }

    /**
     * Plays a turn for the player.
     *
     * @param player
     */
    public static void play(Game game) {
        //TODO afficher qui joue, sait jouer ou pas, ...
        if (game.canPlay()) {
            boolean played = false;
            Coordinates pos;
            while (true) {
                try {
                    pos = readPos();
                    game.put(pos);
                    break;
                } catch (GameException e) {
                    displayError(e.getMessage());
                }
            }
        }
    }
}
