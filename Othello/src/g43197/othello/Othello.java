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

    //TODO ? gérer les exceptions avec Exception et pas runtime Exception
    //TODO * demander ce qu'est le menu dans la vue javafx
    //TODO * interface pour Game
    //TODO * peut rajouter commande replay
    //TODO ? bon avec clone?
    /**
     * Main method of Othello.
     *
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
        Display.startGame();
        Command command;
        while (true) {
            command = Read.readCommand();
            switch (command) {
                case SHOW:
                    draw(game.getBoard(), game.getAccessibles());
                    break;
                case PLAY:
                case WALL:
                    if (!game.isFinished()) {
                        playTurn(game, command);
                    } else {
                        Display.gameIsFinished();
                    }
                    break;
                case SCORE:
                    Display.scores(game.getScores());
                    break;
                case REPLAY:
                    Display.startGame();
                    game.startAgain();
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

    /*Plays a turn for the player.*/
    private static void playTurn(Game game, Command command) {
        boolean played = false;
        while (!played) {
            try {
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
    }
}
