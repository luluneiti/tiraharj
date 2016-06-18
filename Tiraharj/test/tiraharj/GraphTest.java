package tiraharj;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.Manhattan;

public class GraphTest {

    public GraphTest() {

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
    public void testIsReachable() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(false, graph.isReachable(6, 6));
    }

    @Test
    public void testIsReachable2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(true, graph.isReachable(4, 4));
    }

    @Test
    public void testIsTraversable() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        obstacles[graph.getPointId(3, 5)] = true;
        obstacles[graph.getPointId(4, 4)] = true;
        obstacles[graph.getPointId(1, 4)] = true;
        obstacles[graph.getPointId(2, 3)] = true;
        graph.setObstacles(obstacles);
        assertEquals(false, graph.isTraversable(3, 5));

    }

    @Test
    public void testIsTraversable2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        boolean[] obstacles = new boolean[graph.getNodeAmount()];
        obstacles[graph.getPointId(3, 4)] = true;
        obstacles[graph.getPointId(4, 4)] = true;
        obstacles[graph.getPointId(3, 4)] = true;
        obstacles[graph.getPointId(4, 1)] = true;
        graph.setObstacles(obstacles);
        assertEquals(true, graph.isTraversable(3, 1));

    }

    public void testGetNodeAmount() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(25, graph.getNodeAmount());
    }

    @Test
    public void testGetNeighbors() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        Node[] list = graph.getNeighbors(graph, new Node(0, 0, 0), new Manhattan());
        assertEquals(2, list.length);
        assertEquals(true, isInList(list, new Node(1, 0, 1)));
        assertEquals(true, isInList(list, new Node(0, 1, 1)));
    }

    public static boolean isInList(Node[] list, Node node) {

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                if (list[i].getX() == node.getX() && list[i].getY() == node.getY()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Test
    public void testGetNeighbors2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        Node[] list = graph.getNeighbors(graph, new Node(2, 2, 0), new Manhattan());
        assertEquals(4, list.length);
        assertEquals(true, isInList(list, new Node(1, 2, 1)));
        assertEquals(true, isInList(list, new Node(3, 2, 1)));
        assertEquals(true, isInList(list, new Node(2, 1, 1)));
        assertEquals(true, isInList(list, new Node(2, 3, 1)));
    }
    @Test
    public void testGetNeighborsWihtHeuristic() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        Heuristic heur=new Manhattan();
        Node[] list = graph.getNeighbors(graph, new Node(2, 2, 0), heur);
        assertEquals(4, list.length);
        assertEquals(true, isInList(list, new Node(1, 2, 1)));
        assertEquals(true, isInList(list, new Node(3, 2, 1)));
        assertEquals(true, isInList(list, new Node(2, 1, 1)));
        assertEquals(true, isInList(list, new Node(2, 3, 1)));
    }

    @Test
    public void testGetWidth() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(5, graph.getWidth());
    }

    @Test
    public void testGetHeight() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(5, graph.getHeight());
    }

    @Test
    public void testGetPointId() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(13, graph.getPointId(2, 3));
    }

    @Test
    public void testGetPointId2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals(3, graph.getPointId(0, 3));
    }

    @Test
    public void testGetXYByPointId() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        Graph graph = new Graph(matrix);
        assertEquals("0,3", graph.getXYByPointId(3));
    }

}
