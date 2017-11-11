package g43197.othello;

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

    //TODO gérer les exceptions avec Exception et pas runtime Exception
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
        Command command;
        while (!game.isFinished()) {
            command = Read.readCommand();
            switch (command) {
                case SHOW:
                    draw(game.getBoard(), game.getAccessibles());
                    break;
                case PLAY:
                case WALL:
                    playTurn(game, command);
                    break;
                case SCORE:
                    Display.scores(game.getScores());
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

    /*Plays a turn for the player.*/
    private static void playTurn(Game game, Command command) {
        if (game.canPlay()) {
            boolean played = false;
            while (!played) {
                try {
                    //TODO vérifier utilité retour de game.putPiece()
                    switch (command) {
                        case PLAY:
                            game.putPiece(Read.readPos());
                            break;
                        case WALL:
                            game.putWall(Read.readPos());
                            break;
                    }
                    played = true;
                } catch (GameException e) {
                    Display.error(e);
                }
            }
        } else {
            Display.cantPlay();
        }
    }
}
