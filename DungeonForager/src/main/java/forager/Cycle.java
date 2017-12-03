/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

/**
 * Cycle contains a link to a VisitMap.
 *
 * @author lmantyla
 */
public class Cycle {

    private Cycle previousCycle;
    private Tile startingTile;
    private VisitMap visitMap;

    public Cycle(Cycle previousCycle, Tile startingTile, Dungeon dungeon) {
        this.previousCycle = previousCycle;
        this.startingTile = startingTile;
        this.visitMap = new VisitMap(dungeon);
    }

    public Cycle(Cycle previousCycle, Tile startingTile, VisitMap visitMap) {
        this.previousCycle = previousCycle;
        this.startingTile = startingTile;
        this.visitMap = visitMap;
    }

    public boolean isVisited(int x, int y) {
        return visitMap.isVisited(x, y);
    }

    public void setVisited(int x, int y) {
        visitMap.setVisited(x, y);
    }

    // Check whether a special tile has been visited and spent during this or previous cycle.
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

    public Tile getStartingTile() {
        return startingTile;
    }

    public Cycle getPreviousCycle() {
        return previousCycle;
    }

    public VisitMap getVisitMap() {
        return visitMap;
    }
}
