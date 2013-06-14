package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int h = in.readInt();
		int t = in.readInt();
		int v = in.readInt();
		int x = in.readInt();
		double first = Math.max(0, (double)(h - t * x) / (v - x));
		double second = Math.min(t, (double)h / x);
		out.printf("%.9f %.9f\n", first, second);
	}
}
