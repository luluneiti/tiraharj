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
        boolean[] obstacles = new boolean[10];

        Graph graph = new Graph(matrix, obstacles);

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

    }

}
