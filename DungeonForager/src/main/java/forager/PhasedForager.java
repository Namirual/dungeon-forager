/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The main algorithm class.
 *
 * @author lmantyla
 */
public class PhasedForager {

    Dungeon dungeon;
    int cycles;

    public PhasedForager(Dungeon dungeon) {
        this.dungeon = dungeon;
        cycles = 1;
    }

    // Current search algorithm that calculates between special tiles.
    public Step searchPath(Tile startTile, Tile goalTile, int energy) {
        PriorityQueue<Step> availableSteps = new PriorityQueue<>(new AStarStepComparator(goalTile));

        createNeigborLists(15, startTile, goalTile);

        Cycle cycle = new Cycle(null, startTile, dungeon);

        Step startState = new Step(startTile, null, cycle, energy, 0);

        availableSteps.add(startState);
        
        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();
            
            currentStep.getCycle().setVisited(currentStep.getTile().getX(), currentStep.getTile().getY());

            if (currentStep.getTile().equals(goalTile)) {
                System.out.print("available steps: " + availableSteps.size() + " cycles: " + cycles);
                System.out.println(" energy " + currentStep.getEnergyLeft());
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
                //return new Step(tile, currentStep);
                return null;
            } else if (currentStep.getCycle().isSpecialUsed(tile)) {
                return new Step(tile, currentStep, line.getTimeCost());
            } else {
                Cycle newCycle = new Cycle(currentStep.getCycle(), tile, dungeon);
                cycles++;
                return new Step(line, currentStep, newCycle);
            }
        }
        return null;
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
        /*for (Tile neightile : startTile.getNeighbors()) {
            System.out.print(neightile + " " + neightile.getSpecialCost() + ",  ");
        }*/
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
