package tiraharj.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharj.Node;

public class NodeSortTest {

    public NodeSortTest() {
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
    public void testNodeSort() {
        Node[] nodes = new Node[4];
        nodes[0] = new Node(1, 1, 8);
        nodes[1] = new Node(1, 2, 3);
        nodes[2] = new Node(1, 3, 1);
        nodes[3] = new Node(1, 5, 2);
        Node[] nodes2 = new Node[4];
        nodes2[0] = new Node(1, 3, 1);
        nodes2[1] = new Node(1, 5, 2);
        nodes2[2] = new Node(1, 2, 3);
        nodes2[3] = new Node(1, 1, 8);

        Node[] nodes3 = NodeSort.sort(nodes);

        assertEquals(nodes2[0].getDistance(), nodes3[0].getDistance());
        assertEquals(nodes2[1].getDistance(), nodes3[1].getDistance());
        assertEquals(nodes2[2].getDistance(), nodes3[2].getDistance());
        assertEquals(nodes2[3].getDistance(), nodes3[3].getDistance());

    }

}
