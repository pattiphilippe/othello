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

    public AI(Color color, String name) {
        this(color, name, new StratRandom());
    }

    public AI(Color color, String name, Strategy strategy) {
        super(color, name);
        this.strategy = strategy;
    }

    public AI(AI ia) {
        this(ia.getColor(), ia.getName(), ia.strategy);
    }

    @Override
    public void play(Facade game) {
        strategy.play(game);
    }

    //TODO permit to change strat
    /**
     *
     * @param strategy
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
