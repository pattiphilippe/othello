package g43197.othello;

import g43197.othello.model.*;
import g43197.othello.view.BoardView;
import static g43197.othello.view.BoardView.*;

/**
 *
 * @author G43197
 */
public class Othello {

    public static void main(String[] args) {
        BoardView.init();
        Board board = new Board();
        draw(board);
        
        Game game = new Game();
    }
    
    /**
     * Plays a turn for the player. 
     * @param player
     */
    public static void play(Game game){
        if(game.canPlay()){
            boolean played = false;
            while(!played){
            try { 
                readPos();
                played = true;
            } catch (GameException e) {
                displayError("wrongPosition");
            }
        }
    }
}
