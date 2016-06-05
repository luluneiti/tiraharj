package tiraharj;

import tiraharj.algorithm.Dijkstra;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static tiraharj.Main.runAstar;
import static tiraharj.Main.runDijkstra;
import tiraharj.tools.Statistic;

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
    public void testFindPathNoObstacles() {

        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        Dijkstra.setStatistic(statistic);
        Dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        Stack<Integer> stack = Dijkstra.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        assertEquals("1,3", graph.getXYByPointId(stack.pop()));
        assertEquals("1,4", graph.getXYByPointId(stack.pop()));//matka piteni minkeolla 2 askelta!!!!
        assertEquals("2,4", graph.getXYByPointId(stack.pop()));//matka piteni minkeolla 2 askelta!!!!
        assertEquals("2,3", graph.getXYByPointId(stack.pop()));

    }

    @Test
    public void testFindPathWithObstacles() {

        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(4, 4)] = true;
        obstacles[graph.getPointId(4, 1)] = true;
        obstacles[graph.getPointId(4, 2)] = true;

        Dijkstra.setStatistic(statistic);
        Dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        Stack<Integer> stack = Dijkstra.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        assertEquals("0,2", graph.getXYByPointId(stack.pop()));
        assertEquals("0,3", graph.getXYByPointId(stack.pop()));
        assertEquals("0,4", graph.getXYByPointId(stack.pop()));
        assertEquals("1,4", graph.getXYByPointId(stack.pop()));
        assertEquals("2,4", graph.getXYByPointId(stack.pop()));
        assertEquals("3,4", graph.getXYByPointId(stack.pop()));

    }
}
