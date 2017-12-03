/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import forager.structures.VisitTree;
import forager.structures.MyMinHeap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A*-based search algorithm that operates in two phases.
 *
 * @author lmantyla
 */
public class PhasedForager {

    Dungeon dungeon;
    int cycles;
    int reuses;
    int highestNumberOfCycles = 0;
    VisitTree visitTree;

    public PhasedForager(Dungeon dungeon) {
        this.dungeon = dungeon;
        cycles = 1;
    }

    // Second phase search algorithm that calculates between special tiles.
    public Step searchPath(Tile startTile, Tile goalTile, int energy) {
        //PriorityQueue<Step> availableSteps = new PriorityQueue<>(new AStarStepComparator(goalTile));
        MyMinHeap<Step> availableSteps = new MyMinHeap<Step>(new AStarStepComparator(goalTile));

        createNeigborLists(20, startTile, goalTile);

        VisitMap visitMap = new VisitMap(dungeon);
        visitTree = new VisitTree(dungeon.getSpecialTiles().size());
        visitTree.addVisitMap(visitMap);

        Cycle cycle = new Cycle(null, startTile, visitMap);

        Step startState = new Step(startTile, null, cycle, energy, 0);

        availableSteps.add(startState);

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();

            currentStep.getCycle().setVisited(currentStep.getTile().getX(), currentStep.getTile().getY());

            if (currentStep.getTile().equals(goalTile)) {
                System.out.print("available steps: " + availableSteps.size() + " cycles: " + cycles);
                System.out.println(" Reuses: " + reuses);
                System.out.println(" energy left: " + currentStep.getEnergyLeft());
                return currentStep;
            }

            for (Line line : currentStep.getTile().getNeighbors()) {
                Step newStep = handleNewStep(line, currentStep);
                if (newStep != null) {
                    availableSteps.add(newStep);
                }
            }
        }
        return null;
    }

    // Checks if a step can be reached with energy and if a new cycle is needed.
    public Step handleNewStep(Line line, Step currentStep) {
        Tile tile = line.getTargetTile();
        int energyLeft = currentStep.getEnergyLeft() - line.getEnergyCost();
        if (energyLeft > 0 && energyLeft - tile.getSpecialCost() > 0) {
            currentStep.getCycle().setVisited(tile.getX(), tile.getY());

            if (tile.getSpecialCost() == 0) {
                return null;
            } else if (currentStep.getCycle().isSpecialSpent(tile)) {
                return new Step(tile, currentStep, line.getTimeCost());
            } else {
                Cycle newCycle = createNewCycle(currentStep, tile);
                if (newCycle != null) {
                    return new Step(line, currentStep, newCycle);
                }
            }
        }
        return null;
    }

    // Creates a new cycle with either the old visit map or the new one.
    public Cycle createNewCycle(Step currentStep, Tile tile) {
        VisitMap visitMap = new VisitMap(currentStep.getCycle().getVisitMap(), tile);

        Cycle newCycle;

        VisitMap existingMap = visitTree.findVisitMap(visitMap);

        if (existingMap != null) {
            if (existingMap.isVisited(tile.getX(), tile.getY())) {
                return null;
            }

            /*for (int val = 0; val < visitMap.getSpecialVisited().length; val++) {
                if (visitMap.getSpecialVisited()[val]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            for (int val = 0; val < visitMap.getSpecialVisited().length; val++) {
                        if (currentStep.getCycle().getVisits().getSpecialVisited()[val]) {
                            System.out.print("1");
                        } else {
                            System.out.print("0");
                        }
                    }
            System.out.println("");*/

            newCycle = new Cycle(currentStep.getCycle(), tile, existingMap);
            reuses++;
        } else {
            newCycle = new Cycle(currentStep.getCycle(), tile, visitMap);
            visitTree.addVisitMap(visitMap);
            cycles++;

            if (visitMap.specialTilesVisited > highestNumberOfCycles) {
                System.out.println("Cycle depth: " + visitMap.specialTilesVisited);
                highestNumberOfCycles = visitMap.specialTilesVisited;
            }
        }
        return newCycle;
    }

    public void createNeigborLists(int maxEnergy, Tile startTile, Tile goalTile) {
        for (int yval = 0; yval < dungeon.ySize(); yval++) {
            for (int xval = 0; xval < dungeon.xSize(); xval++) {
                Tile tile = dungeon.getTile(xval, yval);
                if (tile != null && tile.getSpecialCost() != 0) {
                    tile.setNeighbors(findAccessibleSpecialTiles(tile, maxEnergy));

                    /*System.out.println(tile);
                    for (Line neightile : tile.getNeighbors()) {
                        System.out.print(neightile);
                    }
                    System.out.println("");*/
                }
            }
        }
        startTile.setNeighbors(findAccessibleSpecialTiles(startTile, maxEnergy));

        goalTile.setNeighbors(findAccessibleSpecialTiles(goalTile, maxEnergy));
    }

    // Calculates all possible routes from each special tile to other special tiles.
    public List<Line> findAccessibleSpecialTiles(Tile startTile, int maxEnergy) {
        LinkedList<Step> availableSteps = new LinkedList<>();
        List<Line> lines = new ArrayList<Line>();

        Cycle cycle = new Cycle(null, startTile, dungeon);
        cycle.setVisited(startTile.getX(), startTile.getY());

        Step startState = new Step(startTile, null, cycle, maxEnergy, 0);

        availableSteps.add(new Step(startTile, startState));

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();

            //currentStep.getCycle().setVisited(currentStep.getTile().getX(), currentStep.getTile().getY());
            for (Tile tile : dungeon.getAdjacentTiles(currentStep.getTile().getX(), currentStep.getTile().getY())) {
                if (currentStep.getCycle().isVisited(tile.getX(), tile.getY())) {
                    continue;
                }
                if (currentStep.getEnergyLeft() <= 0) {
                    continue;
                }

                currentStep.getCycle().setVisited(tile.getX(), tile.getY());

                if (tile.getSpecialCost() == 0) {
                    availableSteps.add(new Step(tile, currentStep));
                } else {
                    Line shortcut = new Line(tile, maxEnergy - currentStep.getEnergyLeft(),
                            currentStep.getTimeSpent());
                    lines.add(shortcut);
                }
            }
        }
        return lines;
    }
}
