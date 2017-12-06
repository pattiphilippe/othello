package g43197.othello.view;

import g43197.othello.model.Move;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Philippe
 */
public class MoveItem {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty action;
    private SimpleStringProperty pos;
    private SimpleIntegerProperty nbTakes;

    public MoveItem() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.action = new SimpleStringProperty();
        this.pos = new SimpleStringProperty();
        this.nbTakes = new SimpleIntegerProperty();
    }

    public MoveItem(Move move) {
        if (move == null) {
            throw new IllegalArgumentException("Move can't be null!");
        }
        this.id = new SimpleIntegerProperty(move.getId());
        this.name = new SimpleStringProperty(move.getName());
        this.action = new SimpleStringProperty(move.getActionDescr());
        this.pos = new SimpleStringProperty(move.getPos());
        this.nbTakes = new SimpleIntegerProperty(move.getNbTakes());
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getAction() {
        return action.get();
    }

    public void setAction(String action) {
        this.action.set(action);
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public String getPos() {
        return pos.get();
    }

    public void setPos(String pos) {
        this.pos.set(pos);
    }

    public SimpleStringProperty posProperty() {
        return pos;
    }

    public int getNbTakes() {
        return nbTakes.get();
    }

    public void setNbTakes(int nbTakes) {
        this.nbTakes.set(nbTakes);
    }

    public SimpleIntegerProperty nbTakesProperty() {
        return nbTakes;
    }

    public void setAll(Move move) {
        setId(move.getId());
        setName(move.getName());
        setAction(move.getActionDescr());
        setPos(move.getPos());
        setNbTakes(move.getNbTakes());
    }
}
