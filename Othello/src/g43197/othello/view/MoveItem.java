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

    /**
     * Returns the id property value.
     *
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Sets the id property value.
     *
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Return the id property.
     *
     * @return
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Returns the name property value.
     *
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name property value.
     *
     * @param name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Return the name property.
     *
     * @return
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * Returns the action property value.
     *
     * @return
     */
    public String getAction() {
        return action.get();
    }

    /**
     * Sets the action property value.
     *
     * @param action
     */
    public void setAction(String action) {
        this.action.set(action);
    }

    /**
     * Return the action property.
     *
     * @return
     */
    public SimpleStringProperty actionProperty() {
        return action;
    }

    /**
     * Returns the pos property value.
     *
     * @return
     */
    public String getPos() {
        return pos.get();
    }

    /**
     * Sets the pos property value.
     *
     * @param pos
     */
    public void setPos(String pos) {
        this.pos.set(pos);
    }

    /**
     * Return the pos property.
     *
     * @return
     */
    public SimpleStringProperty posProperty() {
        return pos;
    }

    /**
     * Returns the nbTakes property value.
     *
     * @return
     */
    public int getNbTakes() {
        return nbTakes.get();
    }

    /**
     * Sets the nbTakes property value.
     *
     * @param nbTakes
     */
    public void setNbTakes(int nbTakes) {
        this.nbTakes.set(nbTakes);
    }

    /**
     * Return the nbTakes property.
     *
     * @return
     */
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
