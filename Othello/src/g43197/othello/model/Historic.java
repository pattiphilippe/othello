package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.MoveAction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class Historic {

    private final List<Move> moves;

    Historic() {
        this.moves = new ArrayList<>();
    }

    void add(String name, MoveAction action, Coordinates pos, int nbTakes) {
        moves.add(new Move(moves.size() + 1, name, action, pos, nbTakes));
    }

    void clear() {
        moves.clear();
    }

    List<Move> getHistoric() {
        return moves;
    }
}
