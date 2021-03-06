package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.MoveAction;

/**
 *
 * @author Philippe
 */
public class Move {

    private final int id;
    private final String name;
    private final MoveAction action;
    private final Coordinates pos;
    private final int nbTakes;

    Move(int id, String name, MoveAction action, Coordinates pos, int nbTakes) {
        if (action == null || name == null) {
            throw new IllegalArgumentException("Action and name can't be null!");
        } else if (action == MoveAction.PIECE || action == MoveAction.WALL) {
            if (pos == null) {
                throw new IllegalArgumentException("Pos can't be null!");
            }
        }
        this.id = id;
        this.name = name;
        this.action = action;
        this.pos = pos;
        this.nbTakes = nbTakes;
    }

    /**
     * Returns the id.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string describing the action.
     *
     * @return
     */
    public String getActionDescr() {
        return action.toString();
    }

    /**
     * Returns a string describing the position.
     *
     * @return
     */
    public String getPos() {
        if (pos == null) {
            return "-";
        } else {
            return (8 - pos.getROW()) + ", " + (pos.getCOL() + 1);
        }
    }

    /**
     * Returns the number of takes.
     *
     * @return
     */
    public int getNbTakes() {
        return nbTakes;
    }

}
