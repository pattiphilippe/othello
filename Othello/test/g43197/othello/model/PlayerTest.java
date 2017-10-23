/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g43197.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class PlayerTest {

    /**
     * Test of constructor, normal case.
     */
    @Test
    public void newPlayer() {
        Player p = new Player(Color.BLACK);
        assertEquals(p.getColor(), Color.BLACK);
        assertEquals(p.getScore)
    }

    /**
     * Checks the exception if null in arg of Player constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void newPlayerNullColor() {
        new Player(null);
    }

    /**
     * Test of modifyScore method, of class Player.
     */
    @Test
    public void testModifyScore() {
        Player player = new Player(Color.WHITE);
        int delta = 2;
        player.modifyScore(delta);
        assertEquals(delta, player.get);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
