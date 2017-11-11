package g43197.othello.model;

/**
 * This class represents a player. Players are different by their color.
 *
 * @author Philippe
 */
public class Player implements Cloneable {

    private final Color COLOR;
    private int score;

    /**
     * Creates a player. The color is set to the given color.
     *
     * @param color
     */
    public Player(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can't be null!");
        }

        this.COLOR = color;
        initScore();
    }

    /**
     * Initializes the score of the player to 2. Can be called when new game.
     */
    public final void initScore() {
        this.score = 2;
    }

    /**
     * Returns the color.
     *
     * @return
     */
    public Color getColor() {
        return COLOR;
    }

    /**
     * Returns the score.
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * This method adds the number in argument to the current score.
     *
     * @param delta
     */
    public void modifyScore(int delta) {
        if (score < -delta) {
            throw new GameException("You're badly in the sh*t,"
                    + " the score is negative!");
        }
        score += delta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.COLOR != other.COLOR) {
            return false;
        }
        return true;
    }

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

}
