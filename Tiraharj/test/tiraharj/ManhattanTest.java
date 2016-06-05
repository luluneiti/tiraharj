package tiraharj;

import tiraharj.algorithm.Manhattan;
import tiraharj.algorithm.Heuristic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManhattanTest {

    public ManhattanTest() {
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
    public void testGetToEnd() {
        Heuristic heuristic = new Manhattan();
        assertEquals(3, heuristic.getToEnd(new Node(4, 6, 1), new Node(4, 9, 1)));
    }

    @Test
    public void testGetToEnd2() {
        Heuristic heuristic = new Manhattan();
        assertEquals(9, heuristic.getToEnd(new Node(3, 1, 1), new Node(4, 9, 1)));
    }

    @Test
    public void testGetToEnd3() {
        Heuristic heuristic = new Manhattan();
        assertEquals(11, heuristic.getToEnd(new Node(1, 1, 1), new Node(4, 9, 1)));
    }
}
