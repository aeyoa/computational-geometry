import main.Point2D;
import main.PointSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
        final PointSet set = PointSet.getSetFromPoints(new HashSet<Point2D>() {
            {
                add(new Point2D(1, 1));
                add(new Point2D(2, 2));
            }
        });

        final Iterator<Point2D> points = set.iterator();
        assertTrue(points.hasNext());
        points.next();
        assertTrue(points.hasNext());
        points.next();
        assertFalse(points.hasNext());

    }
}
