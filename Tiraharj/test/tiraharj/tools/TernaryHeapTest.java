package tiraharj.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharj.Node;

public class TernaryHeapTest {

    private TernaryHeap heap = new TernaryHeap(20);

    public TernaryHeapTest() {
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
    public void testIsEmpty() {
        assertEquals(true, heap.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertEquals(false, heap.isFull());
    }

    @Test
    public void testGetData() {

    }

    @Test
    public void testSetData() {

    }

    @Test
    public void testGetHeapSize() {

    }

    @Test
    public void testHeapify() {

    }

    @Test
    public void testAdd() {
        Node x = new Node(1, 3, 7);
        heap.add(x);
        assertEquals(x, heap.getData(1));
        Node y = new Node(1, 2, 6);
        heap.add(y);
        assertEquals(null, heap.getData(0));
        assertEquals(y, heap.getData(1));
        assertEquals(x, heap.getData(2));
        assertEquals(null, heap.getData(3));
        Node z = new Node(5, 5, 4);
        heap.add(z);
        assertEquals(null, heap.getData(0));
        assertEquals(z, heap.getData(1));
        assertEquals(x, heap.getData(2));
        assertEquals(y, heap.getData(3));
        Node r = new Node(5, 5, 3);
        heap.add(r);
        assertEquals(null, heap.getData(0));
        assertEquals(r, heap.getData(1));
        assertEquals(x, heap.getData(2));
        assertEquals(y, heap.getData(3));
        Node s = new Node(5, 5, 2);
        heap.add(s);
        assertEquals(null, heap.getData(0));
        assertEquals(s, heap.getData(1));
        assertEquals(r, heap.getData(2));
        Node t = new Node(5, 5, 1);
        heap.add(t);
        assertEquals(null, heap.getData(0));
        assertEquals(t, heap.getData(1));
        assertEquals(s, heap.getData(2));
        heap.print(); //onko solmut oikeassa paikassa???? // kolmonen on pohjalla!!
    }

    @Test
    public void testPoll() {
        Node node = heap.getData(2);
        heap.poll();
        assertEquals(node, heap.getData(1));
    }

    @Test
    public void testSwap() {
        Node node=heap.getData(6);
        heap.swap(3, 6);
        assertEquals(node, heap.getData(3));
    }

    @Test
    public void testParent() {
        assertEquals(1, heap.parent(4));
        assertEquals(2, heap.parent(6));
    }

    @Test
    public void testLeft() {
        assertEquals(2, heap.left(1));
    }

    @Test
    public void testMiddle() {
        assertEquals(6, heap.middle(2));
    }

    @Test
    public void testRight() {
        assertEquals(4, heap.right(1));
    }

    @Test
    public void testPrint() {

    }

}
