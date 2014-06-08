package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class RoyAndAlphaBetaTrees {
	private static final long MOD = (long) 1e9 + 9;
	int[] multipliers;
	long[] numbers;
	long[][][] result;
	long[][][] qty;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		multipliers = IOUtils.readIntArray(in, 2);
		numbers = IOUtils.readLongArray(in, count);
		Arrays.sort(numbers);
		multipliers[1] *= -1;
		result = new long[2][count][count];
		qty = new long[2][count][count];
		ArrayUtils.fill(result, -1);
		out.printLine(calculate(0, 0, count - 1));
    }

	private long calculate(int type, int from, int to) {
		if (result[type][from][to] != -1)
			return result[type][from][to];
		result[type][from][to] = 0;
		for (int i = from; i <= to; i++) {
			long lQty;
			long lResult;
			if (from < i) {
				lResult = calculate(1 - type, from, i - 1);
				lQty = qty[1 - type][from][i - 1];
			} else {
				lResult = 0;
				lQty = 1;
			}
			long rQty;
			long rResult;
			if (i < to) {
				rResult = calculate(1 - type, i + 1, to);
				rQty = qty[1 - type][i + 1][to];
			} else {
				rResult = 0;
				rQty = 1;
			}
			result[type][from][to] += (lResult * rQty + rResult * lQty + numbers[i] * multipliers[type] % MOD * lQty % MOD * rQty) % MOD;
			qty[type][from][to] += lQty * rQty % MOD;
		}
		qty[type][from][to] %= MOD;
		result[type][from][to] %= MOD;
		if (result[type][from][to] < 0)
			result[type][from][to] += MOD;
		return result[type][from][to];
	}
}
