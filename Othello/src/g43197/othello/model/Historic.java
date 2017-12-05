package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.MoveAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Philippe
 */
public class Historic {

    private ObservableList<Move> moves;

    Historic() {
        this.moves = FXCollections.observableArrayList();
    }

    void add(String name, MoveAction action, Coordinates pos, int nbTakes) {
        moves.add(new Move(moves.size() + 1, name, action, pos, nbTakes));
    }

    void clear() {
        moves.clear();
    }

    ObservableList<Move> getHistoric() {
        return moves;
    }
}
