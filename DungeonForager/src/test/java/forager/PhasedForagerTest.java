/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class PhasedForagerTest {

    Dungeon dungeon;
    PhasedForager forager;

    Tile tileA;
    Tile tileB;
    Tile tileC;

    Cycle cycleA;
    Cycle cycleB;

    String[] stringMap = new String[]{
        "#############",
        "#..E#....G..#",
        "#..#####.#..#",
        "#........##.#",
        "#S.......E..#",
        "#############"
    };

    @Before
    public void setUp() {
        dungeon = new Dungeon(stringMap);
        forager = new PhasedForager(dungeon);

        tileA = new Tile(1, 1, 1, 0);
        tileB = new Tile(2, 1, 1, -5);
        tileC = new Tile(2, 1, 1, -5);

        cycleA = new Cycle(null, tileA, dungeon);
        cycleB = new Cycle(cycleA, tileB, dungeon);

    }

    @Test
    public void phasedForagerFindsRoute() {
        Step step = forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 7);
        assertEquals(step.getTile(), dungeon.getTile(9, 1));
    }

    @Test
    public void returnsNullWhenNoPathIsFound() {
        assertNull(forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 1));
    }

    @Test
    public void phasedForagerRouteIsCorrectLength() {
        Step step = forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 7);
        assertEquals(step.getTimeSpent(), 21);
    }

}
