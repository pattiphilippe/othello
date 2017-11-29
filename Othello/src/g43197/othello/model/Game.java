package g43197.othello.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private boolean abandonned;

    /**
     * Creates a new game.
     */
    public Game() {
        players = new Players();
        accessibles = new ArrayList<>();
        initGame();
    }

    @Override
    public void startAgain() {
        initGame();
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean isFinished() {
        if (abandonned) {
            return true;
        } else if (canPlay()) {
            return false;
        } else {
            nextPlayer();
            boolean isFinished = !canPlay();
            //TODO voir pour previous player
            nextPlayer();
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
    public List<Player> getScores() {
        return Collections.unmodifiableList(players.getScores());
    }

    @Override
    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    @Override
    public Player getWinner() {
        Player p = players.getWinner();
        if (isFinished()) {
            return p;
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
        //TODO check if can do it differently, not bind it directly with accessibles
        List<Coordinates> list = new ArrayList<>();
        for (Coordinates pos : accessibles) {
            list.add(new Coordinates(pos));
        }
        return list;
    }

    @Override
    public List<Coordinates> getSwitchedPositions() {
        return Collections.unmodifiableList(board.getSwitchedPositions());
    }

    @Override
    public void putPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        int points = board.put(rack.getPiece(players.getCurrentPlayer().getColor()), pos);
        players.modifyScore(points + 1);
        nextPlayer();
        players.modifyScore(-points);
        setChanged();
        notifyObservers();
    }

    @Override
    public void putWall(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        board.putWall(pos);
        rack.addWall();
        setChanged();
        nextPlayer();
    }

    @Override
    public void pass() {
        if (canPlay()) {
            throw new GameException("Can't pass, can play a turn.");
        }
        setChanged();
        nextPlayer();
    }

    @Override
    public void abandon() {
        abandonned = true;
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean abandonned() {
        return abandonned;
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void initGame() {
        board = new Board(MAX_ROWS_COLS);
        rack = new Rack(MAX_ROWS_COLS);
        abandonned = false;
        players.init();
        updateAccessibles();
    }

    private void nextPlayer() {
        players.next();
        updateAccessibles();
        notifyObservers();
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, players.getCurrentPlayer().getColor());
    }
}
