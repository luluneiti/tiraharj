package tiraharj.algorithm;

import tiraharj.Location;
import tiraharj.Node;
import tiraharj.algorithm.Heuristic;

public class Manhattan implements Heuristic {

    @Override
    public int getToEnd(Node a, Node b) {

        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());

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