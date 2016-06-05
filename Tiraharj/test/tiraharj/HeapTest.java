package tiraharj;

import tiraharj.tools.Heap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

    Heap heap = new Heap(20);

    public HeapTest() {

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
    public void testSwap() {
        heap.setData(new Node(1, 1, 7), 1);
        heap.setData(new Node(1, 2, 8), 2);
        Node a = heap.getData(1);
        Node b = heap.getData(2);
        heap.swap(1, 2);
        assertEquals(b, heap.getData(1));
        assertEquals(a, heap.getData(2));
    }

    @Test
    public void testHeapify() {

    }

    @Test
    public void testAddToHeap() {
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.poll());
    }

    @Test
    public void testPollFromHeap() {
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.poll());
    }

    @Test
    public void testParent() {
        assertEquals(1, heap.parent(3));
    }

    @Test
    public void testLeft() {
        assertEquals(6, heap.left(3));
    }

    @Test
    public void testRight() {
        assertEquals(7, heap.right(3));
    }

}
