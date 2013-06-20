package net.egork;

import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point first = Point.readPoint(in);
		Point second = Point.readPoint(in);
		double a = in.readDouble();
		double b = in.readDouble();
		double c = in.readDouble();
		Line line = new Line(a, b, c);
		out.printLine(line.value(first) * line.value(second) > 0 ? "YES" : "NO");
    }
}
