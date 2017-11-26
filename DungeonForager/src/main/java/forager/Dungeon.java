/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import java.util.List;
import java.util.ArrayList;

/**
 * Dungeon is used to store the map and to generate node information.
 *
 * @author lmantyla
 */
public class Dungeon {

    private char[][] map;
    private Tile[][] dungeonTiles;

    public Dungeon(char[][] map) {
        this.map = map;
        createTileList();
    }

    public Dungeon(String[] stringMap) {
        map = new char[stringMap.length][stringMap[0].toCharArray().length];

        for (int val = 0; val < stringMap.length; val++) {
            map[val] = stringMap[val].toCharArray();
        }
        createTileList();
    }

    public void createTileList() {
        dungeonTiles = new Tile[ySize()][xSize()];

        for (int yval = 0; yval < ySize(); yval++) {
            for (int xval = 0; xval < xSize(); xval++) {
                dungeonTiles[yval][xval] = createTile(xval, yval);
            }
        }
    }

    // Selects tiles adjacent to a particular tile.
    public List<Tile> getAdjacentTiles(int x, int y) {
        List<Tile> adjacentTiles = new ArrayList<>();

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

    public Tile getTile(int x, int y) {
        if (isWithinMap(x, y)) {
            return dungeonTiles[y][x];
        }
        return null;
    }

    //Generates tile information from the map.
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
        } else {
            return new Tile(x, y, 1, 0);
        }
    }

    //Checks if coordinates are within bounds.
    public boolean isWithinMap(int x, int y) {
        if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
            return false;
        } else {
            return true;
        }
    }

    public int xSize() {
        return map[0].length;
    }

    public int ySize() {
        return map.length;
    }

    public char getChar(int x, int y) {
        return map[y][x];
    }

    // Finds first example of a particular character in the map.
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
