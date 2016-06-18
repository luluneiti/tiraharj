package tiraharj.algorithm;

import tiraharj.Graph;
import tiraharj.Node;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;

public interface ShortestPath {

    /**
     * Asettaa tilastoluokan, jotta algoritmin toiminnasta saadaan tilastoa
     * @param stat tilastoluokka olio
     */
    public abstract void setStatistic(Statistic stat);

    /**
     * Lyhimmän polun etsivät algoritmi. 
     * Kaikki algoritmit käyttävät heuristiikkaa. Dijkstra vain naapureiden päättelyyn.
     * @param graph verkko
     * @param start aloitussolmu
     * @param goal maalisolmu
     * @param heuristic etsinnässä käytettävä heuristiikka
     */
    public abstract void findPath(Graph graph, Node start, Node goal, Heuristic heuristic);

    /**
     * Palauttaa löydetyn lyhimmän polun pinossa
     * @param graph verkko
     * @param start aloitussolmu
     * @param goal maalisolmu
     * @return lyhin polku pinossa
     */
    public abstract StackO getPathInStack(Graph graph, Node start, Node goal);

    /**
     * Palauttaa true/false riippuen siitä löytyikö lyhin polku, eli oliko maalisolmu tavoitettavissa
     * @return true:maalisolmu ei tavoitettu, false: lyhin polku löydettiin
     */
    public abstract boolean emptyPath();

    /**
     * Metodi tulostaa lyhimmän polun
     * @param graph verkko
     * @param start lähtösolmu
     * @param goal maalisolmu
     * @param algorithm
     */
    public default void printPath(Graph graph, Node start, Node goal, ShortestPath algorithm) {
        
        if (!algorithm.emptyPath()) {
            StackO stack = algorithm.getPathInStack(graph, start, goal);
            System.out.println("Shortest route: ");

            while (!stack.isEmpty()) {
                System.out.println(graph.getXYByPointId(stack.pop()));
            }
        } else {
            System.out.println("Verkko ei ollut yhtenäinen ja maalisolmua ei saavutettu");
        }
    }

    /**
     * Alustaa etäisyydet "äärettömäksi"
     * @param size taulukon koko
     * @return alustettu taulu
     */
    public default int[] initTable(int size) {
        int[] initDist = new int[size];
        for (int i = 0; i < initDist.length; i++) {
            initDist[i] = Integer.MAX_VALUE;
        }
        return initDist;
    }
}
