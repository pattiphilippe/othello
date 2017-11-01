package g43197.othello.model;

/**
 * This class purpose is to differenciate a code error from a broken game's
 * rule.
 *
 * @author Philippe
 */
public class GameException extends RuntimeException {

    /**
     *
     * @param msg
     */
    public GameException(String msg) {
        super(msg);
    }

}
