package tiraharj;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import tiraharj.Graph;
import tiraharj.Location;
import tiraharj.Node;
import tiraharj.algorithm.Astar;
import tiraharj.algorithm.Dijkstra;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.IDAStar;
import tiraharj.algorithm.Manhattan;
import tiraharj.algorithm.ShortestPath;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.Heap;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;
import tiraharj.tools.TernaryHeap;

/**
 * Käyttöliittymän/pääohjelman pyynnöstä luo tarvittavat oliot ja käynnistää
 * pyydetyn algoritmin sekä näyttää lyhimmän polun
 *
 * @author Ulla
 */
public class ApplicationLogic {

    private ShortestPath algorithm;
    private Graph graph;
    private boolean errorStatus;

    /**
     * Konstruktori
     */
    public ApplicationLogic() {
        this.errorStatus = false;
    }

    /**
     * Luo tarvittavat oliot ja käynnistää lyhimmän polun etsinnän halutulla
     * algoritmilla
     *
     * @param matrix verkko
     * @param obstacles esteet
     * @param algorithmName ajettava algoritmi
     * @param heuristicName käytettävä heuristiikka
     * @param heapName käytettävä keko-ratkaisu
     * @param startX lähtösolmu x
     * @param startY lähtösolmu y
     * @param goalX maalisolmu x
     * @param goalY maalisolmu y
     * @return ajon tilasto tai virheilmoitus
     */
    public String runAlgorithm(int[][] matrix, Location[] obstacles, String algorithmName, String heuristicName, String heapName, int startX, int startY, int goalX, int goalY) {

        String ret = "";
        graph = new Graph(matrix);
        boolean[] obst = new boolean[graph.getNodeAmount() + 1];

        if (graph.isReachable(startX, startY) && graph.isReachable(goalX, goalY)) {
            if (obstacles.length > 0) {
                for (Location loc : obstacles) {
                    if (loc != null) {
//                        System.out.println(loc.getX()+" "+loc.getY());
                        if (graph.isReachable(loc.getX(), loc.getY())) {
                            obst[graph.getPointId(loc.getX(), loc.getY())] = true;
                        }
                        if (!graph.isReachable(loc.getX(), loc.getY())) {
                            errorStatus = true;
                        }
                    }
                }
            }
            graph.setObstacles(obst);
            Statistic stat = new Statistic();
            Heap heap = createHeap(heapName, graph);
            run(algorithmName, heap, stat, graph, startX, startY, goalX, goalY);
            ret = stat.toString();
        }
        if (!graph.isReachable(startX, startY) || !graph.isReachable(goalX, goalY) || errorStatus == true) {
            errorStatus = true;
            ret = "Tarkista lähtö- ja maalisolmujen arvot!";
        }
        return ret;
    }

    private void run(String algorithmName, Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        if (algorithmName.equals("Dijkstra")) {
            algorithm = new Dijkstra(heap);
        }
        if (algorithmName.equals("Astar")) {
            algorithm = new Astar(heap);
        }
        if (algorithmName.equals("Idastar")) {
            algorithm = new IDAStar();
        }
        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();

    }

    private Heap createHeap(String heapName, Graph graph) {
        Heap heap;
        if (heapName.equals("bheap")) {
            heap = new BinaryHeap(graph.getNodeAmount() + 1);
        } else {
            heap = new TernaryHeap(graph.getNodeAmount() + 1);

        }
        return heap;
    }

    /**
     * Palauttaa lyhimmän polun käyttölittymälke merkkijonona
     *
     * @param startX lähtösolmun x
     * @param startY lähtösolmun y
     * @param goalX maalisolmun x
     * @param goalY maalisolmun y
     * @return lyhin polku tai virheilmoitus
     */
    public String showPath(int startX, int startY, int goalX, int goalY) {

        String ret = "";
        if (errorStatus == false) {
            if (!algorithm.emptyPath()) {
                StackO stack = algorithm.getPathInStack(this.graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0));
                int i = 0;
                String coord;
                while (!stack.isEmpty()) {
                    coord = graph.getXYByPointId(stack.pop());
                    String[] xy = coord.split(",");
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);
                    if (ret.isEmpty()) {
                        ret = x + ":" + y;
                    } else {
                        ret = ret + ", " + x + ":" + y;
                    }
                }

            }
        } else {
            ret = "Reittiä ei voida näyttää virheen takia";
        }
        return ret;
    }

    /**
     * Palautaa true jos etäisys on positiivinen
     *
     * @param distance etäisyys
     * @return true, jos etäisyys suurempi tai yhtäsuuri kuin 0
     */
    public boolean distanceOk(int distance) {
        return distance >= 0;
    }
    
}
