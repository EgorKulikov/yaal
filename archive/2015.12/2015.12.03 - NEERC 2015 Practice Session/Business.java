package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Business {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			int u = in.readInt();
			int d = in.readInt();
			int current = u * n % (u + d);
			if (current == 0)
				current = u + d;
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
