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
 * @author lmantyla
 */
public class Dungeon {

    public char[][] map;

    public Dungeon(String[] stringMap) {
        map = new char[stringMap.length][stringMap[0].toCharArray().length];
        
        for (int val = 0; val < stringMap.length; val++) {
            map[val] = stringMap[val].toCharArray();
        }
    }
    
    // Selects tiles adjacent to a particular tile.
    public List<Tile> getAdjacentTiles(int x, int y) {
        List<Tile> adjacentTiles = new ArrayList<>();

        adjacentTiles.add(getTile(x + 1, y));
        adjacentTiles.add(getTile(x - 1, y));
        adjacentTiles.add(getTile(x, y + 1));
        adjacentTiles.add(getTile(x, y - 1));

        return adjacentTiles;
    }

    //Generates tile information from the map.
    public Tile getTile(int x, int y) {
        if (!isWithinMap(x, y)) {
            return null;
        }
        if (map[y][x] == '#') {
            return null;
        } else if (map[y][x] == 'E') {
            return new Tile(x, y, -10);
        } else {
            return new Tile(x, y, 1);
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
}
