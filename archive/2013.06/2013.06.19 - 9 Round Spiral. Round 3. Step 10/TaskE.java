package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Ray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point point = Point.readPoint(in);
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Ray ray = new Ray(from, to);
		out.printLine(ray.contains(point) ? "YES" : "NO");
    }
}
