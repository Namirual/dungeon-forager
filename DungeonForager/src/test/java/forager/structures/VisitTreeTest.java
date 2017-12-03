/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import forager.VisitMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lmantyla
 */
public class VisitTreeTest {

    VisitTree tree;
    VisitMap map;
    VisitMap map2;

    @Before
    public void setUp() {
        boolean[] specialVisited = {false, false, true, false, false};
        boolean[] specialVisited2 = {false, false, false, false, true};

        boolean[][] visited = new boolean[5][5];

        map = new VisitMap(specialVisited, visited, 1);
        map2 = new VisitMap(specialVisited2, visited, 1);

        tree = new VisitTree(5);
    }

    @Test
    public void canAddAndRetrieveMap() {
        tree.addVisitMap(map);
        assertEquals(tree.findVisitMap(map), map);
    }

    @Test
    public void canAddAndRetrieveMultipleMaps() {
        tree.addVisitMap(map);
        tree.addVisitMap(map2);

        assertEquals(tree.findVisitMap(map), map);
        assertEquals(tree.findVisitMap(map2), map2);
    }

    @Test
    public void returnsNullWhenMapIsNotFound() {
        assertEquals(tree.findVisitMap(map), null);
    }
}
