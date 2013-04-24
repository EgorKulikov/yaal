package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();

		int n = in.readInt();
		int[] t = new int[n];
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			t[i] = in.readInt();
			x[i] = in.readInt();
		}

		int[] d = new int[1 << n];
		for (int m = 1; m < (1 << n); m++) {
			d[m] |= m;
			int s = 0;
			int nm = 0;
			for (int i = 0; i < n; i++) {
				if (((m >> i) & 1) > 0) {
					s += t[i];
					nm++;
				}
			}
			s /= nm;
//			System.err.println(m + " " + s);
			for (int i = 0; i < n; i++) {
				if (((m >> i) & 1) == 0) {
					if (Math.abs(s - t[i]) <= x[i]) {
						d[m] |= (1 << i);
					}
				}
			}
		}
		int res = n;
		for (int m = 1; m < (1 << n) - 1; m++) {
//			System.err.println(m + " " + d[m]);
			int nm = 0;
			for (int i = 0; i < n; i++) {
				if (((m >> i) & 1) > 0) {
					nm++;
				} else {
					d[m | (1 << i)] |= d[m];
				}
			}
			if (d[m] == (1 << n) - 1) {
				res = Math.min(res, nm);
			}
		}
        out.printLine("Case " + testNumber + ": " + res);



    }
}
