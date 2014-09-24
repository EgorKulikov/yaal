package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		if (m * n == 1) {
			out.printLine(1);
			return;
		}
		double answer = 1d / n + (n - 1d) * (m - 1) / (m * n - 1) / n;
		out.printLine(answer);
    }
}
