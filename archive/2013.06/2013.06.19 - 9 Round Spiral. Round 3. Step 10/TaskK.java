package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Segment first = new Segment(from, to);
		from = Point.readPoint(in);
		to = Point.readPoint(in);
		Segment second = new Segment(from, to);
		out.printLine(first.distance(second) < GeometryUtils.epsilon ? "YES" : "NO");
    }
}
