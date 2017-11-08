package g43197.othello;

import g43197.othello.model.Color;
import g43197.othello.model.Game;
import g43197.othello.model.GameException;
import static g43197.othello.view.console.BoardView.*;
import g43197.othello.view.console.Display;
import g43197.othello.view.console.Read;

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
        Display.startGame();
        play(game);
        while (Read.startAgain()) {
            Display.startGame();
            game.startAgain();
            play(game);
        }
    }

    /*Plays the game once.*/
    private static void play(Game game) {
        while (!game.isFinished()) {
            playTurn(game);
        }
    }

    /*Plays a turn for the player.*/
    private static void playTurn(Game game) {
        Color currentPlayer = game.getCurrentPlayer();
        Display.turn(currentPlayer, game.getScore());
        draw(game.getBoard(), game.getAccessibles());
        if (game.canPlay()) {
            boolean played = false;
            int newScore = 0;
            while (!played) {
                try {
                    newScore = game.put(Read.readPos());
                    played = true;
                } catch (GameException e) {
                    Display.error(e.getMessage());
                }
            }
            Display.endTurn(currentPlayer, newScore);
        } else {
            Display.cantPlay();
        }
    }
}
