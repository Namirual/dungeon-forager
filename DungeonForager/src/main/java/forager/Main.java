package forager;

import forager.domain.Heuristic;
import forager.domain.Step;
import forager.domain.Dungeon;
import forager.creator.*;
import java.io.File;
import java.util.Scanner;
import java.util.Stack;

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
            "#..EO.....G.#",
            "#..#######..#",
            "#........##.#",
            "#S...#..E...#",
            "#############"
        };

        String[] stringMapEnemies = new String[]{
            "###############",
            "#S...E.O...O.E#",
            "#.#E..#.#..#OO#",
            "#O####.#E..#E.#",
            "#.....#...##.E#",
            "#.E#E......####",
            "#O#####.#.....#",
            "#..E#.#...##..#",
            "#...#.E...#E..#",
            "#..E###...#...#",
            "#...#E#..##O###",
            "#E..#.#.E#OOOO#",
            "#####...OOOO#O#",
            "#######.#...#G#",
            "###############"
        };

        String[] symmetricMap = new String[]{
            "##############",
            "#.E..#...#...#",
            "#S.#...#...#G#",
            "#.E..#...#...#",
            "##############"
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tervetuloa testaamaan luolastokeräilijää!");

        Dungeon dungeon = null;
        int energy = 0;

        while (true) {
            System.out.println("l: lataa luolasto");
            System.out.println("c: luo uusi luolasto");
            System.out.println("q: exit");

            String text = scanner.nextLine();
            if (text.equals("q")) {
                return;
            }

            if (text.equals("c")) {
                System.out.println("Anna luolaston leveys: ");
                int width = Integer.parseInt(scanner.nextLine());
                System.out.println("Anna luolaston korkeus: ");
                int height = Integer.parseInt(scanner.nextLine());
                System.out.println("Anna alussaoleva energiamäärä: ");
                energy = Integer.parseInt(scanner.nextLine());
                DungeonCreator creator = new DungeonCreator(width, height, energy);
                dungeon = new Dungeon(creator.createDungeon(false, 0));

            }

            if (text.equals("l")) {
                System.out.println("Anna tiedoston nimi, esim. dungeon.txt");
                String name = scanner.nextLine();

                String[] dungeonStrings = new String[200];
                File file = new File("");
                file = new File(file.getPath() + name);
                Scanner fileScanner = null;

                try {
                    fileScanner = new Scanner(file);
                } catch (Exception e) {
                    System.out.println("Ongelma: " + e);
                }
                int line = 0;
                while (fileScanner.hasNextLine()) {
                    dungeonStrings[line] = fileScanner.nextLine();
                    line++;
                }

                String[] map = new String[line];
                for (int val = 0; val < line; val++) {
                    map[val] = dungeonStrings[val];
                }
                dungeon = new Dungeon(map);
                
                System.out.println("Anna alussa oleva energiamäärä: ");
                energy = Integer.parseInt(scanner.nextLine());
            }

            // We print the dungeon once.
            for (int yval = 0; yval < dungeon.ySize(); yval++) {
                for (int xval = 0; xval < dungeon.xSize(); xval++) {
                    System.out.print("" + dungeon.getChar(xval, yval));
                }
                System.out.println("");
            }

            PhasedForager forager = new PhasedForager(dungeon);
            Forager simpleForager = new Forager(dungeon);

            //Pathfinding is initiated. A tile with 'S' is given as start, 'G' as goal.
            Step step = forager.searchPath(dungeon.findTileWithChar('S'),
                    dungeon.findTileWithChar('G'), energy, Heuristic.Manhattan);

            if (step == null) {
                System.out.println("No path found!");
            }

            System.out.println("Travel time: " + step.getTimeSpent());

            //We represent the path taken by putting all steps in a stack.
            //Then we calculate the in-between steps and insert them to a matrix.
            int[][] stepList = new int[dungeon.ySize()][dungeon.xSize()];

            java.util.Stack<Step> allSteps = new Stack<Step>();

            Step stackStep = step;

            while (stackStep != null) {
                allSteps.add(stackStep);
                stackStep = stackStep.getPreviousStep();
            }

            while (!allSteps.isEmpty()) {
                Step currentStep = allSteps.pop();

                if (allSteps.isEmpty()) {
                    break;
                }
                Step nextStep = allSteps.peek();
                Step betweenStep = simpleForager.searchPath(currentStep.getTile(), nextStep.getTile(), 30, Heuristic.Manhattan);
                betweenStep = betweenStep.getPreviousStep();

                while (betweenStep != null) {
                    stepList[betweenStep.getTile().getY()][betweenStep.getTile().getX()]++;
                    betweenStep = betweenStep.getPreviousStep();
                }
            }

            System.out.println("\n");

            // We print the dungeon again, showing number of visits on each tile on best path.
            for (int yval = 0; yval < dungeon.ySize(); yval++) {
                for (int xval = 0; xval < dungeon.xSize(); xval++) {
                    if (stepList[yval][xval] > 0) {
                        System.out.print(stepList[yval][xval]);
                    } else {
                        System.out.print("" + dungeon.getChar(xval, yval));
                    }
                }
                System.out.println("");
            }

            //Each step of the selected path is printed from end to beginning.
            //Visited special icons are rendered as normal floor dots.

            /*while (step != null) {
            for (int yval = 0; yval < dungeon.ySize(); yval++) {
                for (int xval = 0; xval < dungeon.xSize(); xval++) {
                    if (step.getTile().getX() == xval && step.getTile().getY() == yval) {
                        System.out.print("@");
                    } else if (dungeon.getChar(xval, yval) != '#' && step.getCycle().isSpecialSpent(dungeon.getTile(xval, yval))) {
                        System.out.print(".");
                    } else {
                        System.out.print("" + dungeon.getChar(xval, yval));
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            step = step.getPreviousStep();
        }*/
        }
    }
}
