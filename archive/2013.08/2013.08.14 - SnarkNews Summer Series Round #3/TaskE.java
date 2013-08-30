package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		out.printLine(go(to) - go(from - 1));
    }

	private long go(int count) {
		long answer = 0;
		while (count != 0) {
			int full = Integer.highestOneBit(count);
			count -= full;
			answer += calculate(full);
		}
		return answer;
	}

	private long calculate(int full) {
		if (full == 1)
			return 1;
		return calculate(full >> 1) * 2 + full;
	}
}
