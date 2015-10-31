package on2015_08.on2015_08_30_XV_Open_Cup_Onsite.K___Direction_Signs;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long finish = System.currentTimeMillis() + 4000;
		int n = in.readInt();
		int m = in.readInt();
		int[][] a = new int[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				a[i][j] = in.readInt();
			}
		}
		Random random = new Random(534175431531L);
		int[][] mask = new int[n][(m + 31) / 32];
		boolean[] ok = new boolean[n];
		long[] counts = new long[n];
		int[] best = new int[n];
		int[] prev = new int[n];
		int ansCount = 0;
		boolean[] answer = new boolean[n];
		while (System.currentTimeMillis() < finish) {
			int chosen = random.nextInt(n);
			int ncounts = 0;
			for (int i = 0; i < n; ++i) {
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				for (int j = 0; j < m; ++j) {
					int cur = a[i][j] - a[chosen][j];
					min = Math.min(min, cur);
					max = Math.max(max, cur);
				}
				ok[i] = max <= min + 1;
				if (!ok[i]) continue;
				int cnt = 0;
				Arrays.fill(mask[i], 0);
				if (min < max) {
					for (int j = 0; j < m; ++j) {
						int cur = a[i][j] - a[chosen][j];
						if (cur == max) {
							mask[i][j >> 5] |= 1 << (j & 31);
							++cnt;
						}
					}
				}
				counts[ncounts++] = (((long) cnt) << 32) + i;
			}
			Arrays.sort(counts, 0, ncounts);
			int gbest = 0;
			int gi = -1;
			for (int i = 0; i < ncounts; ++i) {
				best[i] = 1;
				prev[i] = -1;
				int w = (int) counts[i];
				int[] mi = mask[w];
				for (int j = 0; j < i; ++j) if (best[j] + 1 > best[i]) {
					int ww = (int) counts[j];
					int[] mj = mask[ww];
					boolean superset = true;
					for (int k = 0; k < mi.length; ++k) {
						if ((mi[k] & mj[k]) != mj[k]) {
							superset = false;
							break;
						}
					}
					if (superset) {
						best[i] = best[j] + 1;
						prev[i] = j;
					}
				}
				if (best[i] > gbest) {
					gbest = best[i];
					gi = i;
				}
			}
			if (gbest > ansCount) {
				ansCount = gbest;
				Arrays.fill(answer, false);
				int cur = gi;
				while (cur >= 0) {
					answer[(int) counts[cur]] = true;
					cur = prev[cur];
				}
			}
		}
		out.printLine(ansCount);
		long[] sorted = new long[ansCount];
		int ptr = 0;
		for (int i = 0; i < n; ++i) if (answer[i]) {
			long s = 0;
			for (int x : a[i]) s += x;
			sorted[ptr++] = (s << 32) + i;
		}
		if (ptr != sorted.length) throw new RuntimeException();
		Arrays.sort(sorted);
		ArrayUtils.reverse(sorted);
		boolean first = true;
		for (long l : sorted) {
			if (first) first = false; else out.print(" ");
			out.print(1 + (int) l);
		}
		out.printLine();
	}
}
