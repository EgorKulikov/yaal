package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {

	public static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long m = in.readInt();
		String s = in.next();
		long res = 1;
		long rem = 0;
		for (int i = 0; i < n; i++) {
			rem = (rem * 10) + (s.charAt(i) - '0');
			rem %= m;
			if (rem == 0 && i < n - 1) {
				res = (res * 2) % MOD;
			}
		}
		if (rem != 0) out.printLine(0); else out.printLine(res);
	}
}
