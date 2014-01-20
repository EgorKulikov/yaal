package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Curling {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] first = new Point[count];
		for (int i = 0; i < count; i++)
			first[i] = Point.readPoint(in);
		Point[] second = new Point[count];
		for (int i = 0; i < count; i++)
			second[i] = Point.readPoint(in);
		out.printLine(inside(first, second), inside(second, first));
    }

	private int inside(Point[] outer, Point[] inner) {
		Polygon polygon = Polygon.convexHull(outer);
		double[] angle = new double[inner.length];
		for (int i = 0; i < inner.length; i++)
			angle[i] = Math.atan2(inner[i].y - polygon.vertices[0].y, inner[i].x - polygon.vertices[0].x);
		int[] order = ArrayUtils.order(angle);
		int at = polygon.vertices.length - 1;
		int next = at;
		double curAngle = Math.atan2(polygon.vertices[at].y - polygon.vertices[0].y, polygon.vertices[at].x - polygon.vertices[0].x);
		Polygon triangle = new Polygon(polygon.vertices[0], polygon.vertices[at], polygon.vertices[next]);
		int result = 0;
		for (int i : order) {
			while (angle[i] > curAngle && at > 1) {
				next = at--;
				curAngle = Math.atan2(polygon.vertices[at].y - polygon.vertices[0].y, polygon.vertices[at].x - polygon.vertices[0].x);
				triangle = new Polygon(polygon.vertices[0], polygon.vertices[at], polygon.vertices[next]);
			}
			boolean inside = triangle.contains(inner[i]);
			for (Segment segment : triangle.sides())
				inside |= segment.contains(inner[i], true);
			if (inside)
				result++;
		}
		return result;
	}
}
