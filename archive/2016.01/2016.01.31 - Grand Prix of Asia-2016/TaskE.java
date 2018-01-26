package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.readInt();
		}
		Arrays.sort(a);
		long[][] d = new long[n + 1][n + 1];
		ArrayUtils.fill(d, Long.MAX_VALUE);
		d[0][0] = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				d[i][j] = d[i - 1][j];
				if (j > 0 && a[i - 1] > d[i - 1][j - 1]) {
					d[i][j] = Math.min(d[i][j], d[i - 1][j - 1] + a[i - 1]);
				}
			}
		}
		for (int j = n; j >= 0; j--) {
			if (d[n][j] < Long.MAX_VALUE) {
				out.printLine(j);
				return;
			}
		}

	}
}
