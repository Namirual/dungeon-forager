/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager.structures;

import forager.VisitMap;

/**
 * Search tree for storing and retrieving VisitMaps.
 * @author lmantyla
 */
public class VisitTree {

    VisitTree noTile;
    VisitTree haveTile;

    VisitMap thisMap;
    final int specialTileNumber;

    public VisitTree(int specialTileNumber) {
        thisMap = null;
        this.specialTileNumber = specialTileNumber;
    }

    public VisitMap findVisitMap(VisitMap visitMap) {
        return findVisitMap(0, visitMap);
    }

    // Recursive method for retrieving maps.
    public VisitMap findVisitMap(int currentNum, VisitMap visitMap) {

        if (currentNum == specialTileNumber) {
            return thisMap;
        }

        if (visitMap.getSpecialVisited(currentNum)) {
            if (this.haveTile != null) {
                return this.haveTile.findVisitMap(currentNum + 1, visitMap);
            } else {
                return null;
            }
        } else if (this.noTile != null) {
            return this.noTile.findVisitMap(currentNum + 1, visitMap);
        } else {
            return null;
        }
    }

    public void addVisitMap(VisitMap visitMap) {
        addVisitMap(0, visitMap);
    }
    
    // Recursive method for adding maps.
    public void addVisitMap(int currentNum, VisitMap visitMap) {
        if (currentNum == specialTileNumber) {
            thisMap = visitMap;
            return;
        }
        if (visitMap.getSpecialVisited(currentNum)) {
            if (this.haveTile != null) {
                haveTile.addVisitMap(currentNum + 1, visitMap);
            } else {
                this.haveTile = new VisitTree(specialTileNumber);
                haveTile.addVisitMap(currentNum + 1, visitMap);
            }
        } else if (this.noTile != null) {
            noTile.addVisitMap(currentNum + 1, visitMap);
        } else {
            this.noTile = new VisitTree(specialTileNumber);
            noTile.addVisitMap(currentNum + 1, visitMap);
        }
    }
}
