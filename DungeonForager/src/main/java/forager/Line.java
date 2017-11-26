/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

/**
 *
 * @author lmantyla
 */
public class Line {
    Tile targetTile;
    int energyCost;
    int timeCost;

    public Line(Tile targetTile, int energyCost, int timeCost) {
        this.targetTile = targetTile;
        this.energyCost = energyCost;
        this.timeCost = timeCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getTimeCost() {
        return timeCost;
    }
    
    public Tile getTargetTile() {
        return targetTile;
    }
    
    @Override
    public String toString() {
        return targetTile + " E:" + energyCost + " T:" + timeCost + "  ";
    }
}
