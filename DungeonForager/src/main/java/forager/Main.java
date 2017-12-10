/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import forager.creator.*;
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
        String[] stringMap2 = new String[]{
            "##E.#...###....",
            ".S...E.........",
            "..#.#.#.#..#.E#",
            "#.####.#E..#...",
            "......#E..###.#",
            "...#..E..E..#..",
            "..###.#.#......",
            ".####.#...##...",
            "##.##.E..##E..#",
            "...##.....##...",
            ".#..#.#..###...",
            ".E#####.E##....",
            "###....#....#E.",
            ".######.#...#G.",
            "...###.#...##.."
        };

        String[] stringMap3 = new String[]{
            "#E#.#..######....##.",
            ".S...E.........E....",
            "#.#.#..#...#....##.#",
            "..##.#..##.....#....",
            ".#.#...#.E.....#....",
            "##...###.#..#E.....#",
            ".##..#....##...##..#",
            "###.#.....#...#.##E.",
            "E..#.#........#.....",
            "##..###..##E#.......",
            ".E#.#.##..#..#..#...",
            "..#...##..#.#..#.#..",
            "....#.#...#.#.E..#.#",
            "..E..#E.#...#.#....#",
            ".....#.#.###.#.....#",
            "E.....####...#.#.E..",
            "..##...E.....#..##.#",
            ".....##.#...#.....E#",
            "...#...#.#.##.#.#.G.",
            "...###.#.#..#.##...#"
        };

        String[] stringMap4 = new String[]{
            "..#..#.#.......#...#.#......E.#...#..##..E##.E#.##",
            ".S...E.........E.........E.........E.........E....",
            ".#..E.#E.##..#....#...##..#..##...##..##...##..#..",
            "#.#...#...#..###.#.##..#.#.E.#...#...#.......#....",
            "..#.#....#.#E##..#EE..#EE.E.#....#......#.#...##.#",
            "##.##.....E.#.E##..E...##....E.##.#.....##.....#..",
            "##.#....#.##....#....##.#..........###..#.#.E.#...",
            "..###.#...##...#.....##.#####..#.###.#.#...#...#E.",
            "#.......#..#....#..#..#.#.....#..E.#...#####.##..E",
            "...#.##.#.###.####.......##...##.#...####..#...#..",
            "##....#.....#.#.......E#...##..E......####..#.....",
            "####.###..#.#..#.##..#.#.#....##..EE..E#.....#...#",
            "..#..##..#...E.##.#..E.#.#...#.E#.##....###.#..E.#",
            ".E#...#....#........#.#.#.###....#...E....##.....#",
            "..#.#....E.###....#.##..#..#.......#....E...###...",
            "#.#....#.......#.##.E###E.##....#..##..##E..#.##.#",
            "###.##...#..#..###..##..E#.#...##E#.##..#...E.#...",
            "####....#..###...#.#.......E#....#..##...##.##..E.",
            "...#..##..###....E...#E#.#.....E.##E.#..#.....#...",
            ".#.#..##......#.#....#....###...##.#.....#.#E#E...",
            "..E##.E##..#............#..####..##.##....#...#..#",
            ".E..#.....#..#.........#...#..E.#E##.#...#.E#.##.#",
            "####...####.#..#....#.####E#..##..#..#..#.#####..#",
            "..E..E..####...##..##..#E..E.#..#..#.##...#.#....E",
            ".#.E.....#E.###.....#.#EE#......#.E....###.#.#...#",
            "...#.....##...##...###.....##.##..##...###....#...",
            "........E.#.#.#.#.#E#...#.#......#.#....##.#..#..#",
            "#..#....E.#..##.#.###..E.#...##..##E.#.##.#E....E.",
            ".#.......#...E.#....#.....##E....#...##E.#..#.....",
            "...###.#....##...#...##.....###..##.#..........#..",
            "#..#....#.####.......#.#..##..##.##E..........#..E",
            "E#.#.#.#.#....##..###.##.##..#.E...E.#.......#.#..",
            "##..##..####..E.#.#.#.....##..#.....##E.####...E..",
            "..E...#E..#..#.#...#...#.....#..E.##..#.#......#..",
            "#.##.#.#.#..###.#..#.#.###...#..###.#..#.##......#",
            "##.#.#E....#.#.#....#E#..#.##.#.E.#.#....E...#...#",
            "...#.#..####.#E.....E...#.##...#...#E..###..#..#..",
            "...##.#....E.#....##.....##...E.#..#.E...#.##E##E.",
            "E..##..#..E..###...###....##E..###..........#..E.E",
            ".#.##..#.......##..##.####.#.E#.#E...#...#.E.#.#..",
            "####.E.#....###.##.###............#..#....###..#..",
            "#####.E#...#..#..E...#..#.#....#..#......#........",
            "#.#.E.....######.E.#.#..#....#.E#...#####...E#.#.#",
            "###.E#..E.#E###....#..#..#...###EE..##..##..#.##..",
            "....#..######.#.#.....#....##.#.#.###.#.#.#..#....",
            ".###..#####.#E#EE.......#...#...#.#.#######....E.#",
            ".#..E.#..#...#.##.#....##.#.#.#E.#.E...E.......#..",
            "#...##.#.#......###.##E......E#..#..#..#.##.##..E#",
            ".......##..##..#...#.#.#.#..E#E##.#..#..##..#.#.GE",
            "##.......#.......#....##...#......#...#.#.#..#...#"
        };

        DungeonCreator creator = new DungeonCreator(60, 60, 5);

        //Dungeon dungeon = new Dungeon(stringMap);
        Dungeon dungeon = new Dungeon(creator.createDungon());

        PhasedForager forager = new PhasedForager(dungeon);
        Forager simpleForager = new Forager(dungeon);

        //Pathfinding is initiated. A tile with 'S' is given as start, 'G' as goal.
        Step step = forager.searchPath(dungeon.findTileWithChar('S'),
                dungeon.findTileWithChar('G'), 6);

        if (step == null) {
            System.out.println("No path found!");
            return;
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
            Step betweenStep = simpleForager.searchPath(currentStep.getTile(), nextStep.getTile(), 30);
            betweenStep = betweenStep.getPreviousStep();

            while (betweenStep != null) {
                stepList[betweenStep.getTile().getY()][betweenStep.getTile().getX()]++;
                betweenStep = betweenStep.getPreviousStep();
            }
        }

        // We print the dungeon once.
        for (int yval = 0; yval < dungeon.ySize(); yval++) {
            for (int xval = 0; xval < dungeon.xSize(); xval++) {
                System.out.print("" + dungeon.getChar(xval, yval));
            }
            System.out.println("");
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
