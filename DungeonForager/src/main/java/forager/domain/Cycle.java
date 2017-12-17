package forager.domain;

/**
 * Cycle contains a link to a VisitMap.
 *
 * @author lmantyla
 */
public class Cycle {

    private Cycle previousCycle;
    private Tile startingTile;
    private VisitMap visitMap;

    /**
     * Creates a new cycle.
     *
     * @param previousCycle cycle from which this cycle originates
     * @param startingTile the tile this cycle starts from
     * @param dungeon dungeon to be used as a base for creating a visit map
     */
    public Cycle(Cycle previousCycle, Tile startingTile, Dungeon dungeon) {
        this.previousCycle = previousCycle;
        this.startingTile = startingTile;
        this.visitMap = new VisitMap(dungeon);
    }

    /**
     * Creates a new cycle.
     *
     * @param previousCycle cycle from which this cycle originates
     * @param startingTile the tile this cycle starts from
     * @param visitMap visit map to be used by the cycle
     */
    public Cycle(Cycle previousCycle, Tile startingTile, VisitMap visitMap) {
        this.previousCycle = previousCycle;
        this.startingTile = startingTile;
        this.visitMap = visitMap;
    }

    /**
     * Checks if a coordinate has been visited.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true or false
     */
    public boolean isVisited(int x, int y) {
        return visitMap.isVisited(x, y);
    }

    /**
     * Marks a coordinate as visited.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setVisited(int x, int y) {
        visitMap.setVisited(x, y);
    }

    /**
     * Check whether a special tile has been visited and spent during this or
     * previous cycle.
     *
     * @param tile The tile that is being checked
     * @return true or false
     */
    public boolean isSpecialSpent(Tile tile) {
        Cycle cycle = this;
        while (cycle != null) {
            if (cycle.getStartingTile().equals(tile)) {
                return true;
            }
            cycle = cycle.getPreviousCycle();
        }
        return false;
    }

    /**
     * Returns starting cycle.
     *
     * @return the tile the cycle starts from.
     */
    public Tile getStartingTile() {
        return startingTile;
    }

    /**
     * Returns the preceding cycle
     *
     * @return the preceding cycle
     */
    public Cycle getPreviousCycle() {
        return previousCycle;
    }

    /**
     * Returns the visit map used by the cycle.
     *
     * @return visit map
     */
    public VisitMap getVisitMap() {
        return visitMap;
    }
}
