package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	static final long MOD = 1000000007;

	long[][][] result;
	long[][][] resultSmall;
	long[][] c = IntegerUtils.generateBinomialCoefficients(13);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		result = new long[size + 1][count + 1][16];
		resultSmall = new long[size + 1][count + 1][16];
		ArrayUtils.fill(result, -1);
		ArrayUtils.fill(resultSmall, -1);
		long answer = calculate(size, count, 0);
		answer *= IntegerUtils.factorial(count, MOD);
		answer %= MOD;
		out.printLine(answer);
    }

	private long calculate(int size, int count, int mask) {
		if (count < 0)
			return 0;
		if (result[size][count][mask] != -1)
			return result[size][count][mask];
		long current = 0;
		if (mask == 0)
			current += 2 * calculate(0, count - 2, 15);
		for (int i = 0; i < 4; i++) {
			int j = (i + 1) & 3;
			if ((mask >> i & 1) != 0 || (mask >> j & 1) != 0)
				continue;
			int remaining = 15 - mask - (1 << i) - (1 << j);
			for (int k = remaining; ; k = (k - 1) & remaining) {
				current += calculateSmall(size, count - 1 - Integer.bitCount(k), mask + (1 << i) + (1 << j) + k);
				if (k == 0)
					break;
			}
		}
		int remaining = 15 - mask;
		for (int k = remaining; ; k = (k - 1) & remaining) {
			current += calculateSmall(size, count - Integer.bitCount(k), mask + k);
			if (k == 0)
				break;
		}
		return result[size][count][mask] = current % MOD;
	}

	private long calculateSmall(int size, int count, int mask) {
		if (count < 0)
			return 0;
		if (resultSmall[size][count][mask] != -1)
			return resultSmall[size][count][mask];
		if (size == 0)
			return resultSmall[size][count][mask] = count == 0 ? 1 : 0;
		int max = 3 * Integer.bitCount(15 - mask);
		int pairCount = 0;
		for (int i = 0; i < 4; i++) {
			int j = (i + 1) & 3;
			if ((mask >> i & 1) == 0 && ((mask >> j & 1) == 0))
				pairCount++;
		}
		long current = 0;
		for (int j = 0; j <= pairCount; j++) {
			for (int i = 0; i <= max - 2 * j; i++)
				current += calculate(size - 1, count - i - j, mask) * c[max - 2 * j][i] * c[pairCount][j];
		}
		return resultSmall[size][count][mask] = current % MOD;
	}
}
