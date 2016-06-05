package tiraharj;

import tiraharj.algorithm.Manhattan;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.Dijkstra;
import tiraharj.algorithm.Astar;
import tiraharj.tools.Statistic;
import org.junit.Test;

public class PerformanceTest {

    final int MATRIXSIZE = 1000; //heapin memory loppuu jos laittaa nollan lisää
    final int MAXDISTANCE = 9;
    static int[][] matrix;
    static Graph graph;
    static boolean[] obstacles;

    @Test
    public void bigMatrixNoObstacles() {

        initialize();
//        printMatrix();
//        printObstacles();
        runDijkstra(graph);
        runAstar(graph, obstacles);
    }

    public void initialize() {

        matrix = new int[MATRIXSIZE][MATRIXSIZE];
        graph = new Graph(matrix);
        obstacles = new boolean[graph.getNodeAmount() + 1];
        graph.setObstacles(obstacles);
        for (int i = 0; i < MATRIXSIZE; i++) {
            for (int j = 0; j < MATRIXSIZE; j++) {
                matrix[i][j] = getNumber(MAXDISTANCE);
            }
        }

        obstacles[graph.getPointId(8, 40)] = true;
        obstacles[graph.getPointId(80, 237)] = true;
        obstacles[graph.getPointId(97, 94)] = true;
        obstacles[graph.getPointId(250, 300)] = true;
    }

    private int getNumber(int limit) {
        int random = (int) (Math.random() * limit + 1);
        return random;
    }

    private void runDijkstra(Graph graph) {

        Statistic statistic = new Statistic();
        Dijkstra.setStatistic(statistic);
        statistic.startClock();
        Dijkstra.findPath(graph, new Node(1, 2, 0), new Node(97, 92, 0));
        statistic.stopClock();
//        Dijkstra.printPath(graph, new Node(1, 2, 0), new Node(97, 92, 0));
        System.out.println("Dijkstra: " + statistic);

    }

    private void runAstar(Graph graph, boolean[] obstacles) {
        Statistic statistic = new Statistic();
        Astar.setStatistic(statistic);
        Heuristic heuristic = new Manhattan();
        statistic.startClock();
        Astar.findPath(graph, new Node(1, 2, 0), new Node(97, 92, 0), heuristic);
        statistic.stopClock();
//        Astar.printPath(graph, new Node(1, 2, 0), new Node(97, 92, 0));
        System.out.println("Astar: " + statistic);
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
