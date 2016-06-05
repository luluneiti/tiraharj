package tiraharj.algorithm;

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

}
