package g43197.othello.model;

import g43197.othello.model.util.Color;
import g43197.othello.model.util.GameException;
import java.util.Objects;

/**
 * This class represents a player. Players are different by their color.
 *
 * @author Philippe
 */
public class Player {

    private final Color COLOR;
    private int score;
    private String name;
    private int nbWalls;

    /**
     * Creates a player. The color is set to the given color.
     *
     * @param color
     * @param name
     */
    public Player(Color color, String name) {
        if (color == null || name == null) {
            throw new IllegalArgumentException("Color can't be null!");
        }

        this.COLOR = color;
        this.score = 2;
        this.nbWalls = 0;
        if (name.equals("")) {
            String firstLetter = COLOR.name().charAt(0) + "";
            String rest = COLOR.name().substring(1).toLowerCase();
            this.name = firstLetter.concat(rest);
        } else {
            this.name = name;
        }
    }

    /**
     * Creates a new copy of the given player.
     *
     * @param player
     */
    public Player(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player can't be null!");
        }
        this.COLOR = player.COLOR;
        this.name = player.name;
        this.score = player.score;
        this.nbWalls = player.nbWalls;
    }

    /**
     * Initializes the score of the player to 2. Can be called when new game.
     */
    public void init() {
        this.score = 2;
        this.nbWalls = 0;
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

    public int getNbWalls() {
        return nbWalls;
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

    void addWall() {
        nbWalls++;
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
        return this.COLOR == other.COLOR;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.COLOR);
        return hash;
    }
}
