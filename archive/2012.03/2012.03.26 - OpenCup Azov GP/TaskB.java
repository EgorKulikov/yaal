package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out){
		long l = in.readLong();
		long r = in.readLong();
		long x = in.readLong();
		long k = in.readLong();

		long s = 0;
		for (int i = 62; i >= 0; i--) {
			long ss = s ^ x;
			long rr = ss | ((1L << i) - 1);
			long ll = rr - ((1L << i) - 1);

			long lll = Math.max(l, ll);
			long rrr = Math.min(r, rr);

			long c = 0;
			if (rrr >= lll) {
				c = (rrr - lll + 1);
			}

			if (k <= c) {
				// ok
			} else {
				k -= c;
				s += (1L << i);
			}
		}

		out.printLine(s ^ x);
	}
}
