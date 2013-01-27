package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Quota {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String x = in.readString();
		int digitCount = in.readInt();
		double answer;
		if ("0".equals(x))
			answer = .1 * (digitCount - 1);
		else {
			answer = Math.pow(.1, x.length()) * Math.max(0, digitCount - x.length());
			if (digitCount >= x.length())
				answer += Math.pow(.1, x.length() - 1) / 9;
		}
		out.printLine(answer);
	}
}
