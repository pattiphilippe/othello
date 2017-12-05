package g43197.othello.model.util;

import g43197.othello.model.Facade;

/**
 *
 * @author Philippe
 */
public interface Strategy {
    //TODO use Strategy over IA (instanceof)

    /**
     * Plays a turn in the game.
     *
     * @param game
     */
    public void play(Facade game);
}
