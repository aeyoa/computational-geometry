package convexhull;


import main.Point2D;
import main.PointSet;
import main.convexhull.ConvexHull;
import main.convexhull.Graham;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * @author aeyoa
 */
public class GrahamTest extends Assert {

    private final Graham graham = new Graham();

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySet() throws Exception {
        graham.build(new PointSet());
    }

    @Test
    public void testOnePoint() throws Exception {
        final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(new HashSet<Point2D>() {
            {
                add(new Point2D(0, 0));
            }
        }));
        assertEquals(1, convexHull.size());
        assertEquals(new Point2D(0, 0), convexHull.getPoint(0));

    }

    @Test
    public void testTriple() {
        final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(new HashSet<Point2D>() {
            {
                add(new Point2D(1, 0));
                add(new Point2D(1, 1));
                add(new Point2D(0, 0));
            }
        }));
        assertTrue(convexHull.getPoints().contains(new Point2D(0, 0)));
        assertTrue(convexHull.getPoints().contains(new Point2D(1, 0)));
        assertTrue(convexHull.getPoints().contains(new Point2D(1, 1)));
    }

    @Test
    public void testQuad() {
        final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(new HashSet<Point2D>() {
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

    @Test
    public void testCollinear() throws Exception {
        final ConvexHull convexHull = graham.build(new PointSet(
                new Point2D(-1, -1),
                new Point2D(0, 0),
                new Point2D(1, 1),
                new Point2D(2, 2),
                new Point2D(5, 5)));
        assertEquals(new Point2D(-1, -1), convexHull.getPoint(0));
        assertEquals(new Point2D(5, 5), convexHull.getPoint(1));
    }
}
