package net.egork;

import net.egork.collections.map.Indexer;
import net.egork.collections.set.EHashSet;
import net.egork.geometry.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = Point.readPoint(in);
		@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
		Indexer<Point> indexer = new Indexer<Point>();
		for (int i = 0; i < count; i++)
			indexer.get(points[i]);
		Polygon hull = Polygon.convexHull(points);
		Set<Point> hullSet = new EHashSet<Point>(Arrays.asList(hull.vertices));
		if (hullSet.size() == count) {
			int[] answer = new int[count];
			int i = 0;
			for (Point point : hull.vertices)
				answer[i++] = indexer.get(point);
			out.print("Case #" + testNumber + ": ");
			out.printLine(answer);
			return;
		}
		final Line line = hull.vertices[0].line(hull.vertices[1]);
		final Line perpendicular = line.perpendicular(hull.vertices[0]);
		List<Point> notOnHull = new ArrayList<Point>();
		for (Point point : points) {
			if (!hullSet.contains(point))
				notOnHull.add(point);
		}
		Collections.sort(notOnHull, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				double value = line.distance(o1) - line.distance(o2);
				if (Math.abs(value) > GeometryUtils.epsilon)
					return Double.compare(value, 0);
				return Double.compare(perpendicular.value(o1), perpendicular.value(o2));
			}
		});
		Point farthest = notOnHull.get(notOnHull.size() - 1);
		notOnHull.remove(notOnHull.size() - 1);
		Line divider = farthest.line(new Segment(hull.vertices[0], hull.vertices[1]).middle());
		List<Point> top = new ArrayList<Point>();
		List<Point> bottom = new ArrayList<Point>();
		for (Point point : notOnHull) {
			if (divider.value(point) > 0)
				top.add(point);
			else
				bottom.add(point);
		}
		if (divider.value(hull.vertices[1]) < 0) {
			List<Point> temp = top;
			top = bottom;
			bottom = temp;
		}
		List<Point> internal = new ArrayList<Point>();
		Collections.reverse(bottom);
		internal.add(hull.vertices[1]);
		internal.addAll(top);
		internal.add(farthest);
		internal.addAll(bottom);
		internal.add(hull.vertices[0]);
		if (new Polygon(internal.toArray(new Point[internal.size()])).square() * 2 < hull.square() - 1e-5) {
			int[] answer = new int[count];
			int i = 0;
			for (Point point : internal)
				answer[i++] = indexer.get(point);
			for (int j = hull.vertices.length - 1; j >= 2; j--)
				answer[i++] = indexer.get(hull.vertices[j]);
			out.print("Case #" + testNumber + ": ");
			out.printLine(answer);
			return;
		}
		List<Point> remaining = new ArrayList<Point>();
		for (int j = 2; j < hull.vertices.length; j++) {
			Point point = hull.vertices[j];
			if (line.distance(point) < line.distance(farthest))
				notOnHull.add(point);
			else
				remaining.add(point);
		}
		Collections.sort(notOnHull, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				double value = line.distance(o1) - line.distance(o2);
				if (Math.abs(value) > GeometryUtils.epsilon)
					return Double.compare(value, 0);
				return Double.compare(perpendicular.value(o1), perpendicular.value(o2));
			}
		});
		top = new ArrayList<Point>();
		bottom = new ArrayList<Point>();
		for (Point point : notOnHull) {
			if (divider.value(point) > 0)
				top.add(point);
			else
				bottom.add(point);
		}
		int multiplier = divider.value(hull.vertices[1]) > 0 ? 1 : -1;
		if (divider.value(hull.vertices[1]) < 0) {
			List<Point> temp = top;
			top = bottom;
			bottom = temp;
		}
		for (Point point : remaining) {
			if (divider.value(point) * multiplier > 0)
				top.add(point);
		}
		Collections.reverse(remaining);
		for (Point point : remaining) {
			if (divider.value(point) * multiplier <= 0)
				bottom.add(point);
		}
		List<Point> polygon = new ArrayList<Point>();
		Collections.reverse(bottom);
		polygon.add(hull.vertices[1]);
		polygon.addAll(top);
		polygon.add(farthest);
		polygon.addAll(bottom);
		polygon.add(hull.vertices[0]);
		int[] answer = new int[count];
		int i = 0;
		for (Point point : polygon)
			answer[i++] = indexer.get(point);
		out.print("Case #" + testNumber + ": ");
		out.printLine(answer);
	}
}
