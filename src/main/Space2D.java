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
        if (set.getSize() < 1) {
            throw new NoSuchElementException();
        }

        final Iterator<Point2D> pointsIterator = set.iterator();
        Point2D leftPoint = pointsIterator.next();
        while (pointsIterator.hasNext()) {
            Point2D p = pointsIterator.next();
            if (leftPoint.getX() > p.getX() ||
                    ((leftPoint.getX() == p.getX()) && (leftPoint.getY() > p.getY()))) {
                leftPoint = p;
            }
        }

        return leftPoint;
    }

    public static double getDistance(final Point2D first, final Point2D second) {
        final double dX = first.getX() - second.getX();
        final double dY = first.getY() - second.getY();
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static double crossProduct(final Point2D a, final Point2D b, final Point2D c) {
        final Point2D u = new Point2D(b.getX() - a.getX(), b.getY() - a.getY());
        final Point2D v = new Point2D(c.getX() - a.getX(), c.getY() - a.getY());
        return u.getX() * v.getY() - u.getY() * v.getX();
    }

    public static boolean isCollinear(final PointSet points) {
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
