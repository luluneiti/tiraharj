package tiraharj.algorithm;

import tiraharj.algorithm.Dijkstra;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiraharj.Graph;
import tiraharj.Node;
import static org.junit.Assert.*;
import static tiraharj.Main.runAstar;
import static tiraharj.Main.runDijkstra;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;
import tiraharj.tools.TernaryHeap;

public class DijkstraTest {

    private static Statistic statistic = new Statistic();

    public DijkstraTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindPathNoObstaclesWith2Heap() {

        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        Heuristic heuristic = new Manhattan();
        /**
         * *********************************************
         */
        ShortestPath dijkstra = new Dijkstra(new BinaryHeap(graph.getNodeAmount() + 1));
//        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount()+1));
        //prioriteettijono pitää editoida ohjelmassa
        /**
         * *********************************************
         */
        dijkstra.setStatistic(statistic);
        dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        StackO stack = dijkstra.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), dijkstra);
        assertEquals("1,3", graph.getXYByPointId(stack.pop()));
        assertEquals("1,4", graph.getXYByPointId(stack.pop()));
        assertEquals("2,4", graph.getXYByPointId(stack.pop()));
        assertEquals("2,3", graph.getXYByPointId(stack.pop())); //miksi kiertää

    }

    @Test
    public void testFindPathNoObstaclesWith3Heap() {

        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        Heuristic heuristic = new Manhattan();
        /**
         * *********************************************
         */
        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount() + 1));
//        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount()+1));
        //prioriteettijono pitää editoida ohjelmassa
        /**
         * *********************************************
         */
        dijkstra.setStatistic(statistic);
        dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        StackO stack = dijkstra.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), dijkstra);
        assertEquals("2,2", graph.getXYByPointId(stack.pop()));
        assertEquals("3,2", graph.getXYByPointId(stack.pop()));

    }

    @Test
    public void testFindPathWithObstaclesAnd2Heap() {

        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        Heuristic heuristic = new Manhattan();
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(4, 4)] = true;
        obstacles[graph.getPointId(4, 1)] = true;
        obstacles[graph.getPointId(4, 2)] = true;
        /**
         * *********************************************
         */
        ShortestPath dijkstra = new Dijkstra(new BinaryHeap(graph.getNodeAmount() + 1));
//        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount()+1));
        //prioriteettijono pitää editoida ohjelmassa
        /**
         * *********************************************
         */
        dijkstra.setStatistic(statistic);
        dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        StackO stack = dijkstra.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), dijkstra);
        assertEquals("0,2", graph.getXYByPointId(stack.pop()));
        assertEquals("0,3", graph.getXYByPointId(stack.pop()));
        assertEquals("0,4", graph.getXYByPointId(stack.pop()));
        assertEquals("1,4", graph.getXYByPointId(stack.pop()));
        assertEquals("2,4", graph.getXYByPointId(stack.pop()));
        assertEquals("3,4", graph.getXYByPointId(stack.pop()));

    }

    @Test
    public void testFindPathWithObstaclesAnd3Heap() {

        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        Heuristic heuristic = new Manhattan();
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(4, 4)] = true;
        obstacles[graph.getPointId(4, 1)] = true;
        obstacles[graph.getPointId(4, 2)] = true;
        /**
         * *********************************************
         */
        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount() + 1));
//        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount()+1));
        //prioriteettijono pitää editoida ohjelmassa
        /**
         * *********************************************
         */
        dijkstra.setStatistic(statistic);
        dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        StackO stack = dijkstra.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), dijkstra);
        assertEquals("0,2", graph.getXYByPointId(stack.pop()));
        assertEquals("0,3", graph.getXYByPointId(stack.pop()));
        assertEquals("0,4", graph.getXYByPointId(stack.pop()));
        assertEquals("1,4", graph.getXYByPointId(stack.pop()));
        assertEquals("1,3", graph.getXYByPointId(stack.pop()));
        assertEquals("2,3", graph.getXYByPointId(stack.pop()));

    }
}
