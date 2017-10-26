/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g43197.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g43197
 */
public class Game {

    private Board board;
    private List<Player> players;
    private Player currentPlayer; 

    public Game() {
        board = new Board();
        players = new ArrayList<>();
        for (Color color : Color.values()) {
            players.add(new Player(color));
        }
        currentPlayer = players.get(0);
    }

    public boolean isFinished() {
        for(Player player : players){
            if(player.hasMovesLeft()){
                return true;
            }
        }
        return false;
    }
    
    public boolean canPlay(){
        return currentPlayer.hasMovesLeft();
    }

    public int getScore(Player player) {
        return currentPlayer.getScore();
    }

    public Player getCurrentPlayer() {
        return currentPlayer; 
    }

    public void put(Coordinates pos) {
        if(pos == null){
            throw new IllegalArgumentException("Piece and pos can't be null!");
        }
        //TODO take an existing piece (otherwise, can create always new pieces
        board.put(piece, pos);
        nextPlayer();
    }
    
    private void nextPlayer() {
        int i = players.indexOf(currentPlayer) + 1;
        if (i < players.size()) {
            currentPlayer = players.get(i);
        } else {
            currentPlayer = players.get(0);
        }
    }
}
