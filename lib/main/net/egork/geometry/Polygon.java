package net.egork.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Polygon {
    public final Point[] vertices;
    private Segment[] sides;

    public Polygon(Point... vertices) {
        this.vertices = vertices.clone();
    }

    public double area() {
        double sum = 0;
        for (int i = 1; i < vertices.length; i++) {
            sum += (vertices[i].x - vertices[i - 1].x) * (vertices[i].y + vertices[i - 1].y);
        }
        sum += (vertices[0].x - vertices[vertices.length - 1].x) * (vertices[0].y + vertices[vertices.length - 1].y);
        return Math.abs(sum) / 2;
    }

    public Point center() {
        double sx = 0;
        double sy = 0;
        for (Point point : vertices) {
            sx += point.x;
            sy += point.y;
        }
        return new Point(sx / vertices.length, sy / vertices.length);
    }

    public static boolean over(Point a, Point b, Point c) {
        return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) < -GeometryUtils.epsilon;
    }

    private static boolean under(Point a, Point b, Point c) {
        return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) > GeometryUtils.epsilon;
    }

    public static Polygon convexHull(Point[] points) {
        if (points.length == 1) {
            return new Polygon(points);
        }
        Arrays.sort(points, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                int value = Double.compare(o1.x, o2.x);
                if (value != 0) {
                    return value;
                }
                return Double.compare(o1.y, o2.y);
            }
        });
        Point left = points[0];
        Point right = points[points.length - 1];
        List<Point> up = new ArrayList<Point>();
        List<Point> down = new ArrayList<Point>();
        for (Point point : points) {
            if (point == left || point == right || !under(left, point, right)) {
                while (up.size() >= 2 && under(up.get(up.size() - 2), up.get(up.size() - 1), point)) {
                    up.remove(up.size() - 1);
                }
                up.add(point);
            }
            if (point == left || point == right || !over(left, point, right)) {
                while (down.size() >= 2 && over(down.get(down.size() - 2), down.get(down.size() - 1), point)) {
                    down.remove(down.size() - 1);
                }
                down.add(point);
            }
        }
        Point[] result = new Point[up.size() + down.size() - 2];
        int index = 0;
        for (Point point : up) {
            result[index++] = point;
        }
        for (int i = down.size() - 2; i > 0; i--) {
            result[index++] = down.get(i);
        }
        return new Polygon(result);
    }

    public boolean contains(Point point) {
        return contains(point, false);
    }

    public boolean contains(Point point, boolean strict) {
        for (Segment segment : sides()) {
            if (segment.contains(point, true)) {
                return !strict;
            }
        }
        double totalAngle = GeometryUtils.canonicalAngle(Math.atan2(vertices[0].y - point.y, vertices[0].x - point.x) -
                Math.atan2(vertices[vertices.length - 1].y - point.y, vertices[vertices.length - 1].x - point.x));
        for (int i = 1; i < vertices.length; i++) {
            totalAngle += GeometryUtils.canonicalAngle(Math.atan2(vertices[i].y - point.y, vertices[i].x - point.x) -
                    Math.atan2(vertices[i - 1].y - point.y, vertices[i - 1].x - point.x));
        }
        return Math.abs(totalAngle) > Math.PI;
    }

    public Segment[] sides() {
        if (sides == null) {
            sides = new Segment[vertices.length];
            for (int i = 0; i < vertices.length - 1; i++) {
                sides[i] = new Segment(vertices[i], vertices[i + 1]);
            }
            sides[sides.length - 1] = new Segment(vertices[vertices.length - 1], vertices[0]);
        }
        return sides;
    }

    public static double triangleArea(Point a, Point b, Point c) {
        return Math.abs((a.x - b.x) * (a.y + b.y) + (b.x - c.x) * (b.y + c.y) + (c.x - a.x) * (c.y + a.y)) / 2;
    }

    public double perimeter() {
        double result = vertices[0].distance(vertices[vertices.length - 1]);
        for (int i = 1; i < vertices.length; i++) {
            result += vertices[i].distance(vertices[i - 1]);
        }
        return result;
    }
}
