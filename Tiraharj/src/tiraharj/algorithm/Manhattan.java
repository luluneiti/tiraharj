package tiraharj.algorithm;

import tiraharj.Location;
import tiraharj.Node;

/**
 * Tarjoaa etäisyyden ja naapureiden päättelyn manhattan periaatteella
 *
 * @author Ulla
 */
public class Manhattan implements Heuristic {

    @Override
    public int getToEnd(Node a, Node b) {
        return abs(a.getX() - b.getX()) + abs(a.getY() - b.getY());
//        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private int abs(int a) {
        return (a < 0) ? -a : a;
    }

    @Override
    public Location[] getNeighborsCoordinates(int x, int y) {

        Location[] coordinates = new Location[4];
        coordinates[0] = new Location(x + 1, y);
        coordinates[1] = new Location(x - 1, y);
        coordinates[2] = new Location(x, y + 1);
        coordinates[3] = new Location(x, y - 1);

        return coordinates;
    }

}
