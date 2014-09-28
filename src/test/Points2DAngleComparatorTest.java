import main.Point2D;
import main.Points2DAngleComparator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author aeyoa
 */
public class Points2DAngleComparatorTest extends Assert {

    @Test
    public void testSimple() throws Exception {
        final Point2D centerPoint = new Point2D(-10, 0);
        final List<Point2D> points = Arrays.asList(new Point2D(0, 1), new Point2D(1, 0), new Point2D(0, -1));
        final List<Point2D> expected = Arrays.asList(new Point2D(0, -1), new Point2D(1, 0), new Point2D(0, 1));
        Collections.sort(points, new Points2DAngleComparator(centerPoint));
        for (int i = 0; i < points.size(); i++) {
            assertEquals(expected.get(i), points.get(i));
        }
    }

    @Test
    public void testDistances() throws Exception {
        final Point2D centerPoint = new Point2D(0, 0);
        final List<Point2D> points = Arrays.asList(new Point2D(2, 2), new Point2D(1, 1), new Point2D(3, 3));
        final List<Point2D> expected = Arrays.asList(new Point2D(1, 1), new Point2D(2, 2), new Point2D(3, 3 ));
        Collections.sort(points, new Points2DAngleComparator(centerPoint));
        for (int i = 0; i < points.size(); i++) {
            assertEquals(expected.get(i), points.get(i));
        }
    }

}
