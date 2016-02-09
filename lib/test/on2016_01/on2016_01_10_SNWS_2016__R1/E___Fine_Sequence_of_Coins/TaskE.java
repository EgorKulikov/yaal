package on2016_01.on2016_01_10_SNWS_2016__R1.E___Fine_Sequence_of_Coins;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int l = in.readInt();
		long[] p = IntegerUtils.generatePowers(l + 1, n + 1, MOD);
		long answer = 0;
		long[] result = new long[1 << (k - 1)];
		long[] next = new long[result.length];
		result[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < result.length; j++) {
				if (result[j] == 0) {
					continue;
				}
				long cmsk = 2 * j + 1;
				long delta = result[j] * p[n - i - 1] % MOD;
				for (int m = 0; m <= k && m <= l; m++) {
					long msk = cmsk | (cmsk << m);
					if ((msk >> k & 1) == 1) {
						answer += delta;
					} else {
						next[(int) ((msk >> 1) & (next.length - 1))] += result[j];
					}
				}
				if (l > k) {
					next[j] += result[j] * (l - k);
				}
			}
			for (int j = 0; j < result.length; j++) {
				result[j] = next[j] % MOD;
				next[j] = 0;
			}
		}
		out.printLine(answer % MOD);
	}
}
