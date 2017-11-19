/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * The main algorithm class.
 *
 * @author lmantyla
 */
public class Forager {

    Dungeon dungeon;

    public Forager(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    // Current search algorithm with a BFS base.
    public Step searchPath(Tile startTile, Tile goalTile, int energy) {
        LinkedList<Step> availableSteps = new LinkedList<>();

        Cycle cycle = new Cycle(null, startTile, new boolean[dungeon.ySize()][dungeon.xSize()]);

        Step startState = new Step(startTile, null, cycle, energy, 0);

        for (Tile tile : dungeon.getAdjacentTiles(startTile.getX(), startTile.getY())) {
            availableSteps.add(new Step(tile, startState));
        }

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();

            currentStep.getCycle().setVisited(currentStep.getTile().getX(), currentStep.getTile().getY());

            if (currentStep.getTile().equals(goalTile)) {
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
        if (currentStep.getEnergyLeft() - tile.getEnergyCost() > 0) {
            if (tile.getEnergyCost() == 1) {
                return new Step(tile, currentStep);
            } else if (currentStep.getCycle().isSpecialUsed(tile)) {
                return new Step (tile, currentStep, 1);
            } else {
                Cycle newCycle = new Cycle(currentStep.getCycle(), tile, new boolean[dungeon.ySize()][dungeon.xSize()]);;
                return new Step(tile, currentStep, newCycle);
            }
        }
        return null;
    }
}
