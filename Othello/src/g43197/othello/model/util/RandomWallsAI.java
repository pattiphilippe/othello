package g43197.othello.model.util;

import g43197.othello.model.Facade;

/**
 *
 * @author Philippe
 */
public class RandomWallsAI implements Strategy {

    @Override
    public void play(Facade game) {
        if (game.canPlay()) {
            boolean put = false;
            Coordinates pos;
            while (!put) {
                pos = new Coordinates(random(8), random(8));
                if (game.getPiece(pos) == null) {
                    game.putWall(pos);
                    put = true;
                }
            }
        } else {
            game.pass();
        }
    }
}
