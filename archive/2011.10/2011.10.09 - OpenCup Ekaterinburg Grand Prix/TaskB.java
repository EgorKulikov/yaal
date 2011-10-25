import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int h = in.readInt();
		int x0 = in.readInt();
		int n = in.readInt();
		int m = n / 2;
		int[] d = new int[n];
		for (int i = 0; i < n; i++) {
			d[i] = in.readInt();
		}
		int[] p = new int[1];
		p[0] = x0;
		for (int i = 0; i < m; i++) {
			int[] q = new int[2 * p.length];
			int j = 0;
			for (int x : p) {
				int y = x + d[i];
				if (y <= h) {
					q[j++] = y;
				}
				y = x - d[i];
				if (y >= 0) {
					q[j++] = y;
				}
			}
			p = Arrays.copyOfRange(q, 0, j);
		}
		Arrays.sort(p);
		int MAX = 1 << (n - m);
		int amin = h + 1;
		int amax = -1;
		for (int mask = 0; mask < MAX; mask++) {
			int min = 0;
			int max = 0;
			int s = 0;
			for (int i = m; i < n; i++) {
				s += (((mask >> (i - m)) & 1) == 0) ? d[i] : (-d[i]);
				min = Math.min(min, s);
				max = Math.max(max, s);
			}
			int lo = -min;
			int hi = h - max;
			if (lo > hi) {
				continue;
			}
			int i = Arrays.binarySearch(p, lo);
			if (i < 0) {
				i = - 1 - i;
			}
			int j = Arrays.binarySearch(p, hi);
			if (j < 0) {
				j = - 2 - j;
			}
			if (i > j) {
				continue;
			}
			amin = Math.min(amin, p[i] + s);
			amax = Math.max(amax, p[j] + s);
		}
		out.println(amin + " " + amax);
	}
}

