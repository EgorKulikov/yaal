package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskR {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point base = Point.readPoint(in);
		Point first = Point.readPoint(in);
		Point second = Point.readPoint(in);
		Line line = new Line(base, (new Vector(base, first).angle() + new Vector(base, second).angle()) / 2);
		line = canonize(line);
		out.printFormat("%.3f %.3f %.3f\n", 100 * line.a, 100 * line.b, 100 * line.c);
    }

	private Line canonize(Line line) {
		double a = Math.abs(line.a) > GeometryUtils.epsilon ? line.a : 0;
		double b = Math.abs(line.b) > GeometryUtils.epsilon ? line.b : 0;
		double c = Math.abs(line.c) > GeometryUtils.epsilon ? line.c : 0;
		return new Line(a, b, c);
	}
}
