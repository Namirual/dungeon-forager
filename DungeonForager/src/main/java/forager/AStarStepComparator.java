package forager;

import forager.domain.Heuristic;
import forager.domain.Tile;
import forager.domain.Step;
import java.util.Comparator;

/**
 * A-Star comparator with three different heuristics.
 *
 * @author lmantyla
 */
public class AStarStepComparator implements Comparator<Step> {

    private final Tile goalTile;
    private final Heuristic heuristic;

    /**
     * Creates a new comparator for steps that uses heuristics.
     *
     * @param goalTile goal tile used by heuristics.
     */
    public AStarStepComparator(Tile goalTile) {
        this.goalTile = goalTile;
        this.heuristic = Heuristic.Manhattan;
    }

    /**
     * Creates a new comparator for steps that uses heuristics.
     *
     * @param goalTile goal tile used by heuristics.
     * @param heuristic heuristic used.
     */
    public AStarStepComparator(Tile goalTile, Heuristic heuristic) {
        this.goalTile = goalTile;
        this.heuristic = heuristic;
    }

    /**
     * Returns a particular heuristic value depending on the heuristic in use.
     *
     * @param currentTile The tile for which the heuristic value is created.
     * @return Estimated remaining time needed to reach the goal tile.
     */
    public double heuristic(Tile currentTile) {
        if (heuristic == Heuristic.Dijkstra) {
            return 0;
        }
        int x = Math.abs(this.goalTile.getX() - currentTile.getX());
        int y = Math.abs(this.goalTile.getY() - currentTile.getY());

        if (heuristic == Heuristic.Manhattan) {
            return x + y;
        }
        double distance = Math.sqrt((x * x) + (y * y));

        return (double) distance;
    }

    /**
     * Compares two tiles using a combination of time spent and a heuristic.
     *
     * @param t1 First step
     * @param t2 Second step
     * @return result of the comparison
     */
    @Override
    public int compare(Step t1, Step t2) {
        double val1 = heuristic(t1.getTile()) + t1.getTimeSpent();
        double val2 = heuristic(t2.getTile()) + t2.getTimeSpent();

        if (val1 - val2 > 0) {
            return 1;
        } else if (val1 - val2 < 0) {
            return -1;
        }
        return 0;
    }
}
