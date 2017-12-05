package g43197.othello.model;

import g43197.othello.model.util.Coordinates;
import g43197.othello.model.util.MoveAction;
import g43197.othello.model.util.Strategy;
import g43197.othello.model.util.GameException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;

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
    private Rack rack;
    private final Historic historic;
    private boolean abandonned;

    /**
     * Creates a new game.
     */
    public Game() {
        this(false, false);
    }

    public Game(boolean ia1, boolean ia2) {
        players = new Players(ia1, ia2);
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
    public void iaStart() {
        if (players.getCurrentPlayer() instanceof IA) {
            iaPlay();
        }
    }

    @Override
    public boolean isFinished() {
        if (abandonned) {
            return true;
        } else if (canPlay()) {
            return false;
        } else {
            players.next();
            boolean isFinished = !canPlay();
            players.previous();
            return isFinished;
        }
    }

    /**
     * Returns true if the current player can put a piece to switch others.
     *
     * @return true if he can
     */
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
        if (p instanceof IA) {
            return new IA((IA) p);
        }
        return new Player(p);
    }

    @Override
    public Player getPreviousPlayer() {
        Player p = players.getPreviousPlayer();
        if (p instanceof IA) {
            return new IA((IA) p);
        }
        return new Player(p);
    }

    @Override
    public Player getWinner() {
        if (isFinished()) {
            return new Player(players.getWinner());
        } else {
            return null;
        }
    }

    @Override
    public int getNbWalls() {
        return rack.getNbWalls();
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
        List<Coordinates> list = new ArrayList<>();
        for (Coordinates pos : accessibles) {
            list.add(new Coordinates(pos));
        }
        return list;
    }

    @Override
    public List<Coordinates> getSwitchedPositions() {
        // Be careful, the next code line links it to the real list, instantly updated
        return Collections.unmodifiableList(board.getSwitchedPositions());
    }

    @Override
    public ObservableList<Move> getHistoric() {
        return historic.getHistoric();
    }

    @Override
    public void putPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        int points = board.put(rack.getPiece(players.getCurrentPlayer().getColor()), pos);
        rack.removePiece();
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
        rack.addWall();
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
    public void abandon() {
        abandonned = true;
        historic.add(players.getCurrentPlayer().getName(), MoveAction.ABANDON, null, 0);
        setChanged();
        notifyObservers();
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void initGame() {
        board = new Board(MAX_ROWS_COLS);
        rack = new Rack(MAX_ROWS_COLS);
        abandonned = false;
        players.init();
        historic.add(players.getCurrentPlayer().getName(), MoveAction.NEW_GAME, null, 0);
        updateAccessibles();
    }

    private void nextPlayer() {
        players.next();
        updateAccessibles();
        notifyObservers();
        if (!isFinished()) {
            iaPlay();
        }
    }

    private void iaPlay() {
        if (players.getCurrentPlayer() instanceof Strategy) {
            Strategy ia = (Strategy) players.getCurrentPlayer();
            ia.play(this);
        }
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, players.getCurrentPlayer().getColor());
    }
}
