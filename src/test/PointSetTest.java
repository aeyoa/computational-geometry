import main.Point2D;
import main.PointSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author aeyoa
 */
public class PointSetTest extends Assert {

    @Test
    public void testRandom() throws Exception {
        final PointSet set = PointSet.getRandomSet(10);
        for (Point2D point2D : set) {
            System.out.println(point2D);
        }
    }

    @Test
    public void testSpecificPoints() throws Exception {
        final PointSet set = PointSet.getSetFromPoints(new ArrayList<Point2D>() {
            {
                add(new Point2D(1, 1));
                add(new Point2D(2, 2));
            }
        });
        assertTrue(set.iterator().hasNext());
        assertEquals(set.iterator().next().getX(), 1.0, 0.0);
    }
}
