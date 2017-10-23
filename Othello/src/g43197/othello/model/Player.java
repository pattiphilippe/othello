package g43197.othello.model;

/**
 * This class represents a player. Player's are different by their color.
 *
 * @author Philippe
 */
public class Player {

    private final Color COLOR;
    private int score;

    /**
     * Creates a player. The color is set to the given color. Each player starts with half the amount of pieces that you
     * can place on the board.
     *
     * @param color
     */
    public Player(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can't be null!");
        }

        this.COLOR = color;
        this.score = 0;
    }

    /**
     * Returns the color
     *
     * @return
     */
    public Color getColor() {
        return COLOR;
    }

    /**
     * This method adds the number in argument to the current score.
     *
     * @param delta
     */
    public void modifyScore(int delta) {
        score += score;
    }
}
