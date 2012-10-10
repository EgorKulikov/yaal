package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		int d = in.readInt();
		long answer;
		if (d <= a + b)
			answer = sum(Math.max(0, d - 2 * a + 1)) - sum(Math.max(c, 2 * a) - 2 * a);
		else if (c >= a + b)
			answer = sum(Math.max(0, 2 * b - c + 1)) - sum(2 * b - Math.min(d, 2 * b));
		else
			answer = sum(b - a + 1) + sum(b - a) - sum(Math.max(c, 2 * a) - 2 * a) - sum(2 * b - Math.min(d, 2 * b));
		out.printLine(answer);
	}

	private long sum(long upTo) {
		return upTo * (upTo + 1) / 2;
	}
}
