package forager;

import forager.domain.Heuristic;
import forager.domain.Tile;
import forager.domain.Step;
import forager.domain.Dungeon;
import forager.domain.Cycle;
import forager.structures.MyMinHeap;

/**
 * The main algorithm class.
 *
 * @author lmantyla
 */
public class Forager {

    Dungeon dungeon;
    int cycles;

    /**
     * Initializes Forager with a dungeon.
     *
     * @param dungeon Dungeon the search takes place in.
     */
    public Forager(Dungeon dungeon) {
        this.dungeon = dungeon;
        cycles = 1;
    }

    /**
     * Main search algorithm that utilises A*.
     *
     * @param startTile Starting point of the search.
     * @param goalTile Goal of the search.
     * @param energy Starting energy for the search.
     * @param heuristic Heuristic used by MyMinHeap.
     * @return first step to reach the goal.
     */
    public Step searchPath(Tile startTile, Tile goalTile, int energy, Heuristic heuristic) {
        MyMinHeap<Step> availableSteps = new MyMinHeap<Step>(new AStarStepComparator(goalTile, Heuristic.Dijkstra));

        Cycle cycle = new Cycle(null, startTile, dungeon);
        Step startState = new Step(startTile, null, cycle, energy, 0);

        availableSteps.add(startState);

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();

            currentStep.getCycle().setVisited(currentStep.getTile().getX(), currentStep.getTile().getY());

            if (currentStep.getTile().equals(goalTile)) {
                /*System.out.print("available steps: " + availableSteps.size() + " cycles: " + cycles);
                System.out.println(" energy " + currentStep.getEnergyLeft());*/
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

    /**
     * Checks if a step can be reached with current energy and if a new cycle is
     * needed.
     *
     * @param tile Tile being examined
     * @param currentStep step from which tile has been reached
     * @return new step, null if not enough energy.
     */
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

                return new Step(tile, currentStep, newCycle);
            }
        }
        return null;
    }
}
