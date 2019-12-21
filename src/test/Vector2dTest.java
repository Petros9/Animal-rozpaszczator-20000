import org.junit.Test;
import projekt_1.Vector2d;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Vector2dTest {
    @Test
    public void testEquals(){
        Vector2d a = new Vector2d( 1, 1);
        Vector2d b = new Vector2d( 1, 2);
        assertNotEquals(a,b);
        a = new Vector2d( 2, 1);
        b = new Vector2d( 1, 1);
        assertNotEquals(a,b);
        a = new Vector2d( 2, 1);
        b = new Vector2d( 1, 2);
        assertNotEquals(a,b);
    }
    @Test
    public void testFollows(){
        Vector2d a = new Vector2d( 1, 1);
        Vector2d b = new Vector2d( 2, 2);
        assertEquals(b.follows(a),false);
    }
    @Test
    public void testPrecedes(){
        Vector2d a = new Vector2d( 1, 1);
        Vector2d b = new Vector2d( 2, 2);
        assertEquals(a.precedes(b),false);
    }

    @Test
    public void testAdd(){
        Vector2d a = new Vector2d( 1, 1);
        Vector2d b = new Vector2d( 2, 2);
        assertEquals(a.add(b),new Vector2d(3,3));
    }
    @Test
    public void adjustToPlanet()
    {
        Vector2d a = new Vector2d(3,3);
        Vector2d b = new Vector2d(-1,-1);
        int width = 2;
        int height = 2;
        assertEquals(a.adjustToPlanet(a,width,height),new Vector2d(0,0));
        assertEquals(b.adjustToPlanet(b,width,height),new Vector2d(2,2));
    }
}
