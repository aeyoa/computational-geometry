package main.convexhull;

import main.Point2D;
import main.PointSet;
import main.Points2DAngleComparator;
import main.Space2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Graham scan implementation
 *
 * @author aeyoa
 */

public class Graham implements ConvexHullBuider {

    @Override
    public ConvexHull build(final PointSet pointSet) {

        if (pointSet.getSize() == 0) {
            throw new IllegalArgumentException("Trying to build a convex hull of empty set of points.");
        }

        /* For three points or less just return the hull
        * with these points. We assume that convex hull for one,
        * two or three points is just the points themselves. */
        if (pointSet.getSize() < 4) {
            return new ConvexHull() {{
                for (Point2D point : pointSet) {
                    addPoint(point);
                }
            }};
        }

        final Point2D leftLowerPoint = Space2D.findLeftPoint(pointSet);

        final ArrayList<Point2D> candidates = new ArrayList<>();
        for (Point2D point2D : pointSet) {
            if (!point2D.equals(leftLowerPoint)) {
                candidates.add(point2D);
            }
        }

        /* Sorting candidate points according to the angle with leftLowerPoint. */
        Collections.sort(candidates, new Points2DAngleComparator(leftLowerPoint));

        /* If all points in the set are collinear
        * we return the leftLower point and the opposite one. */
        if (Space2D.isCollinear(pointSet)) {
            return new ConvexHull() {{
                addPoint(leftLowerPoint);
                /* Points2DAngleComparator sorts points according the distance
                 * if angles are equal. So just grab the last candidate. */
                addPoint(candidates.get(candidates.size() - 1));
            }};
        }

        final Stack<Point2D> convexHull = new Stack<>();
        convexHull.push(leftLowerPoint);
        convexHull.push(candidates.get(0));
        convexHull.push(candidates.get(1));

        for (int i = 2; i < candidates.size(); i++) {
            while (Space2D.crossProduct(
                    convexHull.get(convexHull.size() - 2),
                    convexHull.peek(),
                    candidates.get(i)) <= 0) {
                convexHull.pop();
            }
            convexHull.push(candidates.get(i));
        }

        final ConvexHull result = new ConvexHull();
        for (Point2D p : convexHull) {
            result.addPoint(p);
        }
        return result;
    }

}
