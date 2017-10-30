package g43197.othello;

import g43197.othello.model.*;
import g43197.othello.view.BoardView;
import static g43197.othello.view.BoardView.*;
import static g43197.othello.view.Read.*;
import static g43197.othello.view.Display.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author G43197
 */
public class Othello {

    public static void main(String[] args) {
        BoardView.init();
        Board board = new Board();

        List<Coordinates> poss = new LinkedList<>();
        poss.add(new Coordinates(2, 3));
        poss.add(new Coordinates(2, 2));
        poss.add(new Coordinates(2, 1));
        poss.add(new Coordinates(5, 3));
        poss.add(new Coordinates(6, 3));
        int nbPut = 0;
        Color color;
        draw(board);
        for (Coordinates pos : poss) {
            if (nbPut % 2 == 0) {
                color = Color.BLACK;
            } else {
                color = Color.WHITE;
            }
            board.put(new Piece(color), pos);
            draw(board);
            nbPut++;
        }
        board.put(new Piece(Color.BLACK), new Coordinates(2, 3));

//        Game game = new Game();
//        while (!game.isFinished()) {
//            play(game);
//        }
    }

    /**
     * Plays a turn for the player.
     *
     * @param game
     */
    public static void play(Game game) {
        //TODO afficher qui joue, sait jouer ou pas, ...
        if (game.canPlay()) {
            boolean played = false;
            Coordinates pos;
            while (!played) {
                try {
                    pos = readPos();
                    game.put(pos);
                    played = true;
                } catch (GameException e) {
                    displayError(e.getMessage());
                }
            }
        }
    }
}
