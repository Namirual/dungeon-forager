/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import forager.structures.MyArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lmantyla
 */
public class DungeonTest {

    Dungeon dungeon;

    String[] stringMap = new String[]{
        "#############",
        "#..E#.......#",
        "#..#..#..#..#",
        "#........##.#",
        "#........E..#",
        "#############"
    };

    String[] testStringMap = new String[]{
        "######",
        "#..E.#",
        "#.. .#",
        "#...##",
        "#..#.#",
        "######"
    };

    @Before
    public void setUp() {
        dungeon = new Dungeon(testStringMap);

    }

    @Test
    public void CorrectSizedDungeonIsCreatedFromString() {
        assertEquals(dungeon.ySize(), stringMap.length);
    }

    @Test
    public void OutOfBoundsTileDetected() {
        dungeon.isWithinMap(6, 6);
        assertFalse(dungeon.isWithinMap(6, 6));
    }

    @Test
    public void OutOfBoundsTileNotReturned() {
        assertEquals(dungeon.getTile(6, 6), null);
    }

    @Test
    public void FourAdjacentTilesReturned() {
        MyArrayList<Tile> tiles = dungeon.getAdjacentTiles(2, 2);
        assertEquals(tiles.size(), 4);
    }

    @Test
    public void FloorTileWithHigherCostRecognised() {
        MyArrayList<Tile> tiles = dungeon.getAdjacentTiles(2, 2);
        boolean highCostExists = false;
        for (Tile tile : tiles) {
            if (tile.getTimeCost() == 2) {
                highCostExists = true;
            }
        }
        assertTrue(highCostExists);
    }

    @Test
    public void EmptyListWhenSurroundedByWalls() {
        MyArrayList<Tile> tiles = dungeon.getAdjacentTiles(4, 4);
        assertEquals(tiles.size(), 0);
    }

}
