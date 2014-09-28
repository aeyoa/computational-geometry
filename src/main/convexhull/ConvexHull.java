package main.convexhull;

import main.Point2D;

import java.util.LinkedList;
import java.util.Iterator;

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
}
