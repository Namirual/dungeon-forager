package forager.domain;

import forager.domain.Tile;
import forager.domain.Dungeon;
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
    Dungeon stringDungeon;

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
        stringDungeon = new Dungeon(stringMap);
        dungeon = new Dungeon(testStringMap);

    }

    @Test
    public void CorrectSizedDungeonIsCreatedFromString() {
        assertEquals(stringDungeon.ySize(), stringMap.length);
        assertEquals(stringDungeon.xSize(), stringMap[1].length());

    }

    @Test
    public void CorrectSizedDungeonIsCreatedFromCharArray() {
        char[][] dungeonMap = new char[10][15];
        Dungeon charDungeon = new Dungeon(dungeonMap);
        assertEquals(charDungeon.ySize(), 10);
        assertEquals(charDungeon.xSize(), 15);
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
