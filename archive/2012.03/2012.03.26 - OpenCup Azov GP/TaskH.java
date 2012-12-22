package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int s = 2 * (n - 1);

		int k = 2;
		while (k <= n) {
			s--;
			k *= 2;
		}

		out.printLine(s);
	}
}
