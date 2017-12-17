package forager;

import forager.domain.Heuristic;
import forager.domain.VisitMap;
import forager.domain.Tile;
import forager.domain.Step;
import forager.domain.Line;
import forager.domain.Dungeon;
import forager.domain.Cycle;
import forager.structures.MyArrayList;
import forager.structures.VisitTree;
import forager.structures.MyMinHeap;

/**
 * A*-based search algorithm that operates in two phases.
 *
 * @author lmantyla
 */
public class PhasedForager {

    private Dungeon dungeon;
    private boolean doReuse;

    private int cycles;
    private int reuses;

    private int highestNumberOfCycles = 0;
    private VisitTree visitTree;

    /**
     * Initialises the forager for a particular dungeon.
     *
     * @param dungeon Dungeon the forager is being used for.
     */
    public PhasedForager(Dungeon dungeon) {
        this(dungeon, true);
    }

    /**
     * Initialises the forager for a particular dungeon.
     *
     * @param dungeon Dungeon the forager is being used for.
     * @param reusing true to reuse VisitMaps, false to not do so.
     */
    public PhasedForager(Dungeon dungeon, boolean reusing) {
        this.dungeon = dungeon;
        visitTree = new VisitTree(dungeon.getSpecialTiles().size());
        doReuse = reusing;
        cycles = 1;
        reuses = 0;
    }

    /**
     * Two-phased search algorithm that simplifies the search by first
     * calculating by first calculating routes between all special tiles.
     *
     * @param startTile the starting tile of the search.
     * @param goalTile the goal tile for the search.
     * @param energy amount of energy at the beginning of the search.
     * @param heuristic heuristic to be used by the heap.
     * @return the step that first reached the goal tile if successful, null if
     * not.
     */
    public Step searchPath(Tile startTile, Tile goalTile, int energy, Heuristic heuristic) {
        MyMinHeap<Step> availableSteps = new MyMinHeap<Step>(new AStarStepComparator(goalTile, heuristic));

        createNeigborLists(500, startTile, goalTile);

        VisitMap visitMap = new VisitMap(dungeon);
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

    /**
     * Creates a new step if the check is possible with the energy available and
     * checks if a new cycle is needed for the new step.
     *
     * @param line Line from the current step currently under consideration to a
     * new tile.
     * @param currentStep The step from which the line originates.
     * @return new Step if the step is possible, null if not.
     */
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

    /**
     * Creates a new cycle with either the old visit map or the new one. A new
     * visit map based on the previous one and the newly reached tile is
     * created, and then used to check if an earlier one with the same explored
     * special tiles exists. If so, the earlier visit map is used.
     *
     * @param currentStep The step from which the line originates
     * @param tile The tile that was just reached.
     * @return new cycle, null if the tile was already explored in the
     * pre-existing visit map.
     */
    public Cycle createNewCycle(Step currentStep, Tile tile) {
        VisitMap visitMap = new VisitMap(currentStep.getCycle().getVisitMap(), tile);

        Cycle newCycle;

        VisitMap existingMap = visitTree.findVisitMap(visitMap);

        if (doReuse && existingMap != null) {
            newCycle = new Cycle(currentStep.getCycle(), tile, existingMap);
            reuses++;
        } else {

            newCycle = new Cycle(currentStep.getCycle(), tile, visitMap);
            visitTree.addVisitMap(visitMap);
            cycles++;
        }
        return newCycle;
    }

    /**
     * Handles creation of neighbor lists for all special tiles.
     *
     * @param maxEnergy Maximum amount of energy considered for each step.
     * @param startTile the start tile for the entire search
     * @param goalTile the goal tile for the search
     */
    public void createNeigborLists(int maxEnergy, Tile startTile, Tile goalTile) {
        for (Tile tile : dungeon.getSpecialTiles()) {
            tile.setNeighbors(findAccessibleSpecialTiles(tile, maxEnergy));
        }

        startTile.setNeighbors(findAccessibleSpecialTiles(startTile, maxEnergy));

        goalTile.setNeighbors(findAccessibleSpecialTiles(goalTile, maxEnergy));
    }

    /**
     * Calculates the best route from one special tile to every other special
     * tile at range using Dijkstra with a fixed range limit.
     *
     * @param startTile Tile
     * @param maxEnergy Maximum distance in energy for the search.
     * @return MyArrayList object containing all possible routes from
     * startTiles.
     */
    public MyArrayList<Line> findAccessibleSpecialTiles(Tile startTile, int maxEnergy) {
        MyMinHeap<Step> availableSteps = new MyMinHeap<Step>(new AStarStepComparator(startTile, Heuristic.Dijkstra));

        MyArrayList<Line> lines = new MyArrayList<Line>();

        Cycle cycle = new Cycle(null, startTile, dungeon);
        cycle.setVisited(startTile.getX(), startTile.getY());

        Step startState = new Step(startTile, null, cycle, maxEnergy, 0);

        availableSteps.add(new Step(startTile, startState));

        while (availableSteps.size() > 0) {
            Step currentStep = availableSteps.poll();

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

    /**
     * Returns the root of the visit tree used by PhasedForager. Only needed in
     * tests.
     *
     * @return Root of the visit tree
     */
    public VisitTree getVisitTree() {
        return visitTree;
    }

    /**
     * Returns the amount of reuses of VisitMaps during a run. Used for
     * diagnostics and tests.
     *
     * @return number of reuses
     */
    public int getReuses() {
        return reuses;
    }

    /**
     * Returns the amount of new cycles with a new VisitMap during a run. Used
     * for diagnostics and tests.
     *
     * @return number of cycles
     */
    public int getCycles() {
        return cycles;
    }
}
