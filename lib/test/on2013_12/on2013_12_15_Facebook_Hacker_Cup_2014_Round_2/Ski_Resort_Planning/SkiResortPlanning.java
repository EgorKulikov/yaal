package on2013_12.on2013_12_15_Facebook_Hacker_Cup_2014_Round_2.Ski_Resort_Planning;



import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SkiResortPlanning {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count - 1);
		long answer = 1;
		int[] good = new int[count];
		for (int i = 0; i < count - 1; i++) {
			Arrays.fill(good, parent[i] + 1, i + 1, 0);
			int other = 0;
			Counter<Integer> counter = new Counter<Integer>();
			for (int j = i; j > parent[i]; j--) {
				if (good[j] != 0) {
					if (good[j] > 0) {
						other++;
						counter.add(good[j]);
					}
					continue;
				}
				int at = j;
				int last = -1;
				while (at > parent[i] && good[at] == 0) {
					last = at;
					at = parent[at - 1];
				}
				int value;
				if (at == parent[i] || at >= parent[i] && good[at] > 0)
					value = at == parent[i] ? last : good[at];
				else
					value = -1;
				at = j;
				while (at > parent[i] && good[at] == 0) {
					good[at] = value;
					at = parent[at - 1];
				}
				if (value > 0) {
					other++;
					counter.add(value);
				}
			}
			long current = IntegerUtils.power(2, other + 1, MOD);
			for (long v : counter.values()) {
				current -= IntegerUtils.power(2, v, MOD) - 1;
			}
			answer *= current % MOD - 1;
			answer %= MOD;
		}
		answer += MOD;
		answer %= MOD;
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
