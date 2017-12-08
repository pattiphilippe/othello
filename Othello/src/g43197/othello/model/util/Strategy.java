package g43197.othello.model.util;

import g43197.othello.model.Facade;
import java.util.Random;

/**
 *
 * @author Philippe
 */
public interface Strategy {

    /**
     * Random generator that can be useful for Strategies.
     */
    final Random RANDOM_GENERATOR = new Random();

    /**
     * Plays a turn in the game.
     *
     * @param game
     */
    public void play(Facade game);

    /**
     * Returns a random number between 0 and the given maximum.
     *
     * @param max
     * @return an integer between 0 and max
     */
    default int random(int max) {
        return RANDOM_GENERATOR.nextInt(max);
    }
}
