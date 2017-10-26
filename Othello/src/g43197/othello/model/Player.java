package g43197.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player. Players are different by their color.
 *
 * @author Philippe
 */
public class Player {

    private final Color COLOR;
    private int score;
    private List<Coordinates> accessibles;

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
        this.accessibles = new ArrayList<>();
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
        if(score < delta){
            throw new GameException("You're badly in the sh*t,"
                    + " the score is negative!");
        }
        score += delta;
    }
    
    public List<Coordinates> getAccessibles(){
        return accessibles;
    }
    
    public boolean hasMovesLeft(){
        return accessibles.isEmpty();
    }
}
