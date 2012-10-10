package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	long p, q, x, y;
//	Map<Long, Long> result = new HashMap<Long, Long>();
	long[] result = new long[4000000];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		p = in.readLong();
		q = in.readLong();
		x = in.readLong();
		y = in.readLong();
		out.printLine(go(n));
	}

	private long go(long n) {
		if (n <= 0)
			return 1;
		if (n < result.length && result[((int) n)] != 0)
			return result[((int) n)];
		long answer = go(n / p - x) + go(n / q - y);
		if (n < result.length)
			result[((int) n)] = answer;
		return answer;
	}
}
