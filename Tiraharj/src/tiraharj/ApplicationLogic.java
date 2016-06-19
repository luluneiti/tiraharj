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

public class ApplicationLogic {

    private ShortestPath algorithm;
    private Graph graph;
    private UserInterface ui;

    public ApplicationLogic() {

    }

    /**
     * Luo tarvittavat oliot ja käynnistää lyhimmän polun etsinnän halutulla algoritmilla
     * @param matrix verkko
     * @param obstacles esteet 
     * @param algorithmName ajettava algoritmi
     * @param heuristicName käytettävä heuristiikka
     * @param heapName käytettävä keko-ratkaisu
     * @param startX lähtösolmu x
     * @param startY lähtösolmu y
     * @param goalX maalisolmu x
     * @param goalY maalisolmu y
     * @return ajon tulos
     */
    public String runAlgorithm(int[][] matrix, boolean[] obstacles, String algorithmName, String heuristicName, String heapName, int startX, int startY, int goalX, int goalY) {

        //tarkista matriisi ennen ettei neg arvoja
        graph = new Graph(matrix);
        graph.setObstacles(obstacles);
        Statistic stat = new Statistic();

        Heap heap = createHeap(heapName, graph);
        createAlgorithm(algorithmName, heap, stat, graph, startX, startY, goalX, goalY);

        return stat.toString();
    }

    private void createAlgorithm(String algorithmName, Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        if (algorithmName.equals("Dijkstra")) {
            algorithm = new Dijkstra(heap);
            runDijkstra(heap, stat, graph, startX, startY, goalX, goalY);
        }
        if (algorithmName.equals("Astar")) {
            algorithm = new Astar(heap);
            runAstar(heap, stat, graph, startX, startY, goalX, goalY);
        }
        if (algorithmName.equals("Idastar")) {
            algorithm = new IDAStar();
            runIDAstar(heap, stat, graph, startX, startY, goalX, goalY);
        }
    }

    private Heap createHeap(String heapName, Graph graph) {
        Heap heap;
        if (heapName == "bheap") {
            heap = new BinaryHeap(graph.getNodeAmount() + 1);
        } else {
            heap = new TernaryHeap(graph.getNodeAmount() + 1);
        }
        return heap;
    }

    private void runDijkstra(Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {

        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();
    }

    private void runAstar(Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();
    }

    private void runIDAstar(Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();
    }

    /**
     * Asettaa käytöliittymä -olion muuttujaan, jotta voidaan kutsua sen metodeita
     * @param ui käyttöliittymä-olio
     */
    public void setUI(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * Palauttaa lyhimmän polun käyttölittymälke merkkijonona 
     * Jatkossa tulee merkitsemään lyhimmän polun käyttöliittymän matriisiin
     * @param startX lähtösolmun x
     * @param startY lähtösolmun y
     * @param goalX maalisolmun x
     * @param goalY maalisolmun y
     * @return lyhin polku
     */
    public String showPath(int startX, int startY, int goalX, int goalY) {

        String ret = "";
        if (!algorithm.emptyPath()) {
            StackO stack = algorithm.getPathInStack(this.graph, new Node(1, 2, 0), new Node(6, 6, 0));

            int i = 0;
            String coord = "";

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

                //ui.setRed(x, y);
            }

        }
        return ret;
    }

    /**
     * Palautaa true jos etäisys on positiivinen
     * @param distance etäisyys
     * @return true, jos etäisyys >=0
     */
    public boolean distanceOk(int distance) {
        return distance>=0;
    }

}
