
package tiraharj.algorithm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharj.Graph;
import tiraharj.Node;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;

public class ShortestPathTest {
    
    public ShortestPathTest() {
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
    public void testInitTable() {
        int[] table=new int[3];
        ShortestPath alg=new Dijkstra(new BinaryHeap(5));
        table=alg.initTable(table.length);
        assertEquals(Integer.MAX_VALUE, table[0]);
        assertEquals(Integer.MAX_VALUE, table[1]);
        assertEquals(Integer.MAX_VALUE, table[2]);
    }

    
}
