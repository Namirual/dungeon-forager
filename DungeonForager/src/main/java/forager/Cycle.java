/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

/**
 * Cycle stores information about tiles that have been visited.
 * @author lmantyla
 */
public class Cycle {
    private Cycle previousCycle;
    private Tile startingTile;
    private boolean[][] visited;

    public Cycle(Cycle previousCycle, Tile startingTile, boolean[][] visited) {
        this.previousCycle = previousCycle;
        this.startingTile = startingTile;
        this.visited = visited;
    }
    
    public boolean isVisited(int x, int y) {
        return visited[y][x];
    }
    
    public void setVisited(int x, int y) {
        visited[y][x] = true;
    }
    
    // Check whether a special tile has been visited and spent during this or previous cycle.
    public boolean isSpecialUsed(Tile tile) {
        Cycle visited = this;
                
        while (visited != null) {
            if (visited.getStartingTile().equals(tile)) {
                return true;
            }
            visited = visited.previousCycle;
        }
        return false;
    }

    public Tile getStartingTile() {
        return startingTile;
    }

    public Cycle getPreviousCycle() {
        return previousCycle;
    }
}
