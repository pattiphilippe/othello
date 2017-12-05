package g43197.othello.model;


/**
 *
 * @author Philippe
 */
public class IA extends Player implements Strategy {

    //TODO check all visibilities
    private Strategy strategy;

    IA(Color color) {
        this(color, new StratRandom());
    }

    IA(Color color, Strategy strategy) {
        super(color);
        this.name = "IA";
        this.strategy = strategy;
    }

    IA(IA ia) {
        this(ia.getColor(), ia.strategy);
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
    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
