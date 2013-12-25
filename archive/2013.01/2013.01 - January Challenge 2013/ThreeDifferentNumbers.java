package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ThreeDifferentNumbers {
	static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] limits = IOUtils.readLongArray(in, 3);
//		for (int i = 0; i < 3; i++)
//			limits[i] %= MOD;
		long answer = (limits[0] % MOD) * (limits[1] % MOD) % MOD * (limits[2] % MOD);
		answer -= Math.min(limits[0], limits[1]) % MOD * (limits[2] % MOD);
		answer -= Math.min(limits[0], limits[2]) % MOD * (limits[1] % MOD);
		answer -= Math.min(limits[2], limits[1]) % MOD * (limits[0] % MOD);
		answer += 2 * Math.min(limits[0], Math.min(limits[1], limits[2])) % MOD;
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer);
    }
}
