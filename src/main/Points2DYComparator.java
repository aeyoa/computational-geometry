package main;

import java.util.Comparator;

/**
 * @author aeyoa
 *
 * Compares two Point2Ds according to the Y-coordinate.
 * In case of a tie, sort by X-coordinate.
 */
public class Points2DYComparator implements Comparator<Point2D> {

    @Override
    public int compare(final Point2D o1, final Point2D o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        if (o1.getY() == o2.getY()) {
            return new Points2DXComparator().compare(o1, o2);
        } else {
            return Double.compare(o1.getY(), o2.getY());
        }
    }
}
