package tiraharj.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticTest {

    public StatisticTest() {
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
    public void testAddCounter() {
        Statistic instance = new Statistic();
        assertEquals(0, instance.getOperCounter());
        instance.addCounter();
        assertEquals(1, instance.getOperCounter());
        instance.addCounter();
        assertEquals(2, instance.getOperCounter());
    }

    @Test
    public void testStartClock() {
        //hankala testata
    }

    @Test
    public void testStopClock() {
        Statistic instance = new Statistic();
        instance.getTimeInEnd();
        assertEquals(0, instance.getTimeInEnd());
        instance.stopClock();
        //assertEquals(>0, instance.getTimeInEnd());
    }

    @Test
    public void testToString() {

    }

}
