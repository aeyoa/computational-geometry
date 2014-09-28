package main.convexhull;

import main.Point2D;
import main.PointSet;
import main.Points2DAngleComparator;
import main.Space2D;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Graham scan implementation.
 * TODO: exeption for two points
 *
 * @author aeyoa
 */

public class Graham implements ConvexHullBuider {


    @Override
    public ConvexHull build(final PointSet pointSet) {
        final ArrayList<Point2D> candidates = new ArrayList<>();
        final Point2D leftLowerPoint = Space2D.findLeftPoint(pointSet);
        for (Point2D point2D : pointSet) {
            /* TODO: think about this O(n^2) duplicates search. */
            if (!candidates.contains(point2D) && !point2D.equals(leftLowerPoint)) {
                candidates.add(point2D);
            }
        }

        /* Sorting candidate points according to the angles. */
        Collections.sort(candidates, new Points2DAngleComparator(leftLowerPoint));

        final ConvexHull convexHull = new ConvexHull();
        convexHull.getPoints().add(leftLowerPoint);
        convexHull.getPoints().add(candidates.get(0));
        convexHull.getPoints().add(candidates.get(1));

        int i = 1;
        while (i < candidates.size() - 1) {
            if (Space2D.crossProduct(
                    convexHull.getPoints().get(convexHull.size() - 3),
                    convexHull.getPoints().get(convexHull.size() - 2),
                    convexHull.getPoints().get(convexHull.size() - 1)) > 0) {
                i++;
                convexHull.getPoints().add(candidates.get(i));
            } else {
                convexHull.getPoints().remove(convexHull.size() - 2);
            }
        }

        /* TODO: remove this code duplication. */
        if (Space2D.crossProduct(
                convexHull.getPoints().get(convexHull.size() - 3),
                convexHull.getPoints().get(convexHull.size() - 2),
                convexHull.getPoints().get(convexHull.size() - 1)) <= 0) {
            convexHull.getPoints().remove(convexHull.size() - 2);
        }
        return convexHull;
    }

}
