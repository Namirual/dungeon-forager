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
public class Main {

    /**
     * Main class, tests and shows output of the algorithm on a single map.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] stringMap = new String[]{
            "#############",
            "#..EO......G#",
            "#..#..#..#..#",
            "#........##.#",
            "#S...#..E...#",
            "#############"
        };

        Dungeon dungeon = new Dungeon(stringMap);
        Forager forager = new Forager(dungeon);

        //Pathfinding is initiated. A tile with 'S' is given as start, 'G' as goal.
        Step step = forager.searchPath(dungeon.findTileWithChar('S'),
                dungeon.findTileWithChar('G'), 5);

        //Each step of the selected path is printed from end to beginning.
        //Visited special icons are rendered as normal floor dots.
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

            step = step.getPreviousStep();
        }
    }
}
