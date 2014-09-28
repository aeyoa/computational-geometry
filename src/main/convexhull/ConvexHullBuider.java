package main.convexhull;

import main.PointSet;

/**
 * @author aeyoa
 */
public interface ConvexHullBuider {

    public ConvexHull build(final PointSet set);
}
