package g43197.othello;

import g43197.othello.model.Board;
import g43197.othello.view.BoardView;

/**
 *
 * @author G43197
 */
public class Othello {

    public static void main(String[] args) {
        BoardView.init();
        Board board = new Board();
        BoardView.draw(board);
    }
}
