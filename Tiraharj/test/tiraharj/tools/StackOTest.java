package tiraharj.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiraharj.Node;

public class StackOTest {

    StackO stack = new StackO(10);

    public StackOTest() {
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
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testAdd() {
        stack.push(1);
        assertEquals(1, stack.getFirst());
    }

    @Test
    public void testPoll() {
        stack.pop();
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testIsFull() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        assertEquals(true, stack.isFull());
    }

}
