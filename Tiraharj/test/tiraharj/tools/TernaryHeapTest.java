package tiraharj.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharj.Node;

public class TernaryHeapTest {

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
    public void testSwap() {
        TernaryHeap heap = new TernaryHeap(20);
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
        TernaryHeap heap = new TernaryHeap(20);
        heap.add(new Node(1, 1, 10));
        heap.add(new Node(1, 2, 8));
        Node node = heap.getData(1);
        assertEquals(8, node.getDistance());

    }

    @Test
    public void testAdd() {
        TernaryHeap heap = new TernaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.poll());
    }

    @Test
    public void testPoll() {
        TernaryHeap heap = new TernaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.poll());
    }

    @Test
    public void testParent() {
        TernaryHeap heap = new TernaryHeap(20);
        assertEquals(1, heap.parent(3));
    }

    @Test
    public void testLeft() {
        TernaryHeap heap = new TernaryHeap(20);
        assertEquals(2, heap.left(1));
    }

    @Test
    public void testMiddle() {
        TernaryHeap heap = new TernaryHeap(20);
        assertEquals(3, heap.middle(1));
    }

    @Test
    public void testRight() {
        TernaryHeap heap = new TernaryHeap(20);
        assertEquals(4, heap.right(1));
    }

    @Test
    public void testGetData() {
        TernaryHeap heap = new TernaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(x, heap.getData(1));
    }

    @Test
    public void testSetData() {
        TernaryHeap heap = new TernaryHeap(20);
        Node x = new Node(1, 3, 1);
        heap.setData(x, 1);
        assertEquals(x, heap.getData(1));
    }

    @Test
    public void testGetHeapSize() {
        TernaryHeap heap = new TernaryHeap(20);
        assertEquals(0, heap.getHeapSize());
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(1, heap.getHeapSize());
    }

    @Test
    public void testIsEmpty() {
        TernaryHeap heap = new TernaryHeap(20);
        assertEquals(true, heap.isEmpty());
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(false, heap.isEmpty());
    }

    @Test
    public void testIsFull() {
        TernaryHeap heap = new TernaryHeap(2);
        assertEquals(false, heap.isFull());
        Node x = new Node(1, 3, 1);
        heap.add(x);
        assertEquals(true, heap.isFull());
    }

    @Test
    public void testPrint() {
        BinaryHeap heap = new BinaryHeap(20);
        int etaisyys = 15;
        for (int i = 0; i < 15; i++) {
            Node x1 = new Node(1, 3, etaisyys);
            heap.add(x1);
            etaisyys--;
        }
        heap.print();

    }

    @Test
    public void testClean() {
        TernaryHeap heap = new TernaryHeap(3);
        Node x = new Node(1, 3, 1);
        heap.add(x);
        heap.add(x);
        assertEquals(false, heap.isEmpty());
        heap.clean();
        assertEquals(true, heap.isEmpty());
    }

}
