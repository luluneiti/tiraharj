package tiraharj;

import tiraharj.algorithm.Manhattan;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.Dijkstra;
import tiraharj.algorithm.Astar;
import tiraharj.tools.Statistic;
import org.junit.Test;
import tiraharj.algorithm.IDAStar;
import tiraharj.algorithm.ShortestPath;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.Heap;
import tiraharj.tools.RandomNumber;
import tiraharj.tools.TernaryHeap;

/**
 * Tätä luokkaa käytetään isojen testien ajamiseen
 *
 * @author Ulla
 */
public class PerformanceTest {

    //********** Muuttamalla ao. final muuttujia + initialize() metodia, pystyt muuttamaan ajoja 
    final int MATRIXSIZE = 3000; //matriisin koko MATRIXSIZE*MATRIXSIZE
    final int MAXDISTANCE = 9; //maksimietäisyys; arpoo etäisyydet
    final int LOOPUNTIL = 10; //eri algoritmien loop-metodit luuppavat LOOPUNTIL-muuttujan verran
    final int lahtox = 1; //lähtösolmun x
    final int lahtoy = 2; //lähtösolmun y
    final int maalix = 4; //maalisolmun x
    final int maaliy = 5; //maalisolmun y
    //******************

    static int[][] matrix;
    static Graph graph;
    static boolean[] obstacles;
    ShortestPath dijkstra;
    ShortestPath astar;
    ShortestPath ida;
    Heap heap;

    @Test
    public void performanceTest() {

        initialize(); //initialize() metodissa voi vaihtaa keko-toteutusta ja esteitä
//        printMatrix(); //tulostaa matriisin, jonka etäisyydet arvottu
//        printObstacles(); //tulostaa esteet

        //************* Valitse ajettavat algoritmit
//        runDijkstra(graph, obstacles);
//        runAstar(graph, obstacles);
        runIDAStar(graph, obstacles);
        //**********************
    }

    public void initialize() {

        matrix = new int[MATRIXSIZE][MATRIXSIZE];
        graph = new Graph(matrix);
        obstacles = new boolean[graph.getNodeAmount() + 1];
        graph.setObstacles(obstacles);
        for (int i = 0; i < MATRIXSIZE; i++) {
            for (int j = 0; j < MATRIXSIZE; j++) {
                matrix[i][j] = RandomNumber.getNumber(MAXDISTANCE);
            }
        }

        //************* Valitse keko-ratkaisu vaihtamalla heap muuttujaa
        heap = new TernaryHeap(graph.getNodeAmount() + 1);
//        Heap heap = new TernaryHeap(graph.getNodeAmount() + 1);

        dijkstra = new Dijkstra(heap);
        astar = new Astar(heap);
        ida = new IDAStar();

        //**************** Valitse esteet
        obstacles[graph.getPointId(1, 3)] = true; 
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(3, 4)] = true;
        obstacles[graph.getPointId(2, 3)] = true;
        obstacles[graph.getPointId(4, 3)] = true;

    }

    //Loppuihin metodeihin ei tarvitse koskea
    //paitsi jos haluat tulostaa polun, eli ota printPath kutsu pois kommenteista
    private void runDijkstra(Graph graph, boolean[] obstacles) { 
        Statistic statistic = new Statistic();
        dijkstra.setStatistic(statistic);
        loopDijkstra(statistic, graph, new Manhattan());
        heap.clean();
    }

    public void loopDijkstra(Statistic statistic, Graph graph, Heuristic heuristic) { 
        for (int i = 0; i < LOOPUNTIL; i++) {
            statistic.startClock();
            dijkstra.findPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), heuristic);
            statistic.stopClock();
            // dijkstra.printPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), dijkstra);
            System.out.println("Dijkstra: " + statistic);
        }
    }

    private void runAstar(Graph graph, boolean[] obstacles) { 
        Statistic statistic = new Statistic();
        astar.setStatistic(statistic);
        Heuristic heuristic = new Manhattan();
        loopAstar(statistic, graph, heuristic);
        heap.clean();
    }

    public void loopAstar(Statistic statistic, Graph graph, Heuristic heuristic) {
        for (int i = 0; i < LOOPUNTIL; i++) {
            statistic.startClock();
            astar.findPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), heuristic);
            statistic.stopClock();
            // astar.printPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), astar);
            System.out.println("Astar: " + statistic);
        }
    }

    public void runIDAStar(Graph graph, boolean[] obstacles) {

        Statistic statistic = new Statistic();
        ida.setStatistic(statistic);
        Heuristic heuristic = new Manhattan();
        loopIDA(statistic, graph, heuristic);
        heap.clean();
    }

    public void loopIDA(Statistic statistic, Graph graph, Heuristic heuristic) {
        for (int i = 0; i < LOOPUNTIL; i++) {
            statistic.startClock();
            ida.findPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), heuristic);
            statistic.stopClock();
//            ida.printPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), ida);
            System.out.println("IDAstar: " + statistic);
        }
    }

    private void printObstacles() {
        for (int i = 0; i < obstacles.length; i++) {
            System.out.print(obstacles[i]);
        }
    }

    private void printMatrix() {
        for (int i = 0; i < MATRIXSIZE; i++) {
            for (int j = 0; j < MATRIXSIZE; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println("");
        }
    }

}
