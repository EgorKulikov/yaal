package on2014_06.on2014_06_05_Single_Round_Match_623.CatchTheBeat;



import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class CatchTheBeat {
    public int maxCatched(int n, int x0, int y0, int a, int b, int c, int d, int mod1, int mod2, int offset) {
		long[] x = generate(n, x0, a, b, mod1);
		long[] y = generate(n, y0, c, d, mod2);
		for (int i = 0; i < n; i++) {
			x[i] -= offset;
		}
		long[] u = new long[n];
		long[] v = new long[n];
		for (int i = 0; i < n; i++) {
			u[i] = y[i] + x[i];
			v[i] = y[i] - x[i];
		}
		long[] position = new long[n + 2];
		Arrays.fill(position, Long.MAX_VALUE);
		position[0] = 0;
		int[] order = ArrayUtils.createOrder(n);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (u[first] != u[second])
					return Long.compare(u[first], u[second]);
				return Long.compare(v[first], v[second]);
			}
		});
		for (int i : order) {
			if (u[i] < 0 || v[i] < 0)
				continue;
			int left = 0;
			int right = n - 1;
			while (left < right) {
				int at = (left + right + 1) >> 1;
				if (position[at] <= v[i])
					left = at;
				else
					right = at - 1;
			}
			position[left + 1] = v[i];
		}
		for (int i = 0; ; i++) {
			if (position[i] == Long.MAX_VALUE)
				return i - 1;
		}
    }

	private long[] generate(int n, int first, int a, int b, int mod) {
		long[] result = new long[n];
		result[0] = first;
		for (int i = 1; i < n; i++)
			result[i] = (int) (((long)result[i - 1] * a + b) % mod);
		return result;
	}
}
