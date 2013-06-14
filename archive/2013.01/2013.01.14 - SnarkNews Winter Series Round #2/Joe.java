package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Joe {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x0 = in.readInt();
        int y0 = in.readInt();
        int x1 = in.readInt();
        int y1 = in.readInt();
        int count = in.readInt();
        int[] x = new int[count];
        int[] y = new int[count];
        IOUtils.readIntArrays(in, x, y);
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++)
            points[i] = new Point(x[i], y[i]);
        points = convexHull(points);
        if (inside(points, x0, y0) || inside(points, x1, y1)) {
            out.printLine(-1);
            return;
        }
        int a = y0 - y1;
        int b = x1 - x0;
        int c = -a * x0 - b * y0;
        boolean good = true;
        if (points.length != 1) {
            for (int i = 0; i < points.length; i++) {
                int last = i - 1;
                if (last == -1)
                    last = points.length - 1;
                long value1 = a * points[i].x + b * points[i].y + c;
                int value2 = a * points[last].x + b * points[last].y + c;
                long vv = value1 * value2;
                if (vv > 0)
                    continue;
                int aa = points[i].y - points[last].y;
                int bb = points[last].x - points[i].x;
                int cc = -aa * points[i].x - bb * points[i].y;
                value1 = x0 * aa + y0 * bb + cc;
                value2 = x1 * aa + y1 * bb + cc;
                long vv2 = value1 * value2;
                if (vv2 >= 0)
                    continue;
                good = false;
                break;
            }
        }
        if (good) {
            out.printLine(Math.hypot(x0 - x1, y0 - y1));
            return;
        }
        Graph<Integer> graph = new BidirectionalGraph<Integer>();
        graph.addWeightedEdge(0, points.length - 1, distance(points[0], points[points.length - 1]));
        for (int i = 1; i < points.length; i++)
            graph.addWeightedEdge(i, i - 1, distance(points[i], points[i - 1]));
        addEdges(graph, points, x0, y0, -1);
        addEdges(graph, points, x1, y1, -2);
        out.printLine(ShortestDistance.dijkstraAlgorithm(graph, -1, -2).first / 1e9);
    }

    private void addEdges(Graph<Integer> graph, Point[] points, int x, int y, int id) {
        int nearest = 0;
        Point point = new Point(x, y);
        for (int i = 0; i < points.length; i++) {
            if (distance(points[i], point) < distance(points[nearest], point))
                nearest = i;
        }
        graph.addWeightedEdge(id, nearest, distance(points[nearest], point));
        int current = nearest;
        while (true) {
            int next = current + 1;
            if (next == points.length)
                next = 0;
            if (under(points[current], point, points[next]))
                break;
            graph.addWeightedEdge(id, next, distance(points[next], point));
            current = next;
        }
        current = nearest;
        while (true) {
            int next = current - 1;
            if (next == -1)
                next = points.length - 1;
            if (over(points[current], point, points[next]))
                break;
            graph.addWeightedEdge(id, next, distance(points[next], point));
            current = next;
        }
    }

    private long distance(Point point, Point other) {
        return Math.round(Math.hypot(point.x - other.x, point.y - other.y) * 1e9);
    }

    private boolean inside(Point[] points, int x, int y) {
        if (points.length == 1)
            return false;
        boolean over = false;
        boolean under = false;
        Point point = new Point(x, y);
        for (int i = 0; i < points.length; i++) {
            int last = i - 1;
            if (last == -1)
                last = points.length - 1;
            if (!over(points[last], point, points[i]))
                under = true;
            if (!under(points[last], point, points[i]))
                over = true;
        }
        return !over || !under;
    }

    private static boolean over(Point a, Point b, Point c) {
        return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) < 0;
    }

    private static boolean under(Point a, Point b, Point c) {
        return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) > 0;
    }

    public static Point[] convexHull(Point[] points) {
        if (points.length == 1)
            return points;
        Arrays.sort(points, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                int value = o1.x - o2.x;
                if (value != 0)
                    return value;
                return o1.y - o2.y;
            }
        });
        Point left = points[0];
        Point right = points[points.length - 1];
        List<Point> up = new ArrayList<Point>();
        List<Point> down = new ArrayList<Point>();
        for (Point point : points) {
            if (point == left || point == right || over(left, point, right)) {
                while (up.size() >= 2 && !over(up.get(up.size() - 2), up.get(up.size() - 1), point))
                    up.remove(up.size() - 1);
                up.add(point);
            }
            if (point == left || point == right || under(left, point, right)) {
                while (down.size() >= 2 && !under(down.get(down.size() - 2), down.get(down.size() - 1), point))
                    down.remove(down.size() - 1);
                down.add(point);
            }
        }
        Point[] result = new Point[up.size() + down.size() - 2];
        int index = 0;
        for (Point point : up)
            result[index++] = point;
        for (int i = down.size() - 2; i > 0; i--)
            result[index++] = down.get(i);
        return result;
    }

    static class Point {
        final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
