package main.convexhull;

import main.Point2D;
import main.PointSet;
import main.Points2DXComparator;
import main.Space2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.*;

/**
 * @author aeyoa
 */
public class MonotoneChain implements ConvexHullBuilder {

    @Override
    public ConvexHull build(final PointSet pointSet) {

        if (pointSet.getSize() == 0) {
            throw new IllegalArgumentException("Trying to build a convex hull of empty set of points.");
        }

        /* For three points or less just return the hull
        * with these points. We assume that convex hull for one,
        * two or three points is just the points themselves. */
        if (pointSet.getSize() < 4) {
            return new ConvexHull() {{
                for (Point2D point : pointSet) {
                    addPoint(point);
                }
            }};
        }

        final Point2D[] boundaryPoints = Space2D.findBoundaryPoints(pointSet);
        final Point2D leftLowerPoint = boundaryPoints[0];
        final Point2D rightUpperPoint = boundaryPoints[2];

        /* If all points in the set are collinear
        * we return the leftLower point and rightUpper point. */
        if (Space2D.isCollinear(pointSet)) {
            return new ConvexHull() {{
                addPoint(leftLowerPoint);
                addPoint(rightUpperPoint);
            }};
        }

        /* Adding both points to upper and lower hulls. */
        final PointSet upperSet = new PointSet(leftLowerPoint, rightUpperPoint);
        final PointSet lowerSet = new PointSet(leftLowerPoint, rightUpperPoint);

        /* Divide set of points into lower and upper sets. */
        for (Point2D point : pointSet) {
            /* (crossProduct != 0) removes all points in between leftLower and rightUpper points. */
            if (Space2D.crossProduct(leftLowerPoint, rightUpperPoint, point) > 0) {
                upperSet.add(point);
            } else if (Space2D.crossProduct(leftLowerPoint, rightUpperPoint, point) < 0) {
                lowerSet.add(point);
            }
        }

        final ExecutorService executor = Executors.newFixedThreadPool(2);
        final Future<List<Point2D>> lowerHullFuture = executor.submit(new HullBuilder(lowerSet, false));
        final Future<List<Point2D>> upperHullFuture = executor.submit(new HullBuilder(upperSet, true));
        ConvexHull convexHull = new ConvexHull();
        try {
            final List<Point2D> lowerHull = lowerHullFuture.get();
            final List<Point2D> upperHull = upperHullFuture.get();
            for (int i = 0; i < lowerHull.size() - 1; i++) {
                convexHull.addPoint(lowerHull.get(i));
            }
            for (int i = 0; i < upperHull.size() - 1; i++) {
                convexHull.addPoint(upperHull.get(i));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return convexHull;
    }

    private class HullBuilder implements Callable<List<Point2D>> {

        final PointSet pointSet;
        final boolean isUpper;

        private HullBuilder(final PointSet pointSet, final boolean isUpper) {
            this.pointSet = pointSet;
            this.isUpper = isUpper;
        }

        @Override
        public List<Point2D> call() throws Exception {

            final ArrayList<Point2D> candidates = new ArrayList<>();
            for (Point2D point : pointSet) {
                candidates.add(point);
            }
            Collections.sort(candidates, new Points2DXComparator());
            if (isUpper) {
                Collections.reverse(candidates);
            }

            if (candidates.size() < 4) {
                return candidates;
            }

            final Stack<Point2D> convexHull = new Stack<>();
            convexHull.push(candidates.get(0));
            convexHull.push(candidates.get(1));

            for (int i = 2; i < candidates.size(); i++) {
                while (Space2D.crossProduct(
                        convexHull.get(convexHull.size() - 2),
                        convexHull.get(convexHull.size() - 1),
                        candidates.get(i)) <= 0) {
                    convexHull.pop();
                    if (convexHull.size() < 2) {
                        convexHull.push(candidates.get(i++));
                    }
                }
                convexHull.push(candidates.get(i));
            }

            return convexHull;
        }
    }
}
