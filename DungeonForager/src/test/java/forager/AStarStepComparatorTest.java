/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import forager.domain.Heuristic;
import forager.domain.Tile;
import forager.structures.MyMinHeapPerformanceTest;
import java.util.PriorityQueue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author lmantyla
 */
public class AStarStepComparatorTest {

    AStarStepComparator comparator;

    Tile goalTile;

    @Before
    public void setUp() {
        goalTile = new Tile(5, 5, 0, 0);

    }

    @Test
    public void diagonalHeuristicWorks() {
        comparator = new AStarStepComparator(goalTile, Heuristic.Diagonal);

        Tile tile = new Tile(2, 1, 0, 0);

        assertEquals(comparator.heuristic(tile), 5, 0.01);
    }

    @Test
    public void manhattanHeuristicWorks() {
        comparator = new AStarStepComparator(goalTile, Heuristic.Manhattan);

        Tile tile = new Tile(0, 0, 0, 0);

        assertEquals(comparator.heuristic(tile), 10d, 0.01);

    }

    @Test
    public void noHeuristicWorks() {
        comparator = new AStarStepComparator(goalTile, Heuristic.Dijkstra);

        Tile tile = new Tile(0, 0, 0, 0);

        assertEquals(comparator.heuristic(tile), 0, 0.01);
    }
}
