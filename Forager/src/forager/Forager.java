/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author lmantyla
 */
public class Forager {

    Dungeon dungeon;

    public Forager(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Step searchPath(Tile startTile, Tile goalTile, int energy) {
        LinkedList<Step> availableSteps = new LinkedList<>();

        Cycle cycle = new Cycle(startTile, new boolean[dungeon.map.length][dungeon.map[0].length]);

        Step startState = new Step(startTile, null, cycle, energy, 0);

        for (Tile tile : dungeon.getAdjacentTiles(startTile.getX(), startTile.getY())) {
            if (tile == null) {
                continue;
            } else {
                availableSteps.add(new Step(tile, startState));
            }
        }

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();
            currentStep.getCycle().visited[currentStep.getTile().getY()][currentStep.getTile().getX()] = true;

            if (currentStep.getTile().equals(goalTile)) {
                return currentStep;
            }

            for (Tile tile : dungeon.getAdjacentTiles(currentStep.getTile().getX(), currentStep.getTile().getY())) {
                if (tile == null) {
                    continue;
                }

                if (currentStep.getCycle().visited[tile.getY()][tile.getX()]) {
                    continue;
                }

                if (currentStep.getEnergyLeft() - tile.getEnergyCost() > 0) {
                    if (tile.getEnergyCost() == 1) {
                        availableSteps.add(new Step(tile, currentStep));
                    } else {
                        Cycle newCycle = new Cycle(startTile, new boolean[dungeon.map.length][dungeon.map[0].length]);
                        availableSteps.add(new Step(tile, currentStep, newCycle));
                    }
                }
            }
        }
        return null;
    }
}
