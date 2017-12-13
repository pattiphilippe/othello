package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.Color;
import g43197.othello.model.util.GameState;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class GameTest {

    /**
     * Normal case of putPiece(), Checks if no error. Score is tested with
     * getScore() method, putPiece is tested in Board.putPiece().
     */
    @Test
    public void testPut() {
        Game game = new Game();
        game.putPiece(new Coordinates(2, 3));
    }

    /**
     * Normal case of putPiece().
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullPos() {
        Game game = new Game();
        game.putPiece(null);
    }

    /**
     * Test when can't play. Also tests method isFinished().
     */
    @Test
    public void testCanPlayFullBoard() {
        Game game = new Game();
        completeBoard(game);
        assertEquals(GameState.FINISHED, game.getState());
    }

    /**
     * Test of initial score.
     */
    @Test
    public void testGetScore() {
        Game game = new Game();
        assertEquals(game.getScore(), 2);
        game.putPiece(new Coordinates(2, 3));
        assertEquals(game.getScore(), 1);
    }

    /**
     * Test of score after a few moves.
     */
    @Test
    public void testGetScoreAfterMoves() {
        Game game = new Game();
        assertEquals(game.getScore(), 2);
        game.putPiece(new Coordinates(2, 3));
        assertEquals(game.getScore(), 1);
        game.putPiece(new Coordinates(2, 2));
        assertEquals(game.getScore(), 3);
        game.putPiece(new Coordinates(2, 1));
        assertEquals(game.getScore(), 2);
        game.putPiece(new Coordinates(5, 3));
        assertEquals(game.getScore(), 4);
        game.putPiece(new Coordinates(6, 3));
        assertEquals(game.getScore(), 1);
    }

    /**
     * Test of getCurrentPlayer method, of class Game, checks if no error.
     */
    @Test
    public void testGetCurrentPlayer() {
        Game game = new Game();
        game.getCurrentPlayer();
    }

    /**
     * Test of nextPlayer method, of class Game.
     */
    @Test
    public void testNextPlayer() {
        Game game = new Game();
        Color color;
        color = game.getCurrentPlayer().getColor();
        game.putPiece(new Coordinates(2, 3));
        assertNotEquals(color, game.getCurrentPlayer().getColor());
        game.putPiece(new Coordinates(2, 2));
        assertEquals(color, game.getCurrentPlayer().getColor());
    }

    /**
     * Test when game started.
     */
    @Test
    public void testIsFinishedStarted() {
        Game game = new Game();
        assertNotEquals(GameState.FINISHED, game.getState());
    }

    /**
     * Test when game is finished.
     */
    @Test
    public void testIsFinishedFinished() {
        Game game = new Game();
        completeBoard(game);
        assertEquals(GameState.FINISHED, game.getState());
    }

    private void completeBoard(Game game) {
        List<Coordinates> poss = new LinkedList<>();
        poss.add(new Coordinates(2, 3));
        poss.add(new Coordinates(2, 2));
        poss.add(new Coordinates(2, 1));
        poss.add(new Coordinates(5, 3));
        poss.add(new Coordinates(6, 3));
        poss.add(new Coordinates(2, 4));
        poss.add(new Coordinates(3, 5));
        poss.add(new Coordinates(2, 0));
        poss.add(new Coordinates(1, 3));
        poss.add(new Coordinates(5, 5));
        poss.add(new Coordinates(5, 4));
        poss.add(new Coordinates(2, 5));
        poss.add(new Coordinates(1, 4));
        poss.add(new Coordinates(0, 4));
        poss.add(new Coordinates(1, 2));
        poss.add(new Coordinates(0, 3));
        poss.add(new Coordinates(0, 2));
        poss.add(new Coordinates(6, 4));
        poss.add(new Coordinates(5, 6));
        poss.add(new Coordinates(7, 3));
        poss.add(new Coordinates(3, 2));
        poss.add(new Coordinates(5, 7));
        poss.add(new Coordinates(7, 4));
        poss.add(new Coordinates(6, 5));
        poss.add(new Coordinates(4, 2));
        poss.add(new Coordinates(7, 5));
        poss.add(new Coordinates(0, 5));
        poss.add(new Coordinates(5, 1));
        poss.add(new Coordinates(5, 2));
        poss.add(new Coordinates(3, 1));
        poss.add(new Coordinates(1, 5));
        poss.add(new Coordinates(4, 5));
        poss.add(new Coordinates(6, 0));
        poss.add(new Coordinates(3, 6));
        poss.add(new Coordinates(4, 6));
        poss.add(new Coordinates(2, 6));
        poss.add(new Coordinates(4, 7));
        poss.add(new Coordinates(3, 7));
        poss.add(new Coordinates(2, 7));
        poss.add(new Coordinates(1, 7));
        poss.add(new Coordinates(1, 6));
        poss.add(new Coordinates(4, 1));
        poss.add(new Coordinates(6, 2));
        poss.add(new Coordinates(0, 7));
        poss.add(new Coordinates(7, 6));
        poss.add(new Coordinates(7, 7));
        poss.add(new Coordinates(6, 7));
        poss.add(new Coordinates(6, 6));
        poss.add(new Coordinates(0, 6));
        poss.add(new Coordinates(0, 1));
        poss.add(new Coordinates(4, 0));
        poss.add(new Coordinates(3, 0));
        poss.add(new Coordinates(5, 0));
        poss.add(new Coordinates(7, 0));
        poss.add(new Coordinates(1, 0));
        poss.add(new Coordinates(0, 0));
        poss.add(new Coordinates(7, 2));
        poss.add(new Coordinates(7, 1));
        poss.add(new Coordinates(1, 1));
        poss.forEach((pos) -> {
            game.putPiece(pos);
        });
        game.putPiece(new Coordinates(6, 1));
    }

    /**
     * Test of wall method, of class Game.
     */
    @Test
    public void testPutWall() {
        Game game = new Game();
        Coordinates pos = new Coordinates(5, 5);
        game.wall(pos);
        assertEquals(game.getPiece(pos).getColor(), Color.WALL);
    }
}
