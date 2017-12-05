package g43197.othello.model;

import java.util.Objects;

/**
 * This class represents a player. Players are different by their color.
 *
 * @author Philippe
 */
public class Player {

    private final Color COLOR;
    private int score;
    String name;

    /**
     * Creates a player. The color is set to the given color.
     *
     * @param color
     */
    Player(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can't be null!");
        }

        this.COLOR = color;
        String firstLetter = COLOR.name().charAt(0) + "";
        String rest = COLOR.name().substring(1).toLowerCase();
        name = firstLetter.concat(rest);
        initScore();
    }
    //TODO gérer les noms différents

    Player(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player can't be null!");
        }
        this.COLOR = player.COLOR;
        this.name = player.name;
        this.score = player.score;
    }

    /**
     * Initializes the score of the player to 2. Can be called when new game.
     */
    final void initScore() {
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
     * Returns the name of the player.
     *
     * @return
     */
    public String getName() {
        return name;
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
    void modifyScore(int delta) {
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
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.COLOR);
        return hash;
    }
}
