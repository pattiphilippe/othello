package g43197.othello;

import g43197.othello.model.*;
import g43197.othello.view.BoardView;
import static g43197.othello.view.BoardView.*;

/**
 *
 * @author G43197
 */
public class Othello {

    public static void main(String[] args) {
        BoardView.init();
        Board board = new Board();
        draw(board);
        board.put(new Piece(Color.BLACK), new Coordinates(0, 0));
        draw(board);
    }
}
