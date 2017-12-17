package forager.domain;

import forager.domain.Line;
import forager.domain.Cycle;

/**
 * A state class that contains information about each step.
 *
 * @author lmantyla
 */
public class Step {

    private final Tile tile;
    private final Step previousStep;
    private final Cycle cycle;

    private final int energyLeft;
    private final int timeSpent;

    /**
     * New step when visiting a normal tile while continuing an existing cycle.
     *
     * @param tile The new tile that was reached
     * @param previousStep Step from which the tile was reached.
     */
    public Step(Tile tile, Step previousStep) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = previousStep.cycle;
        this.energyLeft = previousStep.energyLeft - tile.getEnergyCost();
        this.timeSpent = previousStep.timeSpent + tile.getTimeCost();
    }

    /**
     * New step with a new cycle. Used when visiting a special tile for the
     * first time.
     *
     * @param tile The new tile that was reached.
     * @param previousStep Step from which the tile was reached.
     * @param cycle New cycle started for this step.
     */
    public Step(Tile tile, Step previousStep, Cycle cycle) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = cycle;
        this.energyLeft = previousStep.energyLeft - tile.getSpecialCost();
        this.timeSpent = previousStep.timeSpent + tile.getTimeCost();
    }

    /**
     * New step in which a default value is removed from energy. Used when a
     * special tile has already been visited previously, so it is treated as a
     * normal tile with particular default weights.
     *
     * @param tile The new tile that was reached
     * @param previousStep Step from which the tile was reached.
     * @param defaultValue Default energy value.
     */
    public Step(Tile tile, Step previousStep, int defaultValue) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = previousStep.cycle;
        this.energyLeft = previousStep.energyLeft - defaultValue;
        this.timeSpent = previousStep.timeSpent + defaultValue;
    }

    /**
     * New step when the energy cost is taken from a pre-processed best path
     * between two tiles.
     *
     * @param line Pre-processed best path between two tiles.
     * @param previousStep Step from which the tile was reached.
     * @param cycle New cycle started for this step.
     */
    public Step(Line line, Step previousStep, Cycle cycle) {
        this.tile = line.getTargetTile();
        this.previousStep = previousStep;
        this.cycle = cycle;
        this.energyLeft = previousStep.getEnergyLeft() - line.getEnergyCost() - line.getTargetTile().getSpecialCost();
        this.timeSpent = previousStep.getTimeSpent() + line.getTimeCost();
    }

    /**
     * New step with given values. Used mainly for testing.
     *
     * @param tile The new tile that was reached
     * @param previousStep Step from which the tile was reached.
     * @param cycle New cycle started for this step.
     * @param energyLeft Amount of energy left at this step
     * @param timeSpent Amount of time spent up to this step
     */
    public Step(Tile tile, Step previousStep, Cycle cycle, int energyLeft, int timeSpent) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = cycle;
        this.energyLeft = energyLeft;
        this.timeSpent = timeSpent;
    }

    /**
     * Returns the tile reached in this step.
     *
     * @return Tile.
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Returns the step this tile was freached from.
     *
     * @return Step.
     */
    public Step getPreviousStep() {
        return previousStep;
    }

    /**
     * Returns the cycle of steps this step belongs to.
     *
     * @return cycle.
     */
    public Cycle getCycle() {
        return cycle;
    }

    /**
     * Returns the amount of energy left in this step.
     *
     * @return Remaining energy.
     */
    public int getEnergyLeft() {
        return energyLeft;
    }

    /**
     * Returns the amount of time spent from the start of the search to this
     * step.
     *
     * @return Time spent.
     */
    public int getTimeSpent() {
        return timeSpent;
    }
}
