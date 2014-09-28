package main;

import java.util.Comparator;

/**
 * @author aeyoa
 *
 * Help to compare two points by angle with a given Point2D.
 * If angles are equal compares point by distance to the given point.
 */
public class Points2DAngleComparator implements Comparator<Point2D> {

    private final Point2D centerPoint;

    public Points2DAngleComparator(final Point2D centerPoint) {
        this.centerPoint = centerPoint;
    }

    @Override
    public int compare(final Point2D o1, final Point2D o2) {
        final double angle1 = Space2D.getAngle(centerPoint, o1);
        final double angle2 = Space2D.getAngle(centerPoint, o2);
        if (Double.compare(angle1, angle2) == 0) {
            /* In case of equal angles sorting according to the length. */
            final double dist1 = Space2D.getDistance(centerPoint, o1);
            final double dist2 = Space2D.getDistance(centerPoint, o2);
            return Double.compare(dist1, dist2);
        }
        return Double.compare(angle1, angle2);
    }
}
