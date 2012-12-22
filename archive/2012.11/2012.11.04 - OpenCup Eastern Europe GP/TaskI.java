package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int max = 0;
		int[] breaks = new int[n + 1];
		int nbreaks = 1;
		breaks[0] = 0;
		for (int i = 0; i < n; ++i) {
			int cur = in.readInt();
			max = Math.max(max, cur);
			if (max == i + 1) {
				breaks[nbreaks++] = max;
			}
		}
		out.printLine(nbreaks - 1);
		for (int i = 0; i + 1 < nbreaks; ++i) {
			out.print(breaks[i + 1] - breaks[i]);
			for (int j = breaks[i] + 1; j <= breaks[i + 1]; ++j) {
				out.print(" ");
				out.print(j);
			}
			out.printLine();
		}

	}
}
