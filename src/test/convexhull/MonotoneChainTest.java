package convexhull;


import main.Point2D;
import main.PointSet;
import main.convexhull.ConvexHull;
import main.convexhull.ConvexHullBuilder;
import main.convexhull.Graham;
import main.convexhull.MonotoneChain;
import org.junit.Assert;
import org.junit.Test;
import main.utilities.Stopwatch;


import java.util.HashSet;

/**
 * @author aeyoa
 */
public class MonotoneChainTest extends Assert {

    private final ConvexHullBuilder hullBuilder = new MonotoneChain();

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySet() throws Exception {
        hullBuilder.build(new PointSet());
    }

    @Test
    public void testOnePoint() throws Exception {
        final ConvexHull convexHull = hullBuilder.build(PointSet.getSetFromPoints(new HashSet<Point2D>() {
            {
                add(new Point2D(0, 0));
            }
        }));
        assertEquals(1, convexHull.size());
        assertEquals(new Point2D(0, 0), convexHull.getPoint(0));

    }

    @Test
    public void testTriple() {
        final ConvexHull convexHull = hullBuilder.build(PointSet.getSetFromPoints(new HashSet<Point2D>() {
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
        final ConvexHull convexHull = hullBuilder.build(PointSet.getSetFromPoints(new HashSet<Point2D>() {
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
        assertTrue(convexHull.checkConvexHullCCWProperty());
    }

    @Test
    public void testCollinear() throws Exception {
        final ConvexHull convexHull = hullBuilder.build(new PointSet(
                new Point2D(-1, -1),
                new Point2D(0, 0),
                new Point2D(1, 1),
                new Point2D(2, 2),
                new Point2D(5, 5)));
        assertEquals(new Point2D(-1, -1), convexHull.getPoint(0));
        assertEquals(new Point2D(5, 5), convexHull.getPoint(1));
    }

    @Test
    public void testCompareGrahamAndMonotoneChain() throws Exception {
        final PointSet set = PointSet.getRandomSet(100000);
        Stopwatch.start();
        new Graham().build(set);
        System.out.println("Graham: " + Stopwatch.stop());

        Stopwatch.start();
        new MonotoneChain().build(set);
        System.out.println("Monotone Chain: " + Stopwatch.stop());
    }

    @Test
    public void testCircle() throws Exception {
        final ConvexHull convexHull = hullBuilder.build(new PointSet(
                new Point2D(1.00, 0.00),
                new Point2D(0.81, 0.59),
                new Point2D(0.31, 0.95),
                new Point2D(-0.30, 0.95),
                new Point2D(-0.81, 0.59),
                new Point2D(-1.00, 0.00),
                new Point2D(-0.81, -0.59),
                new Point2D(-0.31, -0.95),
                new Point2D(0.31, -0.95),
                new Point2D(0.81, -0.59)
        ));
        assertEquals(10, convexHull.size());
        assertTrue(convexHull.checkConvexHullCCWProperty());
    }

    @Test
    public void testBruteRandomPointSets() throws Exception {
        for (int i = 0; i < 1000; i++) {
            final ConvexHull convexHull = hullBuilder.build(PointSet.getRandomSet(30));
            assertTrue(convexHull.checkConvexHullCCWProperty());
        }
    }
}
