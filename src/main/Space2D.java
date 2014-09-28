package main;

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

        /* TODO: rewrite in iterator-fashion to use Set and avoid any duplicates. */
        Point2D leftPoint = set.getPoint(0);
        for (int i = 1; i < set.getSize(); i++) {
            if (leftPoint.getX() > set.getPoint(i).getX() ||
                    ((leftPoint.getX() == set.getPoint(i).getX()) && (leftPoint.getY() > set.getPoint(i).getY()))) {
                leftPoint = set.getPoint(i);
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
}
