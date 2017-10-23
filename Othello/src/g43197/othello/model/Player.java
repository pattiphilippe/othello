package g43197.othello.model;

/**
 * This class represents a player. Players are different by their color.
 *
 * @author Philippe
 */
public class Player {

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
        this.score = 0;
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
        score += delta;
        if(score < 0){
            throw new GameException("You're badly in the sh*t,"
                    + " the score is negative!");
        }
    }
}