package net.egork;

import net.egork.collections.Pair;
import net.egork.geometry.*;
import net.egork.geometry.Vector;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskM {
	Line line;
	Point p;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int side = in.readInt();
		p = Point.readPoint(in);
		final Segment[] segments = new Segment[count + 4];
		segments[count] = new Segment(new Point(0, 0), new Point(0, side));
		segments[count + 1] = new Segment(new Point(side, side), new Point(0, side));
		segments[count + 2] = new Segment(new Point(0, 0), new Point(side, 0));
		segments[count + 3] = new Segment(new Point(side, 0), new Point(side, side));
		for (int i = 0; i < count; i++)
			segments[i] = new Segment(Point.readPoint(in), Point.readPoint(in));
		count += 4;
		double[] directions = new double[2 * count];
		for (int i = 0; i < count; i++) {
			directions[i] = GeometryUtils.canonicalAngle(new Vector(p, segments[i].a).angle() + 1);
			directions[i + count] = GeometryUtils.canonicalAngle(new Vector(p, segments[i].b).angle() + 1);
		}
		int[] order = ArrayUtils.order(directions);
		line = new Line(p, -Math.PI - 1);
		NavigableSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				Point p1 = intersect(segments[o1]);
				Point p2 = intersect(segments[o2]);
				int result = Double.compare(p1.distance(p), p2.distance(p));
				if (result != 0)
					return result;
				return o1 - o2;
			}
		});
		List<Pair<Double, Double>>[] parts = new List[count];
		for (int i = 0; i < count; i++)
			parts[i] = new ArrayList<Pair<Double, Double>>();
		for (int i = 0; i < count; i++) {
			double one = Math.min(directions[i], directions[i + count]);
			double two = Math.max(directions[i], directions[i + count]);
			if (two - one > Math.PI)
				set.add(i);
		}
		double answer = 0;
		double lastAngle = -Math.PI - 1;
		for (int i : order) {
			double curAngle = directions[i] - 1;
			i = i >= count ? i - count : i;
			boolean contains = set.contains(i);
			if (!set.isEmpty()) {
				int closest = set.first();
				parts[closest].add(Pair.makePair(lastAngle, curAngle));
			}
			if (contains)
				set.remove(i);
			else
				set.add(i);
			lastAngle = curAngle;
		}
		int close = set.first();
		double curAngle = Math.PI - 1;
		parts[close].add(Pair.makePair(lastAngle, curAngle));
		for (int i = 0; i < parts.length; i++) {
			List<Pair<Double, Double>> list = parts[i];
			double start = Double.NEGATIVE_INFINITY;
			double end = Double.NEGATIVE_INFINITY;
			Segment closest = segments[i];
			for (Pair<Double, Double> pair : list) {
				if (pair.first - end > 1e-12) {
					if (start != Double.NEGATIVE_INFINITY) {
						line = new Line(p, start);
						Point p1 = intersect(closest);
						line = new Line(p, end);
						Point p2 = intersect(closest);
						answer += Polygon.triangleSquare(p, p1, p2);
					}
					start = pair.first;
				}
				end = pair.second;
			}
			if (start != Double.NEGATIVE_INFINITY) {
				line = new Line(p, start);
				Point p1 = intersect(closest);
				line = new Line(p, end);
				Point p2 = intersect(closest);
				answer += Polygon.triangleSquare(p, p1, p2);
			}
		}
		out.printFormat("%.5f\n", answer);
    }

	private Point intersect(Segment segment) {
		Line sLine = segment.line();
		if (sLine.parallel(line)) {
			if (p.distance(segment.a) < p.distance(segment.b))
				return segment.a;
			return segment.b;
		}
		return sLine.intersect(line);
	}
}
