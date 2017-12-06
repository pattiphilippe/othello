package g43197.othello.model;

import g43197.othello.model.util.StratRandom;
import g43197.othello.model.util.Strategy;
import g43197.othello.model.util.Color;

/**
 *
 * @author Philippe
 */
public class AI extends Player implements Strategy {

    private Strategy strategy;

    /**
     * Creates a new player with a default strategy.
     *
     * @param color
     * @param name
     */
    public AI(Color color, String name) {
        this(color, name, new StratRandom());
    }

    /**
     * Creates a new player with a given strategy.
     *
     * @param color
     * @param name
     * @param strategy
     */
    public AI(Color color, String name, Strategy strategy) {
        super(color, name);
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy can't be null!");
        }
        this.strategy = strategy;
    }

    /**
     * Creates a copy of another ai.
     *
     * @param ia
     */
    public AI(AI ia) {
        this(ia.getColor(), ia.getName(), ia.strategy);
    }

    @Override
    public void play(Facade game) {
        strategy.play(game);
    }

    //TODO permit to change strat
    /**
     * Sets the strategy of the current player.
     *
     * @param strategy
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
