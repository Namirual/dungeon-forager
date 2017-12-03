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
        tileA = new Tile(1, 1, -1, -1);
        tileB = new Tile(1, 2, -1, -1);
        tileC = new Tile(2, 2, -1, -1);

        String[] stringMap = stringMap = new String[]{
        "#############",
        "#..E#.......#",
        "#..#..#..#..#",
        "#........##.#",
        "#........E..#",
        "#############"
    };
        
        Dungeon dungeon = new Dungeon(stringMap);
        
        cycleA = new Cycle(null, tileA, dungeon);
        cycleB = new Cycle(cycleA, tileB, dungeon);
        cycleC = new Cycle(cycleB, tileC, dungeon);
    }

    @Test
    public void specialTilesVisitedInPreviousCyclesAreRemembered() {
        cycleC.isSpecialSpent(tileA);

        assertEquals(cycleC.isSpecialSpent(tileA), true);
    }

    @Test
    public void doesNotRememberUnvisitedSpecialTiles() {
        Tile tileD = new Tile(2, 1, -1, -1);
        
        assertEquals(cycleC.isSpecialSpent(tileD), false);
    }

}
