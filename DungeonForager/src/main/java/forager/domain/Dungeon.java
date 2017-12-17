package forager.domain;

import forager.structures.MyArrayList;
import java.util.List;

/**
 * Dungeon is used to store the map and to generate node information.
 *
 * @author lmantyla
 */
public class Dungeon {

    private char[][] map;
    private Tile[][] dungeonTiles;
    private MyArrayList<Tile> specialTiles;

    /**
     * Creates a new dungeon from a 2D char array.
     *
     * @param map dungeon map in the form of a 2D char array
     */
    public Dungeon(char[][] map) {
        this.map = map;

        specialTiles = new MyArrayList<Tile>();
        createTileList();
    }

    /**
     * Creates a new dungeon from a string array.
     *
     * @param stringMap dungeon map in the form of a string array
     */
    public Dungeon(String[] stringMap) {
        map = new char[stringMap.length][stringMap[0].toCharArray().length];

        for (int val = 0; val < stringMap.length; val++) {
            map[val] = stringMap[val].toCharArray();
        }
        specialTiles = new MyArrayList<Tile>();

        createTileList();
    }

    /**
     * Creates a list of tiles in the map.
     */
    public void createTileList() {
        dungeonTiles = new Tile[ySize()][xSize()];

        int specialNum = 0;

        for (int yval = 0; yval < ySize(); yval++) {
            for (int xval = 0; xval < xSize(); xval++) {
                Tile tile = createTile(xval, yval);
                dungeonTiles[yval][xval] = tile;
                if (tile != null && tile.getSpecialCost() != 0) {
                    tile.setSpecialNum(specialNum);
                    specialTiles.add(tile);
                    specialNum++;
                }
            }
        }
    }

    /**
     * Returns a list of non-wall tiles adjacent to a tile at given coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return list of available tiles.
     */
    public MyArrayList<Tile> getAdjacentTiles(int x, int y) {
        MyArrayList<Tile> adjacentTiles = new MyArrayList<>();

        adjacentTiles.add(getTile(x + 1, y));
        adjacentTiles.add(getTile(x - 1, y));
        adjacentTiles.add(getTile(x, y + 1));
        adjacentTiles.add(getTile(x, y - 1));

        int tileNum = adjacentTiles.size() - 1;

        for (int val = tileNum; val >= 0; val--) {
            if (adjacentTiles.get(val) == null) {
                adjacentTiles.remove(val);
            }
        }
        return adjacentTiles;
    }

    /**
     * Returns tile at given coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return found tile, null if none exists
     */
    public Tile getTile(int x, int y) {
        if (isWithinMap(x, y)) {
            return dungeonTiles[y][x];
        }
        return null;
    }

    /**
     * Generates tile information for given coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return new tile.
     */
    public Tile createTile(int x, int y) {
        if (!isWithinMap(x, y)) {
            return null;
        }
        if (map[y][x] == '#') {
            return null;
        } else if (map[y][x] == 'E') {
            return new Tile(x, y, 1, -10);
        } else if (map[y][x] == 'O') {
            return new Tile(x, y, 1, 5);
        } else if (map[y][x] == 'G') {
            return new Tile(x, y, 1, -1);
        } else if (map[y][x] == ' ') {
            return new Tile(x, y, 2, 0, 2);
        } else {
            return new Tile(x, y, 1, 0);
        }
    }

    /**
     * Checks if coordinates are within bounds.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true or false
     */
    public boolean isWithinMap(int x, int y) {
        if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gives the width of the map.
     *
     * @return width
     */
    public int xSize() {
        return map[0].length;
    }

    /**
     * Gives the height of the map.
     *
     * @return height
     */
    public int ySize() {
        return map.length;
    }

    /**
     * Returns the character at given coordinates
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return character
     */
    public char getChar(int x, int y) {
        return map[y][x];
    }

    /**
     * Returns an ordered list of special tiles on the map.
     *
     * @return list of special tiles
     */
    public MyArrayList<Tile> getSpecialTiles() {
        return specialTiles;
    }

    /**
     * Finds first instance of a given character in the map. Mainly used to
     * locate the starting and ending tiles.
     *
     * @param character character to be searched
     * @return tile with given character
     */
    public Tile findTileWithChar(char character) {
        for (int yval = 0; yval < ySize(); yval++) {
            for (int xval = 0; xval < xSize(); xval++) {
                if (getChar(xval, yval) == character) {
                    return getTile(xval, yval);
                }
            }
        }
        return null;
    }
}
