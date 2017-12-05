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
        List<Coordinates> accessibles = game.getAccessibles();

        if (!accessibles.isEmpty()) {
            game.putPiece(accessibles.get(random(accessibles.size())));
        } else {
            //TODO check no bug when can't pass, and don't put up finished game
            boolean stop = false;
            for (int i = 0; i < 8 && !stop; i++) {
                for (int j = 0; j < 8 && !stop; j++) {
                    if (game.getPiece(new Coordinates(i, j)) == null) {
                        game.putWall(new Coordinates(i, j));
                        stop = true;
                    }
                }
            }
        }
    }

    private int random(int size) {
        return (int) (Math.random() * size);
    }

}
