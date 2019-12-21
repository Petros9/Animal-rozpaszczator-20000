import projekt_1.MapDirection;
import org.junit.Test;
import projekt_1.Vector2d;

import static junit.framework.TestCase.assertEquals;

public class MapDirectionTest {
    @Test
    public void testNext(){
        assertEquals(MapDirection.SOUTH.next(), MapDirection.SOUTH_WEST);
        assertEquals(MapDirection.SOUTH_WEST.next(),MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH_WEST);
        assertEquals(MapDirection.NORTH_WEST.next(),MapDirection.NORTH);
        assertEquals(MapDirection.NORTH.next(), MapDirection.NORTH_EAST);
        assertEquals(MapDirection.NORTH_EAST.next(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.next(),MapDirection.SOUTH_EAST);
        assertEquals(MapDirection.SOUTH_EAST.next(),MapDirection.SOUTH);
    }
    @Test
    public void toUnitVector(){
        assertEquals( MapDirection.WEST.toUnitVector(),new Vector2d(-1,0));
        assertEquals( MapDirection.NORTH.toUnitVector(),new Vector2d(0,-1));
        assertEquals( MapDirection.NORTH_EAST.toUnitVector(),new Vector2d(1,-1));
        assertEquals( MapDirection.SOUTH_EAST.toUnitVector(),new Vector2d(1,1));
    }

}
