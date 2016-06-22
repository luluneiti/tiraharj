package tiraharj.algorithm;

import tiraharj.Location;
import tiraharj.Node;

/**
 * Kuvaa heuristiikkaan liittyvät palvelut
 * @author Ulla
 */
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
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return naapurisolmujen koordinaatit
     */
    public abstract Location[] getNeighborsCoordinates(int x, int y);
}
