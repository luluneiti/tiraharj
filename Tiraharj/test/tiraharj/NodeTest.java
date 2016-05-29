package tiraharj;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    public NodeTest() {
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
    public void testGetX() {
        Node node = new Node(2, 1, 1);
        assertEquals(2, node.getX());
    }

    @Test
    public void testGetY() {
        Node node = new Node(9, 10, 1);
        assertEquals(10, node.getY());

    }

    @Test
    public void testGetDistance() {
        Node node = new Node(9, 10, 1);
        assertEquals(1, node.getDistance());
    }

    @Test
    public void testCompareTo1() {

        Node node1 = new Node(1, 2, 1);
        Node node2 = new Node(1, 8, 2);
        assertEquals(-1, node1.compareTo(node2));

    }

    @Test
    public void testCompareTo2() {

        Node node1 = new Node(1, 2, 1);
        Node node2 = new Node(1, 8, 2);
        assertEquals(1, node2.compareTo(node1));

    }

}
