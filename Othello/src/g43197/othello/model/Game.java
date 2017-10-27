/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g43197.othello.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author g43197
 */
public class Game {

    private Board board;
    private Rack rack;
    private List<Player> players;
    private Player currentPlayer;
    private List<Coordinates> accessibles;
    private boolean didPlay;

    public Game() {
        board = new Board();
        rack = new Rack();
        players = new ArrayList<>();
        for (Color color : Color.values()) {
            players.add(new Player(color));
        }
        currentPlayer = players.get(0);
        accessibles = new LinkedList<>();
        initAccessibles();
        didPlay = true;
    }

    public boolean isFinished() {
        return !didPlay && !hasMovesLeft();
    }
    
    public boolean canPlay(){
        boolean canPlay = hasMovesLeft();
        if(!canPlay){
            nextPlayer();
        }
        return canPlay;
    }

    public int getScore(Player player) {
        return currentPlayer.getScore();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void put(Coordinates pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Piece and pos can't be null!");
        }
        board.put(rack.getPiece(currentPlayer.getColor()), pos);
        nextPlayer();
        didPlay = true;
    }

    private void nextPlayer() {
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
        // has to go through put() so that didPlay is true;
        didPlay = false;
        updateAccessibles();
    }
    
    private void updateAccessibles(){
        board.updateAccessibles(accessibles, currentPlayer.getColor());
    }
    
    private boolean hasMovesLeft(){
        return !accessibles.isEmpty();
    }
}
