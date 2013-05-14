package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int steps = in.readInt();
		long[] array = IOUtils.readLongArray(in, count);
		long[] c = new long[count];
		c[0] = 1;
		for (int i = 1; i < count; i++) {
			c[i] = c[i - 1] * (steps + i - 1) % MOD;
			c[i] = c[i] * BigInteger.valueOf(i).modInverse(BigInteger.valueOf(MOD)).longValue() % MOD;
		}
		long[] answer = new long[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j <= i; j++) {
				answer[i] += array[i - j] * c[j] % MOD;
			}
			answer[i] %= MOD;
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
