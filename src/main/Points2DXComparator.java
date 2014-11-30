package main;

import java.util.Comparator;

/**
 * @author aeyoa
 *
 * Compares two Point2Ds according to the X-coordinate.
 * In case of a tie, sort by Y-coordinate.
 */
public class Points2DXComparator implements Comparator<Point2D> {

    @Override
    public int compare(final Point2D o1, final Point2D o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        if (o1.getX() == o2.getX()) {
            return new Points2DYComparator().compare(o1, o2);
        } else {
            return Double.compare(o1.getX(), o2.getX());
        }
    }
}
