package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int m = in.readInt();
		int[] s = new int[m];
		int[] f = new int[m];
		int[] x = new int[m];
		IOUtils.readIntArrays(in, s, f, x);
		MiscUtils.decreaseByOne(s, f);
		for (int i = 0; i < m; i++) {
			int min = Math.min(s[i], f[i]);
			int max = Math.max(s[i], f[i]);
			s[i] = min;
			f[i] = max;
		}
		int[] delta = new int[n + 1];
		int[] dd = new int[m + 1];
		int t = in.readInt();
		for (int i = 0; i < t; i++) {
			int type = in.readInt();
			int start = in.readInt() - 1;
			int finish = in.readInt() - 1;
			if (finish < start) {
				int temp = finish;
				finish = start;
				start = temp;
			}
			if (type == 1) {
				dd[start] ^= 1;
				dd[finish + 1] ^= 1;
			} else {
				Arrays.fill(delta, 0);
				int cd = 0;
				for (int j = 0; j < m; j++) {
					cd ^= dd[j];
					dd[j] = 0;
					if (cd == 1) {
						delta[s[j]] ^= x[j];
						delta[f[j] + 1] ^= x[j];
					}
				}
				int val = 0;
				long answer = 0;
				for (int j = 0; j < n; j++) {
					val ^= delta[j];
					a[j] ^= val;
					if (j >= start && j <= finish) {
						answer += a[j];
					}
				}
				out.printLine(answer);
			}
		}
	}
}
