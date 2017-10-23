/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g43197.othello.model;

/**
 *
 * @author G43197
 */
public class Coordinates {

    private final int ROW;
    private final int COL;

    /**
     *
     * @param row
     * @param col
     */
    public Coordinates(int row, int col) {
        this.ROW = row;
        this.COL = col;
    }

    /**
     *
     * @return
     */
    public int getROW() {
        return ROW;
    }

    /**
     *
     * @return
     */
    public int getCOL() {
        return COL;
    }
}
