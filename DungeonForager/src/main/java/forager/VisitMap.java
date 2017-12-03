/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

/**
 * Class for storing information about visited tiles.
 * @author lmantyla
 */
public class VisitMap implements Comparable<VisitMap> {

    private boolean[] specialVisited;
    private boolean[][] visited;
    int specialTilesVisited;

    public VisitMap(boolean[] specialVisited, boolean[][] visited, int specialTilesVisited) {
        this.specialVisited = specialVisited;
        this.visited = visited;
        this.specialTilesVisited = specialTilesVisited;
    }
    
    public VisitMap(Dungeon dungeon) {
        specialVisited = new boolean[dungeon.getSpecialTiles().size()];
        visited = new boolean[dungeon.ySize()][dungeon.xSize()];
        specialTilesVisited = 0;
    }

    public VisitMap(VisitMap visitMap, Tile tile) {
        this.visited = new boolean[visitMap.visited.length][visitMap.visited[0].length];
        this.specialVisited = new boolean[visitMap.specialVisited.length];
        
        for (int val = 0; val < visitMap.specialVisited.length; val++) {
            this.specialVisited[val] = visitMap.specialVisited[val];
        }

        /*System.out.println("special tiles visited " + visitMap.specialTilesVisited);
        for (int val = 0; val < specialVisited.length; val++) {
            if (visitMap.getSpecialVisited(val)) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println("");*/

        this.specialVisited[tile.getSpecialNum()] = true;
        this.specialTilesVisited = visitMap.specialTilesVisited + 1;
    }

    public boolean isVisited(int x, int y) {
        return visited[y][x];
    }

    public void setVisited(int x, int y) {
        visited[y][x] = true;
    }

    public boolean getSpecialVisited(int specialNum) {
        return specialVisited[specialNum];
    }

    @Override
    public int compareTo(VisitMap o) {
        for (int tile = 0; tile < specialVisited.length; tile++) {
            if (specialVisited[tile] != o.specialVisited[tile]) {
                if (specialVisited[tile]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        VisitMap otherVisitMap = (VisitMap) obj;

        if (specialVisited.equals(otherVisitMap.specialVisited)) {
            return true;
        }
        return false;
    }

}
