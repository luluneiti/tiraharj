package tiraharj.algorithm;

import java.util.Arrays;
import tiraharj.algorithm.Manhattan;
import tiraharj.algorithm.Heuristic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiraharj.Node;
import static org.junit.Assert.*;
import tiraharj.Location;

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

    @Test
    public void testGetNeighborsCoordinates() {
        Heuristic heuristic = new Manhattan();
        Location[] table = heuristic.getNeighborsCoordinates(1, 1);

        assertEquals(2, table[0].getX()); //x+1
        assertEquals(1, table[0].getY());
        assertEquals(0, table[1].getX()); //x-1
        assertEquals(1, table[1].getY());
        assertEquals(1, table[2].getX()); //y+1
        assertEquals(2, table[2].getY());
        assertEquals(1, table[3].getX());//y-1
        assertEquals(0, table[3].getY());
    }
}
