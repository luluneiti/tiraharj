package tiraharj;

import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AstarTest {

    public AstarTest() {

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
        Heuristic heuristic = new Manhattan();
        Astar.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        Stack<Integer> stack = Astar.getPathInStack(graph, new Node(1, 2, 0), new Node(3, 3, 0));

        while (!stack.isEmpty()) {
            assertEquals("2,2", graph.getXYByPointId(stack.pop()));
            assertEquals("3,2", graph.getXYByPointId(stack.pop()));
        }
    }

    @Test
    public void testFindPathWithObstacles() {

        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        graph.setObstacles(obstacles);
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(4, 4)] = true;
        obstacles[graph.getPointId(4, 3)] = true;
        obstacles[graph.getPointId(4, 1)] = true;

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
