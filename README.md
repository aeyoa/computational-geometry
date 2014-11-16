Сomputational geometry
======================

## Graham scam
Graham's scan is a method of computing the convex hull of a finite set of points in the plane.

[Read more on Wikipedia](http://en.wikipedia.org/wiki/Graham_scan)

#### Run
The easiest way to run the code is to use IntelliJ IDEA: just open .iml file and you are ready to go.

[Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

#### Using 
```java
/* Create the set of points to work with */
final PointSet pointSet = new PointSet();
pointSet.add(new Point2D(-1, -1));
pointSet.add(new Point2D(1, -1));
pointSet.add(new Point2D(-1, 1));
pointSet.add(new Point2D(1, 1));
pointSet.add(new Point2D(0, 0));

/* Create Graham object */
final Graham grahamScan = new Graham();

/* Build convex hull from the set of points */
final ConvexHull hull = grahamScan.build(pointSet);

/* Enjoy the result */
for (Point2D point : hull) {
    System.out.println(point);
}
```

#### Simple UI

You can try the algorithm in the simple UI `src/ui/GrahamUI`

Click to add a point, `Reset` to remove them all.

![Graham demo](https://raw.githubusercontent.com/aeyoa/computational-geometry/master/demo/graham-demo.gif)


