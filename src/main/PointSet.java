package main;

import java.util.*;

/**
 * @author aeyoa
 */
public class PointSet implements Iterable<Point2D> {

    private static final Random random = new Random();
    private static final int randomScale = 100;
    private final Set<Point2D> points;

    /* Nice vararg constructor for testing. */
    public PointSet(final Point2D... point2Ds) {
        this.points = new HashSet<>();
        points.addAll(Arrays.asList(point2Ds));
    }

    /* Generate random points set. */
    private PointSet(final int count) {
        this.points = new HashSet<>();
        for (int i = 0; i < count; i++) {
            points.add(new Point2D(random.nextDouble() * randomScale, random.nextDouble() * randomScale));
        }
    }

    /* Make set from specified points. */
    private PointSet(final Set<Point2D> points) {
        this.points = points;
    }

    public static PointSet getRandomSet(final int count) {
        return new PointSet(count);
    }

    public static PointSet getSetFromPoints(final Set<Point2D> points) {
        return new PointSet(points);
    }

    public void add(final Point2D point) {
        points.add(point);
    }

    public int getSize() {
        return points.size();
    }

    @Override
    public Iterator<Point2D> iterator() {
        return points.iterator();
    }
}
