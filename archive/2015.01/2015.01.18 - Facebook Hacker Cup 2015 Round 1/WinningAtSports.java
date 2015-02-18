package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WinningAtSports {
	static final long MOD = (long) (1e9 + 7);

	long[][] free = new long[2001][2001];
	long[][] full = new long[2001][2001];

	{
		ArrayUtils.fill(free, -1);
		ArrayUtils.fill(full, -1);
		free[1][0] = 1;
		full[0][0] = 1;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String score = in.readString();
		int our = Integer.parseInt(score.split("-")[0]);
		int their = Integer.parseInt(score.split("-")[1]);
		out.printLine("Case #" + testNumber + ":", getFree(our, their), getFull(their, their));
    }

	private long getFull(int our, int their) {
		if (our > their || our < 0) return 0;
		if (full[our][their] != -1) return full[our][their];
		return full[our][their] = (getFull(our - 1, their) + getFull(our, their - 1)) % MOD;
	}

	private long getFree(int our, int their) {
		if (our <= their || their < 0) return 0;
		if (free[our][their] != -1) return free[our][their];
		return free[our][their] = (getFree(our - 1, their) + getFree(our, their - 1)) % MOD;
	}
}
