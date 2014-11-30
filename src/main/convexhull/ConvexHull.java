package main.convexhull;

import main.Point2D;
import main.Space2D;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author aeyoa
 */
public class ConvexHull implements Iterable<Point2D> {

    private final LinkedList<Point2D> points;

    public ConvexHull() {
        points = new LinkedList<>();
    }

    public void addPoint(final Point2D point) {
        points.add(point);
    }

    public LinkedList<Point2D> getPoints() {
        return points;
    }

    public int size() {
        return points.size();
    }

    public Point2D getPoint(final int index) {
        return points.get(index);
    }

    @Override
    public Iterator<Point2D> iterator() {
        return null;
    }

    /* Checks if this ConvexHull is actually the convex hull.
    * In this method we assume that convex hull has CCW rotation.
    * Needed for tests only. */
    public boolean checkConvexHullCCWProperty() {
        final LinkedList<Point2D> tempList = new LinkedList<>();
        tempList.addAll(points);
        tempList.add(points.peekFirst());
        for (int i = 2; i < tempList.size(); i++) {
            if (Space2D.crossProduct(tempList.get(i - 2), tempList.get(i - 1), tempList.get(i)) <= 0) {
                return false;
            }
        }
        return true;
    }
}
