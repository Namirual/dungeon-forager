package forager.domain;

/**
 * Stores a pre-calculated best possible route from one special tile to another.
 *
 * @author lmantyla
 */
public class Line {

    Tile targetTile;
    int energyCost;
    int timeCost;

    /**
     * Creates a new line.
     *
     * @param targetTile the tile the path leads to
     * @param energyCost the amount of energy the path requires
     * @param timeCost the amount of time the path requires
     */
    public Line(Tile targetTile, int energyCost, int timeCost) {
        this.targetTile = targetTile;
        this.energyCost = energyCost;
        this.timeCost = timeCost;
    }

    /**
     * Returns the amount of energy used by the path.
     *
     * @return energy cost.
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Returns the amount of time used by the path.
     *
     * @return time cost
     */
    public int getTimeCost() {
        return timeCost;
    }

    /**
     * Returns the tile the path leads to.
     *
     * @return target tile
     */
    public Tile getTargetTile() {
        return targetTile;
    }

    @Override
    public String toString() {
        return targetTile + " E:" + energyCost + " T:" + timeCost + "  ";
    }
}
