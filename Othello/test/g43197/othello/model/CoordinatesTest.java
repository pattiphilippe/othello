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
public class CoordinatesTest {

    /**
     * Test of clone method, of class Coordinates.
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        Coordinates pos = new Coordinates(2, 3);
        Coordinates clone = pos.clone();
        assertEquals(pos, clone);
    }
}
