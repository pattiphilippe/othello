package g43197.othello.model;

import g43197.othello.model.util.Color;
import g43197.othello.model.util.GameException;
import g43197.othello.model.util.Human;
import g43197.othello.model.util.RandomAI;
import g43197.othello.model.util.RandomWallsAI;
import g43197.othello.model.util.Strategies;
import g43197.othello.model.util.Strategy;
import java.util.Locale;
import java.util.Objects;

/**
 * This class represents a player. Players are different by their color.
 *
 * @author Philippe
 */
public class Player implements Strategy {

    private final Color COLOR;
    private int score;
    private String name;
    private Strategy strategy;
    private int nbTakes;

    /**
     * Creates a new human player. This constructor is mainly there for retro
     * compatibility, codes before the ai was added.
     *
     * @param color
     * @param name
     */
    public Player(Color color, String name) {
        this(color, name, Strategies.HUMAN);
    }

    /**
     * Creates a player. The color is set to the given color.
     *
     * @param color
     * @param name
     * @param strategy
     */
    public Player(Color color, String name, Strategies strategy) {
        if (color == null || name == null || strategy == null) {
            throw new IllegalArgumentException("Parametres can't be null!");
        }

        this.COLOR = color;
        this.score = 2;
        this.nbTakes = 0;
        if (name.equals("")) {
            String firstLetter = COLOR.name().charAt(0) + "";
            String rest = COLOR.name().substring(1).toLowerCase(Locale.ENGLISH);
            this.name = firstLetter.concat(rest);
            this.name += strategy != Strategies.HUMAN ? " AI" : "";
        } else {
            this.name = name;
        }
        selectStrategy(strategy);
    }

    private void selectStrategy(Strategies strategy) {
        switch (strategy) {
            case HUMAN:
                this.strategy = new Human();
                break;
            case AI_RANDOM_WALLS:
                this.strategy = new RandomWallsAI();
                break;
            case AI_RANDOM:
            default:
                this.strategy = new RandomAI();
                break;
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
    }

    /**
     * Initializes the score of the player to 2. Can be called when new game.
     */
    public void init() {
        this.score = 2;
        //TODO corriger bug historique
        this.nbTakes = 0;
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
     * Returns the number of pieces that this player has switched since the
     * start of the game.
     *
     * @return
     */
    public int getNbTakes() {
        return nbTakes;
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
        return this.COLOR == other.COLOR;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.COLOR);
        return hash;
    }

    @Override
    public void play(Facade game) {
        this.strategy.play(game);
    }

    /**
     * Returns true if this player has an ai strategy.
     */
    boolean isAI() {
        return !(strategy instanceof Human);
    }

    /**
     * Adds an amount of takes to the counter of this player.
     *
     * @param delta nb of takes to add
     */
    void addTakes(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Delta can't be negative!");
        }
        this.nbTakes += delta;
    }
}
