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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] stringMap = new String[]{
            "#############",
            "#..E#.......#",
            "#..#..#..#..#",
            "#........##.#",
            "#........E..#",
            "#############"
        };

        Dungeon dungeon = new Dungeon(stringMap);

        Forager forager = new Forager(dungeon);

        Step step = forager.searchPath(dungeon.getTile(1, 4), dungeon.getTile(9, 1), 10);

        while (step != null) {

            for (int yval = 0; yval < dungeon.map.length; yval++) {
                for (int xval = 0; xval < dungeon.map[0].length; xval++) {
                    if (step.getTile().getX() == xval && step.getTile().getY() == yval) {
                        System.out.print("@");
                    } else {
                        System.out.print("" + dungeon.map[yval][xval]);
                    }
                }
                System.out.println("");
            }

            step = step.getPreviousStep();
        }
    }
}
