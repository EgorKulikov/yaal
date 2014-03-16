package net.egork;

import net.egork.misc.ArrayUtils;

public class AlienAndSetDiv1 {
	private static final long MOD = (long) (1e9 + 7);
	long[][][] answer;
	int delta;

    public int getNumber(int N, int K) {
		answer = new long[2 * N + 1][N + 1][1 << K];
		ArrayUtils.fill(answer, -1);
		delta = K;
		return (int)go(2 * N, 0, 0);
    }

	private long go(int remaining, int outstanding, int mask) {
		if (outstanding < 0 || outstanding >= answer[remaining].length)
			return 0;
		if (answer[remaining][outstanding][mask] != -1)
			return answer[remaining][outstanding][mask];
		if (remaining == 0) {
			if (outstanding == 0 && mask == 0)
				return answer[remaining][outstanding][mask] = 1;
			return answer[remaining][outstanding][mask] = 0;
		}
		answer[remaining][outstanding][mask] = (go(remaining - 1, outstanding + (mask >> (delta - 1)), ((mask << 1) & ((1 << delta) - 1)) + 1) +
			go(remaining - 1, outstanding + (mask >> (delta - 1)) - 1, ((mask << 1) & ((1 << delta) - 1)))) % MOD;
		if (outstanding == 0 && mask == 0)
			answer[remaining][outstanding][mask] = answer[remaining][outstanding][mask] * 2 % MOD;
		return answer[remaining][outstanding][mask];
	}
}
