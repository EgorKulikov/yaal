package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		long result = 0;
		for (int i = 0; i < 63; i++) {
			if ((from >> i & 1) != 0) {
				long delta = 1L << i;
				if (to - delta >= from) {
					long next = from + delta;
					if (i == 0)
						result ^= from;
					else if (i == 1)
						result ^= 1;
					if (length(next) != length(from))
						result <<= 1;
					from = next;
				}
			}
		}
		for (int i = 62; i >= 0; i--) {
			long delta = 1L << i;
			if (to - delta >= from) {
				long next = from + delta;
				if (i == 0)
					result ^= from;
				else if (i == 1)
					result ^= 1;
				if (length(next) != length(from))
					result <<= 1;
				from = next;
			}
		}
		result ^= to;
		out.println("Case " + testNumber + ": " + result);
	}

	private int length(long to) {
		return Long.bitCount(Long.highestOneBit(to) - 1) + 1;
	}
}
