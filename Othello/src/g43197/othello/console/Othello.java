package g43197.othello.console;

import g43197.othello.model.Facade;
import g43197.othello.model.Game;
import g43197.othello.model.util.GameException;
import g43197.othello.model.util.GameState;
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
        Facade game = new Game();
        Display.startGame();
        Command command;
        while (true) {
            command = Read.readCommand();
            switch (command) {
                case SHOW:
                    draw(game, game.getAccessibles());
                    break;
                case PLAY:
                case PASS:
                case WALL:
                    if (!(game.getState() == GameState.FINISHED)) {
                        playTurn(game, command);
                    } else {
                        Display.gameIsFinished();
                    }
                    break;
                case SCORE:
                    Display.scores(game.getPlayers());
                    break;
                case REPLAY:
                    Display.startGame();
                    game.startAgain();
                    break;
                case ABANDON:
                    game.abandon();
                    break;
                case HELP:
                    Display.commands();
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

    /*Plays a turn for the player.*/
    private static void playTurn(Facade game, Command command) {
        boolean played = false;
        while (!played) {
            try {
                switch (command) {
                    case PLAY:
                        if (!game.canPlay()) {
                            Display.cantPlay();
                        } else {
                            game.putPiece(Read.readPos(game.getMaxRowsCols()));
                        }
                        break;
                    case WALL:
                        game.wall(Read.readPos(game.getMaxRowsCols()));
                        break;
                    case PASS:
                        if (game.canPlay()) {
                            Display.cantPass();
                        } else {
                            game.pass();
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Shouldn't call this method with that command!");
                }
                played = true;
            } catch (GameException e) {
                Display.error(e);
            }
        }
    }
}
