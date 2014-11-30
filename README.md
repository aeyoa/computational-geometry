Сomputational geometry
======================

#### Run
The easiest way to run the code is to use IntelliJ IDEA: just open .iml file and you are ready to go.

[Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## Graham scan
Graham's scan is a method of computing the convex hull of a finite set of points in the plane.

[Read more on Wikipedia](http://en.wikipedia.org/wiki/Graham_scan)

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


## Andrew's monotone chain algorithm 
Monotone chain algorithm is actually a modified Graham scan. In the Graham scan we need to sort points by angle so that requires a lot of trigonometric operations. The main goal of this GS modification is to get rid of all trigonometric functions and use only plain arithmetic. 
We can achieve that by doing the following:

![Monotone chain](https://raw.githubusercontent.com/aeyoa/computational-geometry/master/demo/mc-algo.png)

1. Connect the leftmost point of the original point set and the rightmost point.
2. Split the original point set into UPPER and LOWER parts.
3. Sort points according to x-coordinate in the LOWER set.
4. Sort points according to x-coordinate in the UPPER set and reverse the sorted list.
5. Apply Graham scan to the UPPER and LOWER sets.
6. Merge UPPER and LOWER hulls. 

Another advantage of this modification is that we can build LOWER and UPPER hulls in different threads.
As a result the Monotone chain algorithm is 2,5x faster than regular Graham scan. 
```
Graham: 503 ms
Monotone chain: 184 ms
```

#### Using 
```java
final ConvexHull hull = new MonotoneChain().build(pointSet);
for (Point2D point2D : hull) {
    System.out.println(point2D);
}
```
