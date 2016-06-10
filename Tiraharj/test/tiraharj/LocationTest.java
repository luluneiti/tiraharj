package tiraharj;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    public LocationTest() {
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
    public void testGetX() {
        Location loc = new Location(1, 1);
        assertEquals(1, loc.getX());
    }

    @Test
    public void testGetY() {
        Location loc = new Location(1, 1);
        assertEquals(1, loc.getY());
    }

}
