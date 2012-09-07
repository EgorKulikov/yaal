package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Gym {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = Point.readPoint(in);
		Polygon polygon = new Polygon(points);
		double angle0 = Math.atan2(points[0].y - points[count - 1].y, points[0].x - points[count - 1].x);
		double best = Double.POSITIVE_INFINITY;
		int index = -1;
		for (int i = 1; i < count; i++) {
			double delta = Math.atan2(points[i].y - points[i - 1].y, points[i].x - points[i - 1].x) - angle0;
			while (delta > Math.PI)
				delta -= Math.PI;
			while (delta < 0)
				delta += Math.PI;
			if (Math.abs(delta - Math.PI / 2) < best) {
				best = Math.abs(delta - Math.PI / 2);
				index = i - 1;
			}
		}
		double square = polygon.square();
		Line first = points[count - 1].line(points[0]);
		double delta = 2 * square / count / points[0].distance(points[count - 1]);
		first = new Line(first.a, first.b, first.c - delta);
		Line second = points[index].line(points[index + 1]);
		delta = 2 * square / count / points[index].distance(points[index + 1]);
		second = new Line(second.a, second.b, second.c - delta);
		Point center = first.intersect(second);
		double[] squares = new double[count];
		squares[0] = square / count;
		for (int i = 1; i < count; i++)
			squares[i] = Polygon.triangleSquare(center, points[i], points[i - 1]);
		if (CollectionUtils.maxElement(Array.wrap(squares)) - CollectionUtils.minElement(Array.wrap(squares)) < 1e-3)
			out.printLine(center.x, center.y);
		else
			out.printLine("Impossible");
	}
}
