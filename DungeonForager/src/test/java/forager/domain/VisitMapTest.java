/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.domain;

import forager.domain.VisitMap;
import forager.domain.Tile;
import forager.domain.Cycle;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class VisitMapTest {

    Tile tileA;
    Tile tileB;
    Tile tileC;
    VisitMap visitMapA;
    VisitMap visitMapB;

    Cycle cycleA;

    @Before
    public void setUp() {
        tileA = new Tile(1, 1, 1, 0);
        tileA.setSpecialNum(0);
        tileB = new Tile(2, 1, 1, -5);
        tileB.setSpecialNum(1);
        tileC = new Tile(2, 1, 1, -5);
        tileC.setSpecialNum(2);

        boolean[] specialVisitedA = {true, false, false};
        boolean[] specialVisitedB = {true, true, false};
        boolean[] specialVisitedC = {true, true, true};

        visitMapA = new VisitMap(specialVisitedA, new boolean[5][5], 0);
        visitMapB = new VisitMap(specialVisitedB, new boolean[5][5], 1);

        cycleA = new Cycle(null, tileA, visitMapA);

    }

    @Test
    public void equalWorksBasedOnVisitedSpecialTiles() {
        boolean[] specialVisited = {true, false, false};
        boolean[][] mapA = new boolean[5][5];

        mapA[1][1] = true;

        VisitMap newVisitMap = new VisitMap(specialVisited, mapA, 1);

        newVisitMap.equals(visitMapA);
    }

    @Test
    public void newTileMapHasVisitedTilesOfOldVisitMapAndNewTile() {
        VisitMap newVisitMap = new VisitMap(visitMapA, tileB);

        newVisitMap.equals(visitMapB);
    }

    @Test
    public void compareToRecognisesTwoIdenticalVisitMaps() {
        VisitMap newVisitMap = new VisitMap(visitMapA, tileB);

        assertEquals(newVisitMap.compareTo(visitMapB), 0);
    }

    @Test
    public void compareToRecognisesDifferentVisitMaps() {
        assertEquals(visitMapB.compareTo(visitMapA), 1);
        assertEquals(visitMapA.compareTo(visitMapB), -1);
    }
}
