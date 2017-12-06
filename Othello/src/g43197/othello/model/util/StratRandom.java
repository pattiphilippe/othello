package g43197.othello.model.util;

import g43197.othello.model.Facade;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class StratRandom implements Strategy {

    @Override
    public void play(Facade game) {
        if (game.canPlay()) {
            List<Coordinates> accessibles = game.getAccessibles();
            game.putPiece(accessibles.get(random(accessibles.size())));
        } else {
            boolean put = false;
            Coordinates pos;
            while (!put) {
                pos = new Coordinates(random(8), random(8));
                if (game.getPiece(pos) == null) {
                    game.putWall(pos);
                    put = true;
                }
            }
        }
    }

    private int random(int size) {
        return (int) (Math.random() * size);
    }

}
