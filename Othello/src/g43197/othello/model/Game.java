package g43197.othello.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Facade of Othello. The methods in this class are mainly around the current
 * player, and adapts to the current player every turn.
 *
 * @author g43197
 */
public class Game extends Facade {

    private final Players players;
    private final List<Coordinates> accessibles;
    private Board board;
    private Rack rack;

    /**
     * Creates a new game.
     */
    public Game() {
        players = new Players();
        accessibles = new LinkedList<>();
        startAgain();
    }

    @Override
    public final void startAgain() {
        board = new Board();
        rack = new Rack();
        players.initScores();
        updateAccessibles();
    }

    @Override
    public boolean isFinished() {
        if (canPlay()) {
            return false;
        } else {
            nextPlayer();
            boolean isFinished = !canPlay();
            nextPlayer();
            return isFinished;
        }
    }

    /**
     * Returns true if the current player can put a piece to switch others.
     *
     * @return true if he can
     */
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
        return players.getScores();
    }

    @Override
    public Color getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    @Override
    public int getNbWalls() {
        return rack.getNbWalls();
    }

    @Override
    public Board getBoard() {
        try {
            //TODO check no clone calls inside the model himself
            return board.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public List<Coordinates> getAccessibles() {
        List<Coordinates> accClone = new LinkedList<>();
        accessibles.forEach((pos) -> {
            try {
                accClone.add(pos.clone());
            } catch (CloneNotSupportedException e) {
                //TODO ? que faire avec catch vide
            }
        });
        return accClone;
    }

    @Override
    public List<Coordinates> getSwitchedPositions() {
        return board.getSwitchedPositions();
    }

    @Override
    public void putPiece(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Pos can't be null!");
        }
        int points = board.put(rack.getPiece(players.getCurrentPlayer()), pos);
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
        nextPlayer();
    }

    ///////////////////////////Private//Methods//////////////////////////////
    private void nextPlayer() {
        players.nextPlayer();
        updateAccessibles();
        notifyObservers();
    }

    private void updateAccessibles() {
        board.updateAccessibles(accessibles, players.getCurrentPlayer());
    }
}
