package net.egork;

import net.egork.geometry.Circle;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int radius = in.readInt();
		Point crystal = Point.readPoint(in);
		Point first = Point.readPoint(in);
		Point second = Point.readPoint(in);
		if (first.distance(crystal) < 2 * radius + GeometryUtils.epsilon && second.distance(crystal) < 2 * radius + GeometryUtils.epsilon) {
			out.printLine("Now we have enough power");
			Point p1 = new Segment(first, crystal).middle();
			Point p2 = new Segment(second, crystal).middle();
			out.printLine(p1.x, p1.y);
			out.printLine(p2.x, p2.y);
			return;
		}
		Point[] points = {crystal, first, second};
		for (int i = 0; i < 3; i++) {
			Point[] intersect = new Circle(points[(i + 1) % 3], radius).intersect(new Circle(points[(i + 2) % 3], radius));
			if (intersect.length == 0)
				continue;
			Point[] candidates;
			if (intersect.length == 1)
				candidates = intersect;
			else {
				candidates = new Point[6];
				System.arraycopy(intersect, 0, candidates, 0, 2);
				System.arraycopy(points[i].line(points[(i + 1) % 3]).intersect(new Circle(points[(i + 1) % 3], radius)), 0, candidates, 2, 2);
				System.arraycopy(points[i].line(points[(i + 2) % 3]).intersect(new Circle(points[(i + 2) % 3], radius)), 0, candidates, 4, 2);
			}
			for (Point point : candidates) {
				if (points[i].distance(point) < 2 * radius + GeometryUtils.epsilon && points[(i + 1) % 3].distance(point) < radius + GeometryUtils.epsilon &&
					points[(i + 2) % 3].distance(point) < radius + GeometryUtils.epsilon)
				{
					out.printLine("Now we have enough power");
					Point p2 = new Segment(points[i], point).middle();
					out.printLine(point.x, point.y);
					out.printLine(p2.x, p2.y);
					return;
				}
			}
		}
		out.printLine("Death");
	}
}
