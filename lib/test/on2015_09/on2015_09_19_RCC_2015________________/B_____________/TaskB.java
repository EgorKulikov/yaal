package on2015_09.on2015_09_19_RCC_2015________________.B_____________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	long[][][] answer;
	int start;
	int[] a;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int t = in.readInt();
		start = in.readInt() - 1;
		a = IOUtils.readIntArray(in, n);
		answer = new long[n][n][t + 1];
		ArrayUtils.fill(answer, -1);
		long result = 0;
		for (int i = 0; i < n; i++) {
			result = Math.max(result, go(i, i, t) + t * a[i]);
		}
		out.printLine(result);
	}

	private long go(int current, int other, int t) {
		if (answer[current][other][t] != -1) {
			return answer[current][other][t];
		}
		if (Math.abs(current - start) > t) {
			return answer[current][other][t] = Long.MIN_VALUE / 2;
		}
		answer[current][other][t] = 0;
		int down = Math.min(current, other) - 1;
		if (down >= 0 && current - down <= t) {
			int nt = t - (current - down);
			answer[current][other][t] = Math.max(answer[current][other][t], go(down, Math.max(current, other), nt) + nt * a[down]);
		}
		int up = Math.max(current, other) + 1;
		if (up < a.length && up - current <= t) {
			int nt = t - (up - current);
			answer[current][other][t] = Math.max(answer[current][other][t], go(up, Math.min(current, other), nt) + nt * a[up]);
		}
		return answer[current][other][t];
	}
}
