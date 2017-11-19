/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class CycleTest {

    Cycle cycleA;
    Cycle cycleB;
    Cycle cycleC;

    Tile tileA;
    Tile tileB;
    Tile tileC;

    @Before

    public void setUp() {
        tileA = new Tile(1, 1, -1);
        tileB = new Tile(1, 2, -1);
        tileC = new Tile(2, 2, -1);

        cycleA = new Cycle(null, tileA, new boolean[3][3]);
        cycleB = new Cycle(cycleA, tileB, new boolean[3][3]);
        cycleC = new Cycle(cycleB, tileC, new boolean[3][3]);

    }

    @Test
    public void specialTilesVisitedInPreviousCyclesAreRemembered() {
        cycleC.isSpecialUsed(tileA);

        assertEquals(cycleC.isSpecialUsed(tileA), true);
    }

    @Test
    public void doesNotRememberUnvisitedSpecialTiles() {
        Tile tileD = new Tile(2, 1, -1);
        
        assertEquals(cycleC.isSpecialUsed(tileD), false);
    }

}
