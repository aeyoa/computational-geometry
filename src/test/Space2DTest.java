import main.Point2D;
import main.PointSet;
import main.Space2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by arsenykogan on 27/09/14.
 */
public class Space2DTest extends Assert {

    @Test
    public void testAngles() throws Exception {
        assertEquals(Space2D.getAngle(new Point2D(0, 0), new Point2D(1, 1)), Math.PI / 4, 0.0);
        assertEquals(Space2D.getAngle(new Point2D(0, 0), new Point2D(1, 0)), 0.0, 0.0);
        assertEquals(Space2D.getAngle(new Point2D(0, 0), new Point2D(0, 0)), 0.0, 0.0);
        assertEquals(Space2D.getAngle(new Point2D(-1, 1), new Point2D(0, 0)), -Math.PI / 4, 0.0);
        assertEquals(Space2D.getAngle(new Point2D(0, 0), new Point2D(0, -1)), -Math.PI / 2, 0.0);
        assertEquals(Space2D.getAngle(new Point2D(0, 0), new Point2D(-1, 0)), Math.PI, 0.0);
        assertEquals(Space2D.getAngle(new Point2D(13 / 6, 5), new Point2D(13 / 6, -9 / 11)), -Math.PI / 2, 0.0);
    }

    @Test
    public void testLeftPoint() throws Exception {
        final ArrayList<Point2D> points = new ArrayList<Point2D>() {
            {
                add(new Point2D(0, 0));
                add(new Point2D(1, 1));
                add(new Point2D(0.5, -10));
            }
        };
        assertEquals(points.get(0), Space2D.findLeftPoint(PointSet.getSetFromPoints(points)));
    }

    @Test
    public void testOnePoint() throws Exception {
        final ArrayList<Point2D> points = new ArrayList<Point2D>() {
            {
                add(new Point2D(0.5, -10));
            }
        };
        assertEquals(points.get(0), Space2D.findLeftPoint(PointSet.getSetFromPoints(points)));
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmpty() {
        final ArrayList<Point2D> points = new ArrayList<Point2D>();
        assertEquals(new Point2D(0, 0), Space2D.findLeftPoint(PointSet.getSetFromPoints(points)));
    }

    @Test
    public void testDistance() throws Exception {
        assertEquals(1, Space2D.getDistance(new Point2D(0, 0), new Point2D(1, 0)), 0.0);
        assertEquals(1, Space2D.getDistance(new Point2D(0, 0), new Point2D(0, -1)), 0.0);
        assertEquals(0, Space2D.getDistance(new Point2D(10, 10), new Point2D(10, 10)), 0.0);
        assertEquals(Math.sqrt(2), Space2D.getDistance(new Point2D(1, 1), new Point2D(2, 2)), 0.0);
    }

    @Test
    public void testCrossProduct() throws Exception {
        /* Counter-clockwise turn. */
        assertTrue(Space2D.crossProduct(new Point2D(0, 0), new Point2D(1, 0), new Point2D(1, 1)) > 0);
        /* Clockwise turn. */
        assertTrue(Space2D.crossProduct(new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 0)) < 0);
        /* Collinear. */
        assertTrue(Space2D.crossProduct(new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 2)) == 0);
    }
}
