package g43197.othello.model;

/**
 *
 * @author Philippe
 */
public interface Strategy {

    /**
     * Plays a turn in the game.
     * @param game
     */
    public void play(Facade game);
}
