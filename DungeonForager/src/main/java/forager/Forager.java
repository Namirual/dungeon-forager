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
public class Forager {

    Dungeon dungeon;
    int cycles;

    public Forager(Dungeon dungeon) {
        this.dungeon = dungeon;
        cycles = 1;
    }

    // Current search algorithm with a BFS base.
    public Step searchPath(Tile startTile, Tile goalTile, int energy) {
        LinkedList<Step> availableSteps = new LinkedList<>();

        Cycle cycle = new Cycle(null, startTile, dungeon);

        Step startState = new Step(startTile, null, cycle, energy, 0);

        for (Tile tile : dungeon.getAdjacentTiles(startTile.getX(), startTile.getY())) {
            availableSteps.add(new Step(tile, startState));
        }

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();

            currentStep.getCycle().setVisited(currentStep.getTile().getX(), currentStep.getTile().getY());

            if (currentStep.getTile().equals(goalTile)) {
                System.out.print("available steps: " + availableSteps.size() + " cycles: " + cycles);
                System.out.println(" energy " + currentStep.getEnergyLeft());
                return currentStep;
            }

            for (Tile tile : dungeon.getAdjacentTiles(currentStep.getTile().getX(), currentStep.getTile().getY())) {
                if (currentStep.getCycle().isVisited(tile.getX(), tile.getY())) {
                    continue;
                }
                Step newStep = handleNewStep(tile, currentStep);
                if (newStep != null) {
                    availableSteps.add(newStep);
                }
            }
        }
        return null;
    }
    
    // Checks if a step can be reached with energy and if a new cycle is needed.
    public Step handleNewStep(Tile tile, Step currentStep) {

        int energyLeft = currentStep.getEnergyLeft() - tile.getEnergyCost();
        if (energyLeft >= 0) {
            currentStep.getCycle().setVisited(tile.getX(), tile.getY());
            if (tile.getSpecialCost() == 0) {
                return new Step(tile, currentStep);
            } else if (currentStep.getCycle().isSpecialSpent(tile)) {
                return new Step(tile, currentStep);
            } else {
                Cycle newCycle = new Cycle(currentStep.getCycle(), tile, dungeon);
                cycles++;
                //System.out.println("New cycle! " + tile.getX() + "," + tile.getY());

                return new Step(tile, currentStep, newCycle);
            }
        }
        return null;
    }
}
