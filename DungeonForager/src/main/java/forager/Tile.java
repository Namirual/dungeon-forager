/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import java.util.ArrayList;
import java.util.List;

/**
 * A node class containing information about a tile.
 *
 * @author lmantyla
 */
public class Tile {

    private int x;
    private int y;

    private int energyCost;
    private int specialCost;
    private int timeCost;
    private List<Line> neighbors;

    public Tile(int x, int y, int energyCost, int specialCost) {
        this.x = x;
        this.y = y;
        this.energyCost = energyCost;
        this.specialCost = specialCost;
        this.timeCost = 1;
        //this.neighbors = new ArrayList<>();
    }

    public Tile(int x, int y, int energyCost, int specialCost, int timeCost) {
        this.x = x;
        this.y = y;
        this.energyCost = energyCost;
        this.specialCost = specialCost;
        this.timeCost = timeCost;
        //this.neighbors = new ArrayList<>();
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

    public int getSpecialCost() {
        return specialCost;
    }

    public int getTimeCost() {
        return timeCost;
    }

    public List<Line> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Line> neighbors) {
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
