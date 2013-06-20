package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Vector first = new Vector(Point.readPoint(in));
		Vector second = new Vector(Point.readPoint(in));
		out.printFormat("%.5f\n", Math.abs(first.angleTo(second)));
    }
}
