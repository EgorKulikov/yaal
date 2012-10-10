package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long blockCount = in.readLong();
		long maxDelta = 0;
		long minDelta = Long.MAX_VALUE;
		for (long a = 1; a * a * a <= blockCount; a++) {
			if (blockCount % a != 0)
				continue;
			for (long b = a; a * b * b <= blockCount; b++) {
				if (blockCount % (a * b) != 0)
					continue;
				long c = blockCount / (a * b);
				long current = 2 * (a * b + a * c + b * c + a + b + c);
				long current1 = current - b * c + 2 * a;
				long current2 = current - a * c + 2 * b;
				long current3 = current - a * b + 2 * c;
				maxDelta = Math.max(Math.max(maxDelta, current1), Math.max(current2, current3));
				minDelta = Math.min(Math.min(minDelta, current1), Math.min(current2, current3));
			}
		}
		out.printLine(minDelta + 4, maxDelta + 4);
	}
}
