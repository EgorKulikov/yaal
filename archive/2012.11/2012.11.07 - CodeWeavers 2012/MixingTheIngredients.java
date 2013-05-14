package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MixingTheIngredients {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] value = IOUtils.readLongArray(in, count);
		char[] type = in.readString().toCharArray();
		long current = value[0];
		long answer = 1;
		for (int i = 0; i < count - 1; i++) {
			if (type[i] == 'm') {
				answer = answer * current % MOD;
				current = 0;
			}
			current = (current + value[i + 1]) % MOD;
		}
		answer = answer * current % MOD;
		out.printLine(answer);
	}
}
