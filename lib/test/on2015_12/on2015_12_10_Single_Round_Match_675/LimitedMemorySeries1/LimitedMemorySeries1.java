package on2015_12.on2015_12_10_Single_Round_Match_675.LimitedMemorySeries1;



import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class LimitedMemorySeries1 {
	private static final int MOD = (int) (1e9 + 7);

	public long getSum(int n, int x0, int a, int b, int[] query) {
		int m = query.length;
		int[] left = ArrayUtils.createArray(m, 0);
		int[] right = ArrayUtils.createArray(m, MOD - 1);
		Arrays.sort(query);
		int[] middle = new int[m];
		int[] count = new int[m];
		while (!Arrays.equals(left, right)) {
			for (int i = 0; i < m; i++) {
				middle[i] = (left[i] + right[i]) >> 1;
			}
			long x = x0;
			Arrays.fill(count, 0);
			for (int i = 0; i < n; i++) {
				int l = 0;
				int r = m;
				while (l < r) {
					int mid = (l + r) >> 1;
					if (middle[mid] >= x) {
						r = mid;
					} else {
						l = mid + 1;
					}
				}
				if (l < m) {
					count[l]++;
				}
				x = (x * a + b) % MOD;
			}
			for (int i = 1; i < m; i++) {
				count[i] += count[i - 1];
			}
			for (int i = 0; i < m; i++) {
				if (count[i] <= query[i]) {
					left[i] = middle[i] + 1;
				} else {
					right[i] = middle[i];
				}
			}
		}
		return ArrayUtils.sumArray(left);
	}
}
