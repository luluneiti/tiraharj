package tiraharj.algorithm;

import tiraharj.Location;
import tiraharj.Node;

public interface Heuristic {

    /**
     * Palauttaa suoran etäisyyden solmusta a solmuun b
     *
     * @param a mistä solmusta
     * @param b mihin solmuu
     * @return etäisyys
     */
    public abstract int getToEnd(Node a, Node b);

    /**
     * Palauttaa koordinaatit, joihin ko. heuristiikassa voidaan liikkua
     * @param x
     * @param y
     * @return koordinaatit
     */
    public abstract Location[] getNeighborsCoordinates(int x, int y);
}
