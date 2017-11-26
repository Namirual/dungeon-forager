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
public class ForagerTest {

    Dungeon dungeon;
    Forager forager;

    Tile tileA;
    Tile tileB;
    Tile tileC;

    Cycle cycleA;
    Cycle cycleB;

    String[] stringMap = new String[]{
        "#############",
        "#..E#.......#",
        "#..#..#..#..#",
        "#........##.#",
        "#........E..#",
        "#############"
    };

    @Before
    public void setUp() {
        dungeon = new Dungeon(stringMap);
        forager = new Forager(dungeon);

        tileA = new Tile(1, 1, 1, 0);
        tileB = new Tile(2, 1, 1, -5);
        tileC = new Tile(2, 1, 1, -5);

        cycleA = new Cycle(null, tileA, dungeon);
        cycleB = new Cycle(cycleA, tileB, dungeon);

    }

    @Test
    public void newStepIsNotTakenIfAtZeroEnergy() {
        Step step = new Step(tileA, null, cycleA, 0, 0);

        assertNull(forager.handleNewStep(tileA, step));
    }

    @Test
    public void newStepIsTakenWhenJustEnoughEnergy() {
        Step step = new Step(tileA, null, cycleA, 1, 0);

        assertEquals(forager.handleNewStep(tileA, step).getEnergyLeft(), 0);
    }

    @Test
    public void newCycleIfTileWithSpecialEnergyAmount() {
        Step step = new Step(tileA, null, cycleA, 5, 0);

        Step newStep = forager.handleNewStep(tileC, step);

        assertEquals(newStep.getCycle().getPreviousCycle(), cycleA);
    }

    @Test
    public void specialTilesCanIncreaseEnergyAmount() {
        Step step = new Step(tileA, null, cycleA, 5, 0);

        Step newStep = forager.handleNewStep(tileC, step);

        assertEquals(newStep.getEnergyLeft(), 10);
    }

    @Test
    public void newCycleNotCreatedWhenSpecialTileAlreadyVisited() {
        Cycle cycleC = new Cycle(cycleB, tileC, dungeon);

        Step step = new Step(tileC, null, cycleC, 5, 0);
        Step newStep = forager.handleNewStep(tileB, step);

        assertEquals(newStep.getCycle(), cycleC);
    }

    @Test
    public void energyChangeIsDefaultAmountWhenSpecialTileAlreadyVisited() {
        Cycle cycleC = new Cycle(cycleB, tileC, dungeon);

        Step step = new Step(tileC, null, cycleC, 5, 0);
        Step newStep = forager.handleNewStep(tileB, step);

        assertEquals(newStep.getEnergyLeft(), 4);
    }

    @Test
    public void foragerFindsRoute() {
        Step step = forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 8);
        assertEquals(step.getTile(), dungeon.getTile(9, 1));
    }

    @Test
    public void returnsNullWhenNoPathIsFound() {
        assertNull(forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 1));
    }
}
