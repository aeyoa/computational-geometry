package main.convexhull;

import main.PointSet;

/**
 * @author aeyoa
 */
public interface ConvexHullBuilder {

    public ConvexHull build(final PointSet set);
}
