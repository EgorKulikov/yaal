package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point a = Point.readPoint(in);
		Point b = Point.readPoint(in);
		Point start = new Point(0, 0);
		double answer = start.distance(a) + start.distance(b);
		answer = Math.min(answer, Math.abs(start.distance(a) - start.distance(b)) +
			Math.min(start.distance(a), start.distance(b)) * Math.abs(GeometryUtils.canonicalAngle(a.angle() - b.angle())));
		out.printLine(answer);
	}
}
