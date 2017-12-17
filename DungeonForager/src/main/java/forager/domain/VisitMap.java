package forager.domain;

import forager.domain.Tile;
import forager.domain.Dungeon;

/**
 * Class for storing a map of visited normal tiles and special tiles that have
 * been spent.
 *
 * @author lmantyla
 */
public class VisitMap implements Comparable<VisitMap> {

    private boolean[] specialVisited;
    private boolean[][] visited;
    int specialTilesVisited;

    /**
     * Creates a new VisitMap. Used mainly for testing.
     *
     * @param specialVisited Boolean array marking whether each special tile has
     * been visited on this map.
     * @param visited Boolean map marking whether normal tiles have been
     * visited.
     * @param specialTilesVisited number of special tiles visited.
     */
    public VisitMap(boolean[] specialVisited, boolean[][] visited, int specialTilesVisited) {
        this.specialVisited = specialVisited;
        this.visited = visited;
        this.specialTilesVisited = specialTilesVisited;
    }

    /**
     * Creates a new VisitMap based on a dungeon. Only used for the first
     * VisitMap created.
     *
     * @param dungeon dungeon the VisitMap is based on.
     */
    public VisitMap(Dungeon dungeon) {
        specialVisited = new boolean[dungeon.getSpecialTiles().size()];
        visited = new boolean[dungeon.ySize()][dungeon.xSize()];
        specialTilesVisited = 0;
    }

    /**
     * Creates a new VisitMap based on a previous visit map. Used most of the
     * time.
     *
     * @param visitMap visit map from previous cycle.
     * @param tile new special tile to be added to the visit map.
     */
    public VisitMap(VisitMap visitMap, Tile tile) {
        this.visited = new boolean[visitMap.visited.length][visitMap.visited[0].length];
        this.specialVisited = new boolean[visitMap.specialVisited.length];

        for (int val = 0; val < visitMap.specialVisited.length; val++) {
            this.specialVisited[val] = visitMap.specialVisited[val];
        }

        this.specialVisited[tile.getSpecialNum()] = true;
        this.specialTilesVisited = visitMap.specialTilesVisited + 1;
    }

    /**
     * Checks if a normal tile is marked as visited.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true or false
     */
    public boolean isVisited(int x, int y) {
        return visited[y][x];
    }

    /**
     * Marks a normal tile as visited
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setVisited(int x, int y) {
        visited[y][x] = true;
    }

    /**
     * Checks if a particular special tile has been visited
     *
     * @param specialNum index of the special tile on the special tiles array
     * @return true or false as marked on specialVisited
     */
    public boolean getSpecialVisited(int specialNum) {
        return specialVisited[specialNum];
    }

    @Override
    public int compareTo(VisitMap o) {
        for (int tile = 0; tile < specialVisited.length; tile++) {
            if (specialVisited[tile] != o.specialVisited[tile]) {
                if (specialVisited[tile]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        VisitMap otherVisitMap = (VisitMap) obj;

        if (specialVisited.equals(otherVisitMap.specialVisited)) {
            return true;
        }
        return false;
    }

}
