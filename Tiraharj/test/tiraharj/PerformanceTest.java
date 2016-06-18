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

public class PerformanceTest {

    final int MATRIXSIZE = 5; //memory loppuu jos laittaa yli 8000
    final int MAXDISTANCE = 1;
    final int LOOPUNTIL = 10;
    static int[][] matrix;
    static Graph graph;
    static boolean[] obstacles;
    final int lahtox = 1;
    final int lahtoy = 2;
    final int maalix = 4;
    final int maaliy = 4;
    ShortestPath dijkstra;
    ShortestPath astar;
    ShortestPath ida;
    Heap heap;

    @Test
    public void bigMatrixNoObstacles() {

        initialize();
//        printMatrix();
//        printObstacles();
//        runDijkstra(graph, obstacles);
//        runAstar(graph, obstacles);
        runIDAStar(graph, obstacles);
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

        heap = new TernaryHeap(graph.getNodeAmount() + 1);
//        Heap heap = new TernaryHeap(graph.getNodeAmount() + 1);
        dijkstra = new Dijkstra(heap);
        //prioriteettijono pitää editoida ohjelmassa

        astar = new Astar(heap);
        //prioriteettijono pitää editoida ohjelmassa

        ida = new IDAStar();

//        obstacles[graph.getPointId(2, 4)] = true; //IDA ei löydä reittiä esteillä!
//        obstacles[graph.getPointId(3, 2)] = true;
//        obstacles[graph.getPointId(3, 4)] = true;
//        obstacles[graph.getPointId(2, 3)] = true;
//        obstacles[graph.getPointId(4, 3)] = true;

    }

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
            //        dijkstra.printPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), dijkstra);
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
            //        astar.printPath(graph, new Node(lahtox, lahtoy, 0), new Node(maalix, maaliy, 0), astar);
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
