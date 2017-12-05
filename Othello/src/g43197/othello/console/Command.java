package g43197.othello.console;

/**
 * Enum of every possible commands in console game.
 *
 * @author Philippe
 */
public enum Command {

    /**
     * Show the board.
     */
    SHOW,
    /**
     * Shows the players and their score.
     */
    SCORE,
    /**
     * Puts a piece for the current player with a given position.
     */
    PLAY,
    /**
     * Passes the turn of the current player if possible.
     */
    PASS,
    /**
     * Puts a wall for the current player in the given position.
     */
    WALL,
    /**
     * Starts a new game.
     */
    REPLAY,
    /**
     * Abandons the game for the current player. The game is then finished and
     * the winner is the other player.
     */
    ABANDON,
    /**
     * Exits the game.
     */
    EXIT,
    /**
     * Shows the possible commands.
     */
    HELP;
}
