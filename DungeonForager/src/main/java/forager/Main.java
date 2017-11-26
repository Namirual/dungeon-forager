/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;
import forager.creator.*;

/**
 *
 * @author lmantyla
 */
public class Main {

    /**
     * Main class, tests and shows output of the algorithm on a single map.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] stringMap = new String[]{
            "#############",
            "#..EO...G...#",
            "#..#######..#",
            "#........##.#",
            "#S...#..E...#",
            "#############"
        };
       
        DungeonCreator creator = new DungeonCreator(35, 35, 5);
        //Dungeon dungeon = new Dungeon(stringMap);
        Dungeon dungeon = new Dungeon(creator.createDungon());
                
        PhasedForager forager = new PhasedForager(dungeon);

        //Pathfinding is initiated. A tile with 'S' is given as start, 'G' as goal.
        Step step = forager.searchPath(dungeon.findTileWithChar('S'),
                dungeon.findTileWithChar('G'), 6);
        
        //Each step of the selected path is printed from end to beginning.
        //Visited special icons are rendered as normal floor dots.
        
        System.out.println("Travel time: " + step.getTimeSpent());
        
        while (step != null) {
            for (int yval = 0; yval < dungeon.ySize(); yval++) {
                for (int xval = 0; xval < dungeon.xSize(); xval++) {
                    if (step.getTile().getX() == xval && step.getTile().getY() == yval) {
                        System.out.print("@");
                    } else if (dungeon.getChar(xval, yval) != '#' && step.getCycle().isSpecialUsed(dungeon.getTile(xval, yval))) {
                        System.out.print(".");
                    } else {
                        System.out.print("" + dungeon.getChar(xval, yval));
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            step = step.getPreviousStep();
        }
    }
}
