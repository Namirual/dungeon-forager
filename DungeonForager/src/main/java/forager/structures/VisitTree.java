package forager.structures;

import forager.domain.VisitMap;

/**
 * Search tree for storing and retrieving VisitMaps.
 *
 * @author lmantyla
 */
public class VisitTree {

    VisitTree noTile;
    VisitTree haveTile;

    VisitMap visitMap;
    final int specialTileNumber;

    /**
     * Creates a new VisitTree node. The same kind of VisitTree object is used
     * in all parts of a VisitTree.
     *
     * @param specialTileNumber number of special tiles in current dungeon
     */
    public VisitTree(int specialTileNumber) {
        visitMap = null;
        this.specialTileNumber = specialTileNumber;
    }

    /**
     * Starts an iterative search for a particular map.
     *
     * @param visitMap map being searched.
     * @return Map found in tree, null if no map is found
     */
    public VisitMap findVisitMap(VisitMap visitMap) {
        return findVisitMap(0, visitMap);
    }

    private VisitMap findVisitMap(int startingNum, VisitMap soughtMap) {

        int currentNum = startingNum;
        VisitTree currentTree = this;

        while (true) {
            if (currentNum == specialTileNumber) {
                return currentTree.visitMap;
            }

            if (soughtMap.getSpecialVisited(currentNum)) {
                if (currentTree.haveTile != null) {
                    currentTree = currentTree.haveTile;
                    currentNum++;
                } else {
                    return null;
                }
            } else if (currentTree.noTile != null) {
                currentTree = currentTree.noTile;
                currentNum++;
            } else {
                return null;
            }
        }
    }

    /**
     * Starts an iterative search for adding a particular map.
     *
     * @param visitMap map to be added
     */
    public void addVisitMap(VisitMap visitMap) {
        addVisitMap(0, visitMap);
    }

    private void addVisitMap(int startingNum, VisitMap addedMap) {

        int currentNum = startingNum;
        VisitTree currentTree = this;

        while (true) {
            if (currentNum == specialTileNumber) {
                currentTree.visitMap = addedMap;
                return;
            }
            if (addedMap.getSpecialVisited(currentNum)) {
                if (currentTree.haveTile == null) {
                    currentTree.haveTile = new VisitTree(specialTileNumber);
                }
                currentTree = currentTree.haveTile;
                currentNum++;
            } else {
                if (currentTree.noTile == null) {
                    currentTree.noTile = new VisitTree(specialTileNumber);
                }
                currentTree = currentTree.noTile;
                currentNum++;
            }
        }
    }
}
