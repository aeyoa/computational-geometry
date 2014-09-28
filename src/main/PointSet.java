package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author aeyoa
 */
public class PointSet implements Iterable<Point2D> {

    private static final Random random = new Random();
    private static final int randomScale = 100;
    private final List<Point2D> points;

    /* Generate random points set. */
    private PointSet(final int count) {
        this.points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            points.add(new Point2D(random.nextDouble() * randomScale, random.nextDouble() * randomScale));
        }
    }

    /* Make set from specified points. */
    private PointSet(final List<Point2D> points) {
        this.points = points;
    }


    public static PointSet getRandomSet(final int count) {
        return new PointSet(count);
    }

    public static PointSet getSetFromPoints(final List<Point2D> points) {
        return new PointSet(points);
    }

    public int getSize() {
        return points.size();
    }

    public Point2D getPoint(final int index) {
        return points.get(index);
    }

    @Override
    public Iterator<Point2D> iterator() {
        return points.iterator();
    }
}
