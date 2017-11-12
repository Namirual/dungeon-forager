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
    Tile startingTile;
    boolean[][] visited;

    public Cycle(Tile startingTile, boolean[][] visited) {
        this.startingTile = startingTile;
        this.visited = visited;
    }
}
