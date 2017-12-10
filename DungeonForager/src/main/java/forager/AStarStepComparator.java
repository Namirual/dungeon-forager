package forager;

import java.util.Comparator;

/**
 *
 * @author lmantyla
 */
public class AStarStepComparator implements Comparator<Step> {

    private final Tile goalTile;
    private final Heuristic heuristic;

    public AStarStepComparator(Tile goalTile) {
        this.goalTile = goalTile;
        this.heuristic = Heuristic.Manhattan;
    }

    public AStarStepComparator(Tile goalTile, Heuristic heuristic) {
        this.goalTile = goalTile;
        this.heuristic = heuristic;
    }

    /**
     * Diagonal distance heuristic.
     *
     * @param stop
     * @return Estimated remaining time
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
     * Comparator
     *
     * @param t1
     * @param t2
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