package tiraharj.tools;

import tiraharj.tools.BinaryHeap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiraharj.Node;
import static org.junit.Assert.*;

public class BinaryHeapTest {

    public BinaryHeapTest() {

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
        BinaryHeap heap = new BinaryHeap(20);
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
        BinaryHeap heap = new BinaryHeap(20);
        heap.add(new Node(1, 1, 10));
        heap.add(new Node(1, 2, 8));
        Node node = heap.getData(1);
        assertEquals(8, node.getDistance());

    }

    @Test
    public void testAdd() {
        BinaryHeap heap = new BinaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.poll());
    }

    @Test
    public void testPoll() {
        BinaryHeap heap = new BinaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.poll());
    }

    @Test
    public void testParent() {
        BinaryHeap heap = new BinaryHeap(20);
        assertEquals(1, heap.parent(3));
    }

    @Test
    public void testLeft() {
        BinaryHeap heap = new BinaryHeap(20);
        assertEquals(6, heap.left(3));
    }

    @Test
    public void testRight() {
        BinaryHeap heap = new BinaryHeap(20);
        assertEquals(7, heap.right(3));
    }

    @Test
    public void testGetData() {
        BinaryHeap heap = new BinaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.getData(1));
    }

    @Test
    public void testSetData() {
        BinaryHeap heap = new BinaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.setData(x, 1);
        assertEquals(x, heap.getData(1));
    }

    @Test
    public void testGetHeapSize() {
        BinaryHeap heap = new BinaryHeap(20);
        assertEquals(0, heap.getHeapSize());
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(1, heap.getHeapSize());
    }

    @Test
    public void testIsEmpty() {
        BinaryHeap heap = new BinaryHeap(20);
        assertEquals(true, heap.isEmpty());
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(false, heap.isEmpty());
    }

    @Test
    public void testIsFull() {
        BinaryHeap heap = new BinaryHeap(2);
        assertEquals(false, heap.isFull());
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(true, heap.isFull());
    }

    @Test
    public void testPrint() {
        BinaryHeap heap = new BinaryHeap(20);
        int etaisyys = 20;
        for (int i = 0; i < 20; i++) {
            Node x1 = new Node(1, 3, etaisyys);
            heap.add(x1);
            etaisyys--;
        }
        heap.print();

    }

    @Test
    public void testClean() {
        BinaryHeap heap = new BinaryHeap(3);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        heap.add(x);
        assertEquals(false, heap.isEmpty());
        heap.clean();
        assertEquals(true, heap.isEmpty());
    }

}
