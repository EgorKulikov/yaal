package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[] f = new long[100];
		int m = 0;
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < 100; i++) {
			f[i] = f[i - 1] + f[i - 2];
			if (f[i] < 0) {
				m = i;
				break;
			}
		}
		//System.err.println(m);
		for (int i = 0; i < n; i++) {
			int[] z = new int[100];
			long k = in.readLong();
			for (int j = m - 1; j >= 0; j--) {
				if (k >= f[j]) {
					z[j] = 1;
					k -= f[j];
				}
			}
			for (int j = 0; j < 98; j++) {
				if (z[j] == 1) {
					if (z[j + 1] == 1) {
						z[j + 2] = 1;
						z[j] = 0;
						z[j + 1] = 0;
					} else if (z[j + 2] == 1) {
						z[j + 3] = 1;
						z[j - 1] = -1;
						z[j] = 0;
						z[j + 2] = 0;
					} else if (z[j + 3] == 1) {
						z[j + 4] = 1;
						z[j + 1] = -1;
						z[j] = 0;
						z[j + 3] = 0;
					}
				}
			}
			int res = 0;
			for (int j = 0; j < 100; j++) {
				res += Math.abs(z[j]);
			}
			out.printLine(res);
		}
	}
}
