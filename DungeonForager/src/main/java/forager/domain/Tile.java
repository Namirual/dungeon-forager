package forager.domain;

import forager.domain.Line;
import forager.structures.MyArrayList;

/**
 * A node class containing information about a tile.
 *
 * @author lmantyla
 */
public class Tile {

    private int x;
    private int y;
    private int specialNum;

    private int energyCost;
    private int specialCost;
    private int timeCost;
    private MyArrayList<Line> neighbors;

    /**
     * Creates a new tile.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param energyCost amount of energy spent while moving to this tile
     * @param specialCost amount of energy spent or gained when reaching this
     * tile for the first time
     */
    public Tile(int x, int y, int energyCost, int specialCost) {
        this.x = x;
        this.y = y;
        this.energyCost = energyCost;
        this.specialCost = specialCost;
        this.timeCost = 1;

        this.specialNum = 0;
    }

    /**
     * Creates a new tile.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param energyCost amount of energy spent while moving to this tile
     * @param specialCost amount of energy spent or gained when reaching this
     * tile for the first time
     * @param timeCost amount of time spent moving to this tile
     */
    public Tile(int x, int y, int energyCost, int specialCost, int timeCost) {
        this.x = x;
        this.y = y;
        this.energyCost = energyCost;
        this.specialCost = specialCost;
        this.timeCost = timeCost;

        this.specialNum = 0;
    }

    /**
     * Returns x-coordinate of the tile on the dungeon map.
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y-coordinate of the tile on the dungeon map.
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the index number of the tile on the dungeon's list of special
     * tiles.
     *
     * @return index number
     */
    public int getSpecialNum() {
        return specialNum;
    }

    /**
     * Stores the index number of the tile on the on the dungeon's list of
     * special tiles.
     *
     * @param specialNum the number to be stored
     */
    public void setSpecialNum(int specialNum) {
        this.specialNum = specialNum;
    }

    /**
     * Returns energy cost.
     *
     * @return energy cost.
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Returns cost spent or gained while reaching a special tile for the first
     * time.
     *
     * @return special cost. 0 means the tile is not a special tile.
     */
    public int getSpecialCost() {
        return specialCost;
    }

    /**
     * Returns the amount of time spent while moving into this tile.
     *
     * @return time cost.
     */
    public int getTimeCost() {
        return timeCost;
    }

    /**
     * Returns the list of best routes to neighbors defined as processed for
     * this tile by PhasedForager.
     *
     * @return list of neighbors
     */
    public MyArrayList<Line> getNeighbors() {
        return neighbors;
    }

    /**
     * Sets the list of best routes to neighbors used by the second phase of
     * PhasedForager.
     *
     * @param neighbors new list of neighbors
     */
    public void setNeighbors(MyArrayList<Line> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public boolean equals(Object obj) {
        Tile otherTile = (Tile) obj;
        if (x == otherTile.x && y == otherTile.y) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + x + "," + y;
    }

}
