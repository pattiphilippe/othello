package g43197.othello.model;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActionDescr() {
        return action.toString();
    }

    public String getPos() {
        if (pos == null) {
            return "-";
        } else {
            return pos.toString();
        }
    }

    public int getNbTakes() {
        return nbTakes;
    }

}
