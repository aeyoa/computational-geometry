package main;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author aeyoa
 */
public class Space2D {

    /* Calculates angles between two points.
    * Range from (-PI to PI]. */
    public static double getAngle(final Point2D first, final Point2D second) {
        return Math.atan2(second.getY() - first.getY(), second.getX() - first.getX());
    }

    public static Point2D findLeftPoint(final PointSet set) {
        return findBoundaryPoints(set)[0];
    }

    /** @return array of boundary points in order [left(0), lower(1), right(2), top(3)] */
    public synchronized static Point2D[] findBoundaryPoints(final PointSet set) {
        if (set.getSize() < 1) {
            throw new NoSuchElementException();
        }
        final Points2DXComparator byX = new Points2DXComparator();
        final Points2DYComparator byY = new Points2DYComparator();
        final Iterator<Point2D> pointsIterator = set.iterator();
        Point2D firstPoint = pointsIterator.next();
        final Point2D[] boundaryPoints = {firstPoint, firstPoint, firstPoint, firstPoint};
        while (pointsIterator.hasNext()) {
            Point2D p = pointsIterator.next();
            /* Left point */
            if (byX.compare(boundaryPoints[0], p) > 0) {
                boundaryPoints[0] = p;
            }
            /* Right point */
            if (byX.compare(boundaryPoints[2], p) < 0) {
                boundaryPoints[2] = p;
            }
            /* Lower point */
            if (byY.compare(boundaryPoints[1], p) > 0) {
                boundaryPoints[1] = p;
            }
            /* Top point */
            if (byY.compare(boundaryPoints[3], p) < 0) {
                boundaryPoints[3] = p;
            }
        }
        return boundaryPoints;
    }

    public synchronized static double getDistance(final Point2D first, final Point2D second) {
        final double dX = first.getX() - second.getX();
        final double dY = first.getY() - second.getY();
        return Math.sqrt(dX * dX + dY * dY);
    }

    /** @return cross product for three points
     * if [result > 0] points are counter-clockwise
     * if [result < 0] points are clockwise
     * if [result = 0] points are collinear */
     public synchronized static double crossProduct(final Point2D a, final Point2D b, final Point2D c) {
        final Point2D u = new Point2D(b.getX() - a.getX(), b.getY() - a.getY());
        final Point2D v = new Point2D(c.getX() - a.getX(), c.getY() - a.getY());
        return u.getX() * v.getY() - u.getY() * v.getX();
    }

    public synchronized static boolean isCollinear(final PointSet points) {
        if (points.getSize() < 3) {
            return true;
        }
        final Iterator<Point2D> pointsIterator = points.iterator();
        final Point2D a = pointsIterator.next();
        final Point2D b = pointsIterator.next();
        while (pointsIterator.hasNext()) {
            if (crossProduct(a, b, pointsIterator.next()) != 0) {
                return false;
            }
        }
        return true;
    }
}
