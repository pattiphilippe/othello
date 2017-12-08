package g43197.othello.view;

import g43197.othello.model.Move;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Philippe
 */
class MoveItem {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty action;
    private SimpleStringProperty pos;
    private SimpleIntegerProperty nbTakes;

    MoveItem() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.action = new SimpleStringProperty();
        this.pos = new SimpleStringProperty();
        this.nbTakes = new SimpleIntegerProperty();
    }

    MoveItem(Move move) {
        if (move == null) {
            throw new IllegalArgumentException("Move can't be null!");
        }
        this.id = new SimpleIntegerProperty(move.getId());
        this.name = new SimpleStringProperty(move.getName());
        this.action = new SimpleStringProperty(move.getActionDescr());
        this.pos = new SimpleStringProperty(move.getPos());
        this.nbTakes = new SimpleIntegerProperty(move.getNbTakes());
    }

    int getId() {
        return id.get();
    }

    void setId(int id) {
        this.id.set(id);
    }

    SimpleIntegerProperty idProperty() {
        return id;
    }

    String getName() {
        return name.get();
    }

    void setName(String name) {
        this.name.set(name);
    }

    SimpleStringProperty nameProperty() {
        return name;
    }

    String getAction() {
        return action.get();
    }

    void setAction(String action) {
        this.action.set(action);
    }

    SimpleStringProperty actionProperty() {
        return action;
    }

    String getPos() {
        return pos.get();
    }

    void setPos(String pos) {
        this.pos.set(pos);
    }

    SimpleStringProperty posProperty() {
        return pos;
    }

    int getNbTakes() {
        return nbTakes.get();
    }

    void setNbTakes(int nbTakes) {
        this.nbTakes.set(nbTakes);
    }

    SimpleIntegerProperty nbTakesProperty() {
        return nbTakes;
    }

    void setAll(Move move) {
        setId(move.getId());
        setName(move.getName());
        setAction(move.getActionDescr());
        setPos(move.getPos());
        setNbTakes(move.getNbTakes());
    }
}
