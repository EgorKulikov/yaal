package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskQ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double a = in.readInt();
		double b = in.readInt();
		double c = in.readInt();
		int r = in.readInt();
		out.printFormat("%.6f %.6f %.6f\n", a, b, c + Math.hypot(a, b) * r);
		out.printFormat("%.6f %.6f %.6f\n", a, b, c - Math.hypot(a, b) * r);
    }
}
