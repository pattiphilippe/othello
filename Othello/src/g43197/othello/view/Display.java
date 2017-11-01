/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g43197.othello.view;

import g43197.othello.model.Color;

/**
 *
 * @author Philippe
 */
public class Display {

    public static void error(String errorMsg) {
        System.out.println(errorMsg);
    }

    public static void endGame() {
        //TODO implement scores
    }

    public static void cantPlay() {
        System.out.println("Current player can't play.");
    }

    public static void player(Color currentPlayer) {
    }

    public static void score(int score) {
    }

    public static void turn(Color currentPlayer, int score) {
        System.out.println("Player: " + currentPlayer);
        System.out.println("Score: " + score);
    }

    public static void endTurn(Color currentPlayer, int newScore) {
        turn(currentPlayer, newScore);
        System.out.println("\n\n");
    }
}
