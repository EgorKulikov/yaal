package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	long[][] answer;
	int good;
	long[][] powers;
	static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		good = in.readInt();
		answer = new long[1 << good][1 << good];
		powers = new long[good + 1][good + 1];
		for (int i = 0; i <= good; i++) {
			for (int j = 0; j <= good; j++)
				powers[i][j] = IntegerUtils.power(i, j);
		}
		ArrayUtils.fill(answer, -1);
		long answer = go(1, 1) * good % MOD;
		if (total != good) {
			answer *= IntegerUtils.power(total - good, total - good, MOD);
			answer %= MOD;
		}
		out.printLine(answer);
    }

	private long go(int all, int last) {
		if (answer[all][last] != -1)
			return answer[all][last];
		if (all == answer.length - 1)
			return answer[all][last] = 1;
		int remaining = (1 << good) - 1 - all;
		int base = Integer.bitCount(last);
		answer[all][last] = 0;
		for (int mask = remaining; mask != 0; mask = (mask - 1) & remaining) {
			answer[all][last] += go(all + mask, mask) * powers[base][Integer.bitCount(mask)];
			answer[all][last] %= MOD;
		}
		return answer[all][last];
	}
}
