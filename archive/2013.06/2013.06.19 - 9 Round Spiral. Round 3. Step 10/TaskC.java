package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Vector vector = new Vector(from, to);
		out.printFormat("%.6f\n", vector.length());
    }
}
