/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

/**
 * A node class containing information about a tile.
 * @author lmantyla
 */
public class Tile {

    private int x;
    private int y;

    private int energyCost;
    private int timeCost;

    public Tile(int x, int y, int energyCost) {
        this.x = x;
        this.y = y;
        this.energyCost = energyCost;
        this.timeCost = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getTimeCost() {
        return timeCost;
    }

    @Override
    public boolean equals(Object obj) {
        Tile otherTile = (Tile) obj;
        if (x == otherTile.x && y == otherTile.y) {
            return true;
        }
        return false;
    }    
}
