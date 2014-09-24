package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		if (n == 0) {
			out.printLine(0, 1);
			out.printLine(0, m);
			out.printLine(0, 0);
			out.printLine(0, m - 1);
			return;
		}
		if (m == 0) {
			out.printLine(1, 0);
			out.printLine(n, 0);
			out.printLine(0, 0);
			out.printLine(n - 1, 0);
			return;
		}
		if (n <= m && n <= 3) {
			out.printLine(0, 0);
			out.printLine(n, m);
			out.printLine(n, 0);
			out.printLine(0, m);
			return;
		}
		if (m <= n && m <= 3) {
			out.printLine(0, 0);
			out.printLine(n, m);
			out.printLine(0, m);
			out.printLine(n, 0);
			return;
		}
		if (n >= m) {
			out.printLine(0, 1);
			out.printLine(n, m);
			out.printLine(0, 0);
			out.printLine(n, m - 1);
		} else {
			out.printLine(1, 0);
			out.printLine(n, m);
			out.printLine(0, 0);
			out.printLine(n - 1, m);
		}
    }
}
