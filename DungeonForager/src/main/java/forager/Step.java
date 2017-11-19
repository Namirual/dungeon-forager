/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forager;

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

    // New step created when visiting a normal tile, continuing previous cycle.
    public Step(Tile tile, Step previousStep) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = previousStep.cycle;
        this.energyLeft = previousStep.energyLeft - tile.getEnergyCost();
        this.timeSpent = previousStep.timeSpent + tile.getTimeCost();
    }

    // new step with a new cycle. Used when visiting a special tile for the first time.
    public Step(Tile tile, Step previousStep, Cycle cycle) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = cycle;
        this.energyLeft = previousStep.energyLeft - tile.getEnergyCost();
        this.timeSpent = previousStep.timeSpent + tile.getTimeCost();
    }

    // New step in which a default value is removed from energy. Used when a special
    // tile has already been visited previously, turning it into a normal tile.
    public Step(Tile tile, Step previousStep, int defaultValue) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = previousStep.cycle;
        this.energyLeft = previousStep.energyLeft - defaultValue;
        this.timeSpent = previousStep.timeSpent + defaultValue;
    }
    
    public Step(Tile tile, Step previousStep, Cycle cycle, int energyLeft, int timeSpent) {
        this.tile = tile;
        this.previousStep = previousStep;
        this.cycle = cycle;
        this.energyLeft = energyLeft;
        this.timeSpent = timeSpent;
    }

    public Tile getTile() {
        return tile;
    }

    public Step getPreviousStep() {
        return previousStep;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public int getEnergyLeft() {
        return energyLeft;
    }

    public int getTimeSpent(int timeSpent) {
        return timeSpent;
    }
}
