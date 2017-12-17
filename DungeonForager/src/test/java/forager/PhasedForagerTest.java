package forager;

import forager.domain.Heuristic;
import forager.domain.VisitMap;
import forager.domain.Tile;
import forager.domain.Step;
import forager.domain.Line;
import forager.domain.Dungeon;
import forager.domain.Cycle;
import forager.structures.MyArrayList;
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

    String[] symmetricMap = new String[]{
        "##############",
        "#.E..#...#...#",
        "#S.#...#...#G#",
        "#.E..#...#...#",
        "##############"
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
    public void specialRoutesFindTilesWithinRange() {
        MyArrayList<Line> routes = forager.findAccessibleSpecialTiles(dungeon.getTile(1, 4), 6);
        assertEquals(routes.size(), 1);
        routes = forager.findAccessibleSpecialTiles(dungeon.getTile(1, 4), 9);
        assertEquals(routes.size(), 2);
    }

    @Test
    public void newCyclesCreateVisitTrees() {
        boolean[] specialVisited = {true, false, false};
        boolean[] specialVisited2 = {true, true, false};

        tileA.setSpecialNum(0);
        tileB.setSpecialNum(1);

        VisitMap visitMap = new VisitMap(specialVisited, new boolean[5][5], 0);
        VisitMap nextVisitMap = new VisitMap(specialVisited2, new boolean[5][5], 1);

        forager.getVisitTree().addVisitMap(visitMap);

        Cycle cycleD = new Cycle(cycleA, tileA, visitMap);
        Step currentStep = new Step(tileA, null, cycleD, 5, 0);

        Cycle newCycle = forager.createNewCycle(currentStep, tileB);

        newCycle.getVisitMap().equals(nextVisitMap);

        assertEquals(forager.getReuses(), 0);
        assertEquals(forager.getCycles(), 2);

    }

    @Test
    public void newCyclesAreReuseExistingVisitTrees() {
        boolean[] specialVisited = {true, false, false};
        boolean[] specialVisited2 = {true, true, false};

        //Line line = new Line(tileB, 5, 5);
        tileA.setSpecialNum(0);
        tileB.setSpecialNum(1);

        VisitMap visitMap = new VisitMap(specialVisited, new boolean[5][5], 0);
        VisitMap nextVisitMap = new VisitMap(specialVisited2, new boolean[5][5], 1);

        forager.getVisitTree().addVisitMap(visitMap);
        forager.getVisitTree().addVisitMap(nextVisitMap);

        Cycle cycleD = new Cycle(cycleA, tileA, visitMap);
        Step currentStep = new Step(tileA, null, cycleD, 5, 0);

        Cycle newCycle = forager.createNewCycle(currentStep, tileB);

        newCycle.getVisitMap().equals(nextVisitMap);

        assertEquals(forager.getReuses(), 1);
    }

    @Test
    public void phasedForagerFindsRoute() {
        Step step = forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 7, Heuristic.Manhattan);
        assertEquals(step.getTile(), dungeon.getTile(9, 1));
    }

    @Test
    public void returnsNullWhenNoPathIsFound() {
        assertNull(forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 1, Heuristic.Manhattan));
    }

    @Test
    public void phasedForagerRouteIsCorrectLength() {
        Step step = forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 7, Heuristic.Manhattan);
        assertEquals(step.getTimeSpent(), 21);
    }

    @Test
    public void symmetricMapReusesVisitTree() {
        dungeon = new Dungeon(symmetricMap);
        forager = new PhasedForager(dungeon);

        Step step = forager.searchPath(dungeon.findTileWithChar('S'),
                dungeon.findTileWithChar('G'), 5, Heuristic.Manhattan);

        assertEquals(forager.getReuses(), 4);
    }
}
//