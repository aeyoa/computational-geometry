package convexhull;


import main.Point2D;
import main.PointSet;
import main.convexhull.ConvexHull;
import main.convexhull.Graham;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author aeyoa
 */
public class GrahamTest extends Assert {

    @Test
    public void testOnePoint() throws Exception {
        final Graham graham = new Graham();
        final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(new ArrayList<Point2D>() {
            {
                add(new Point2D(0, 0));
            }
        }));
        assertEquals(0, convexHull.size());
        assertEquals(new Point2D(0, 0), convexHull.getPoint(1));

    }

    @Test
    public void testTriple() {
        final Graham graham = new Graham();
        final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(new ArrayList<Point2D>() {
            {
                add(new Point2D(1, 0));
                add(new Point2D(1, 1));
                add(new Point2D(0, 0));
            }
        }));
        assertEquals(new Point2D(0, 0), convexHull.getPoint(0));
        assertEquals(new Point2D(1, 0), convexHull.getPoint(1));
        assertEquals(new Point2D(1, 1), convexHull.getPoint(2));
    }

    @Test
    public void testQuad() {
        final Graham graham = new Graham();
        final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(new ArrayList<Point2D>() {
            {
                add(new Point2D(0, 0));
                add(new Point2D(0, 2));
                add(new Point2D(1, 1));
                add(new Point2D(2, 2));
                add(new Point2D(2, 0));
            }
        }));
        assertEquals(new Point2D(0, 0), convexHull.getPoint(0));
        assertEquals(new Point2D(2, 0), convexHull.getPoint(1));
        assertEquals(new Point2D(2, 2), convexHull.getPoint(2));
        assertEquals(new Point2D(0, 2), convexHull.getPoint(3));
    }
}
