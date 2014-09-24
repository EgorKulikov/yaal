package net.egork;

import net.egork.misc.ArrayUtils;

public class PatternLock {
	long[][] answer;
	long mod;

    public int solve(int N, int MOD) {
		answer = new long[N + 1][N + 1];
		ArrayUtils.fill(answer, -1);
		mod = MOD;
		answer[1][0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				calculate(i, j);
			}
			for (int j = 0; j <= i; j++) {
				calculate(j, i);
			}
		}
		return (int) (calculate(N, N) * 2 % mod);
    }

	private long calculate(int current, int opposite) {
		if (answer[current][opposite] != -1) {
			return answer[current][opposite];
		}
		if (current == 0) {
			return answer[current][opposite] = 0;
		}
		answer[current][opposite] = (current * calculate(opposite, current - 1) + 2 * calculate(current - 1, opposite)) % mod;
		return answer[current][opposite];
	}
}
