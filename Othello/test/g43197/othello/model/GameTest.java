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
public class GameTest {

    /**
     * Test of isFinished method, of class Game.
     */
    @Test
    public void testIsFinished() {
        System.out.println("isFinished");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlay method, of class Game.
     */
    @Test
    public void testCanPlay() {
        System.out.println("canPlay");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.canPlay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Player player = null;
        Game instance = new Game();
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class Game.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Coordinates pos = null;
        Game instance = new Game();
        instance.put(pos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
