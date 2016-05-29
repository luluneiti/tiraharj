package tiraharj;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    boolean[] obstacles = new boolean[99];
    Graph graph = new Graph(matrix, obstacles);

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
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(false, graph.isReachable(6, 6));
    }

    @Test
    public void testIsReachable2() {
        assertEquals(true, graph.isReachable(4, 4));
    }

    @Test
    public void testIsTraversable() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        obstacles[graph.getPointId(3, 5)] = true;
        obstacles[graph.getPointId(4, 5)] = true;
        obstacles[graph.getPointId(5, 5)] = true;
        obstacles[graph.getPointId(6, 5)] = true;
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(false, graph.isTraversable(3, 5));

    }

    @Test
    public void testIsTraversable2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        obstacles[graph.getPointId(3, 5)] = true;
        obstacles[graph.getPointId(4, 5)] = true;
        obstacles[graph.getPointId(5, 5)] = true;
        obstacles[graph.getPointId(6, 5)] = true;
        assertEquals(true, graph.isTraversable(3, 6));

    }

    public void testGetNodeAmount() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(25, graph.getNodeAmount());
    }

    @Test
    public void testGetNeighbors() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        Node[] list = graph.getNeighbors(graph, new Node(0, 0, 0));
        assertEquals(2, list.length);
        assertEquals(true, isInList(list, new Node(1, 0, 1)));
        assertEquals(true, isInList(list, new Node(0, 1, 1)));
    }

    public static boolean isInList(Node[] list, Node node) {

        for (int i = 0; i < list.length; i++) {
            if (list[i].getX() == node.getX() && list[i].getY() == node.getY()) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void testGetNeighbors2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        Node[] list = graph.getNeighbors(graph, new Node(2, 2, 0));
        assertEquals(4, list.length);
        assertEquals(true, isInList(list, new Node(1, 2, 1)));
        assertEquals(true, isInList(list, new Node(3, 2, 1)));
        assertEquals(true, isInList(list, new Node(2, 1, 1)));
        assertEquals(true, isInList(list, new Node(2, 3, 1)));
    }

    @Test
    public void testGetAmountOfNeighbors() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        System.out.println(graph.getHeight());
        System.out.println(graph.getWidth());
        //kulmat
        assertEquals(2, graph.getAmountOfNeighbors(0, 0));
        assertEquals(2, graph.getAmountOfNeighbors(0, 4));
        assertEquals(2, graph.getAmountOfNeighbors(4, 0));
        assertEquals(2, graph.getAmountOfNeighbors(4, 4));

        //reunat, mutta ei kulmat
        assertEquals(3, graph.getAmountOfNeighbors(0, 1));
        assertEquals(3, graph.getAmountOfNeighbors(1, 4));
        assertEquals(3, graph.getAmountOfNeighbors(4, 1));
        assertEquals(3, graph.getAmountOfNeighbors(3, 4));

        //keskellä
        assertEquals(4, graph.getAmountOfNeighbors(3, 1));
        assertEquals(4, graph.getAmountOfNeighbors(1, 2));
        assertEquals(4, graph.getAmountOfNeighbors(2, 3));
        assertEquals(4, graph.getAmountOfNeighbors(3, 3));

    }

    @Test
    public void testGetWidth() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(5, graph.getWidth());
    }

    @Test
    public void testGetHeight() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(5, graph.getHeight());
    }

    @Test
    public void testGetPointId() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(13, graph.getPointId(2, 3));
    }

    @Test
    public void testGetPointId2() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals(3, graph.getPointId(0, 3));
    }

    @Test
    public void testGetXYByPointId() {
        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        Graph graph = new Graph(matrix, obstacles);
        assertEquals("0,3", graph.getXYByPointId(3));
    }

}
