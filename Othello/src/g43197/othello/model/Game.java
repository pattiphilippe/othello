package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.MoveAction;
import g43197.othello.model.util.Strategy;
import g43197.othello.model.util.GameException;
import g43197.othello.model.util.GameState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade of Othello. The methods in this class are mainly around the current
 * player, and adapts to the current player every turn.
 *
 * @author g43197
 */
public class Game extends Facade {

    private static final int MAX_ROWS_COLS = 8;
    private final Players players;
    private final List<Coordinates> accessibles;
    private Board board;
    private final Historic historic;
    private GameState gameState;

    /**
     * Creates a new game.
     */
    public Game() {
        this(false, false);
    }

    /**
     * Creates a new game.
     *
     * @param ai1 true if the first player is an ai.
     * @param ai2 true if the second player is an ai.
     */
    public Game(boolean ai1, boolean ai2) {
        players = new Players(ai1, ai2);
        accessibles = new ArrayList<>();
        historic = new Historic();
        initGame();
    }

    @Override
    public void startAgain() {
        initGame();
        setChanged();
        notifyObservers();
    }

    @Override
    public GameState getState() {
        return gameState;
    }

    @Override
    public boolean canPlay() {
        return !accessibles.isEmpty();
    }

    /**
     * Gets the score of the current player.
     *
     * @return
     */
    public int getScore() {
        return players.getScore();
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players.getScores());
    }

    @Override
    public Player getCurrentPlayer() {
        Player p = players.getCurrentPlayer();
        if (p instanceof AI) {
            return new AI((AI) p);
        }
        return new Player(p);
    }

    @Override
    public Player getPreviousPlayer() {
        Player p = players.getPreviousPlayer();
        if (p instanceof AI) {
            return new AI((AI) p);
        }
        return new Player(p);
    }

    @Override
    public Player getWinner() {
        if (gameState == GameState.FINISHED) {
            return new Player(players.getWinner());
        } else {
            return null;
        }
    }

    @Override
    public Piece getPiece(Coordinates pos) {
        if (board.getPiece(pos) == null) {
            return null;
        }
        return new Piece(board.getPiece(pos));
    }

    @Override
    public int getMaxRowsCols() {
        return MAX_ROWS_COLS;
    }

    @Override
    public List<Coordinates> getAccessibles() {
        return accessibles.stream()
                .map(pos -> new Coordinates(pos))
                .collect(Collectors.toList());
    }

    @Override
    public List<Coordinates> getSwitchedPositions() {
        // Be careful, the next code line links it to the real list, instantly updated
        return Collections.unmodifiableList(board.getSwitchedPositions());
    }

    @Override
    public List<Move> getHistoric() {
        return Collections.unmodifiableList(historic.getHistoric());
    }

    @Override
    public void putPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        int points = board.put(new Piece(players.getCurrentPlayer().getColor()), pos);
        players.modifyScore(points);
        historic.add(players.getCurrentPlayer().getName(), MoveAction.PIECE, pos, points);
        setChanged();
        nextPlayer();
    }

    @Override
    public void putWall(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        board.putWall(pos);
        players.getCurrentPlayer().addWall();
        historic.add(players.getCurrentPlayer().getName(), MoveAction.WALL, pos, 0);
        setChanged();
        nextPlayer();
    }

    @Override
    public void pass() {
        if (canPlay()) {
            throw new GameException("Can't pass, can play a turn.");
        }
        historic.add(players.getCurrentPlayer().getName(), MoveAction.PASS, null, 0);
        setChanged();
        nextPlayer();
    }
    
    @Override
    public boolean isAi() {
        return players.isAi();
    }

    @Override
    public void iaPlay() {
        if (isAi() && gameState != GameState.FINISHED) {
            Strategy ai = (Strategy) players.getCurrentPlayer();
            ai.play(this);
        }
    }

    @Override
    public void abandon() {
        historic.add(players.getCurrentPlayer().getName(), MoveAction.ABANDON, null, 0);
        gameState = GameState.FINISHED;
        setChanged();
        notifyObservers();
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void initGame() {
        board = new Board(MAX_ROWS_COLS);
        gameState = GameState.JUST_STARTED;
        players.init();
        historic.add(players.getCurrentPlayer().getName(), MoveAction.NEW_GAME, null, 0);
        updateAccessibles();
    }

    private void nextPlayer() {
        players.next();
        updateAccessibles();
        updateState();
        notifyObservers();
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, players.getCurrentPlayer().getColor());
    }

    private void updateState() {
        if (!canPlay()) {
            players.next();
            updateAccessibles();
            if (!canPlay()) {
                gameState = GameState.FINISHED;
            } else {
                gameState = GameState.PLAYING;
            }
            players.previous();
            updateAccessibles();
        } else {
            gameState = GameState.PLAYING;
        }
    }
}
